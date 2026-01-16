package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Ticket;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.TicketType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.TicketMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Servicio de negocio para la gestión de tickets individuales (entradas).
 * Gestiona los tickets/entradas vendidas para el festival.
 * <p>
 * Diferencia entre Ticket y TicketType:
 * - TicketType: Categoría/tipo de entrada (VIP, General, Early Bird) - datos maestros
 * - Ticket: Entrada individual vendida a una persona específica - transaccional
 * <p>
 * Responsabilidades:
 * - Consulta de tickets vendidos (para administración y reportes)
 * - Gestión manual de tickets individuales (casos especiales)
 * - Asociación de tickets con tipos de entrada
 * <p>
 * NOTA IMPORTANTE:
 * Los tickets normalmente se crean automáticamente durante el proceso de compra
 * (ver OrderService.executeOrder()). Este servicio proporciona operaciones CRUD
 * para gestión administrativa o casos especiales (cortesías, cambios, etc.).
 * <p>
 * Cada ticket tiene:
 * - Asistente (nombre y apellidos)
 * - Tipo de entrada (TicketType)
 * - Código QR único para validación
 * - Asociación con un pedido (Order)
 */
@Service
@RequiredArgsConstructor
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    // Inyección de dependencias mediante constructor (Lombok @RequiredArgsConstructor)
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketMapper ticketMapper;

    /**
     * Obtiene tickets paginados con búsqueda opcional.
     * Permite filtrar por nombre del asistente, tipo de entrada o código QR.
     * Utilizado principalmente en panel administrativo para gestión y reportes.
     *
     * @param searchTerm Término de búsqueda opcional (nombre, tipo, QR)
     * @param pageable   Configuración de paginación (página, tamaño, ordenación)
     * @return Página de DTOs de tickets
     */
    public Page<TicketDTO> getAllTickets(String searchTerm, Pageable pageable) {
        logger.info("Iniciando búsqueda de entradas. Término: '{}', Página: {}, Tamaño: {}",
                searchTerm != null ? searchTerm : "SIN FILTRO",
                pageable.getPageNumber(),
                pageable.getPageSize());

        // Decisión: búsqueda filtrada o listado completo
        Page<Ticket> ticketPage;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            logger.debug("Aplicando búsqueda con término: '{}'", searchTerm);
            ticketPage = ticketRepository.searchTickets(searchTerm, pageable);
        } else {
            logger.debug("Recuperando todas las entradas sin filtro");
            ticketPage = ticketRepository.findAll(pageable);
        }

        logger.debug("Entradas encontradas: {} de {} totales",
                ticketPage.getNumberOfElements(),
                ticketPage.getTotalElements());

        // Convertir entidades a DTOs usando el mapper
        return ticketPage.map(ticketMapper::toDTO);
    }

    /**
     * Obtiene el detalle completo de un ticket por su ID.
     * Incluye información del asistente, tipo de entrada y código QR.
     *
     * @param id ID del ticket
     * @return Optional con el DTO del ticket o vacío si no existe
     */
    public Optional<TicketDTO> getTicketById(Long id) {
        logger.info("Buscando detalle de la entrada con ID: {}", id);

        Optional<TicketDTO> result = ticketRepository.findById(id)
                .map(ticketMapper::toDTO);

        if (result.isPresent()) {
            TicketDTO ticket = result.get();
            logger.debug("Entrada encontrada: {} {} (Tipo: {}, QR: {})",
                    ticket.getFirstName(),
                    ticket.getLastName(),
                    ticket.getTicketTypeName(),
                    ticket.getQrCode());
        } else {
            logger.warn("No se encontró entrada con ID: {}", id);
        }

        return result;
    }

    /**
     * Crea un nuevo ticket manualmente asociándolo a un TicketType.
     * <p>
     * USO TÍPICO:
     * - Entradas de cortesía para invitados especiales
     * - Corrección de errores en pedidos
     * - Casos especiales que requieren creación manual
     * <p>
     * NOTA: En flujo normal de compra, los tickets se crean automáticamente
     * en OrderService.executeOrder() con generación de QR y asignación a pedido.
     *
     * @param dto DTO con los datos del ticket a crear
     * @return DTO del ticket creado
     * @throws AppException si el tipo de ticket no existe
     */
    @Transactional
    public TicketDTO createTicket(TicketCreateDTO dto) {
        logger.info("Iniciando creación manual de entrada para: {} {}",
                dto.getFirstName(), dto.getLastName());
        logger.debug("Tipo de entrada solicitado ID: {}", dto.getTicketTypeId());

        // Paso 1: Buscar y validar el tipo de entrada
        TicketType type = ticketTypeRepository.findById(dto.getTicketTypeId())
                .orElseThrow(() -> {
                    logger.error("Tipo de entrada con ID {} no encontrado al crear ticket",
                            dto.getTicketTypeId());
                    return new AppException("msg.ticket.type-not-found", dto.getTicketTypeId());
                });

        logger.debug("Tipo de entrada encontrado: {} (Precio: {}, Stock: {})",
                type.getName(), type.getPrice(), type.getStockAvailable());

        // Paso 2: Mapear DTO a entidad
        Ticket ticket = ticketMapper.toEntity(dto);
        logger.debug("Entidad Ticket mapeada desde DTO");

        // Paso 3: Establecer la relación con el tipo de entrada
        ticket.setTicketType(type);
        logger.debug("Relación con tipo de entrada establecida");

        // Paso 4: Persistir en base de datos
        Ticket saved = ticketRepository.save(ticket);

        logger.info("✓ Entrada creada exitosamente. ID: {}, Asistente: {} {}, Tipo: {}",
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                type.getName());

        return ticketMapper.toDTO(saved);
    }

    /**
     * Actualiza un ticket existente.
     * Permite cambiar el asistente o el tipo de entrada.
     * <p>
     * CASOS DE USO:
     * - Corrección de nombres mal escritos
     * - Cambio de tipo de entrada (upgrade/downgrade)
     * - Transferencia de entrada a otra persona
     * <p>
     * IMPORTANTE: No se puede cambiar el pedido asociado ni el código QR.
     *
     * @param id  ID del ticket a actualizar
     * @param dto DTO con los nuevos datos
     * @return DTO del ticket actualizado
     * @throws AppException si el ticket o el tipo de entrada no existen
     */
    @Transactional
    public TicketDTO updateTicket(Long id, TicketCreateDTO dto) {
        logger.info("Iniciando actualización de entrada con ID: {}", id);

        // Paso 1: Buscar el ticket existente
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Entrada con ID {} no encontrada para actualización", id);
                    return new AppException("msg.ticket.not-found", id);
                });

        logger.debug("Entrada encontrada. Datos actuales: Asistente={} {}, Tipo={}",
                existing.getFirstName(),
                existing.getLastName(),
                existing.getTicketType().getName());

        // Paso 2: Buscar y validar el nuevo tipo de entrada
        TicketType type = ticketTypeRepository.findById(dto.getTicketTypeId())
                .orElseThrow(() -> {
                    logger.error("Tipo de entrada con ID {} no encontrado al actualizar ticket",
                            dto.getTicketTypeId());
                    return new AppException("msg.ticket.type-not-found", dto.getTicketTypeId());
                });

        logger.debug("Nuevo tipo de entrada encontrado: {}", type.getName());

        // Paso 3: Actualizar los campos de la entidad
        ticketMapper.updateEntityFromDTO(dto, existing);
        existing.setTicketType(type);
        logger.debug("Datos de la entrada actualizados en memoria");

        // Paso 4: Persistir cambios
        Ticket updated = ticketRepository.save(existing);

        logger.info("✓ Entrada ID {} actualizada correctamente. Nuevo asistente: {} {}, Nuevo tipo: {}",
                id,
                updated.getFirstName(),
                updated.getLastName(),
                type.getName());

        return ticketMapper.toDTO(updated);
    }

    /**
     * Elimina un ticket del sistema.
     * <p>
     * ADVERTENCIA: Esta operación debe usarse con precaución.
     * Eliminar un ticket puede:
     * - Dejar inconsistente la información del pedido asociado
     * - Afectar los reportes de ventas
     * - Causar problemas si el QR ya fue enviado al usuario
     * <p>
     * CASOS DE USO VÁLIDOS:
     * - Cancelación de entrada antes de enviar confirmación
     * - Corrección de entradas duplicadas por error
     * - Tickets de prueba que deben eliminarse
     *
     * @param id ID del ticket a eliminar
     * @throws AppException si el ticket no existe
     */
    @Transactional
    public void deleteTicket(Long id) {
        logger.info("Iniciando eliminación de entrada con ID: {}", id);
        logger.warn("⚠ ADVERTENCIA: Eliminación de entrada en progreso. " +
                "Verificar que no afecte pedidos confirmados.");

        // Verificar que el ticket existe antes de intentar eliminar
        if (!ticketRepository.existsById(id)) {
            logger.error("Intento de eliminar entrada inexistente con ID: {}", id);
            throw new AppException("msg.ticket.not-found", id);
        }

        logger.debug("Entrada encontrada, procediendo a eliminar");

        // Eliminar el ticket
        ticketRepository.deleteById(id);

        logger.info("✓ Entrada con ID {} eliminada del sistema", id);
    }
}