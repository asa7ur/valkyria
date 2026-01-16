package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Camping;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.CampingType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.CampingMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.CampingTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Servicio de negocio para la gestión de campings.
 * Proporciona operaciones CRUD completas con validación de tipos de camping.
 */
@Service
@RequiredArgsConstructor
public class CampingService {
    private static final Logger logger = LoggerFactory.getLogger(CampingService.class);

    // Inyección de dependencias mediante constructor (Lombok @RequiredArgsConstructor)
    private final CampingRepository campingRepository;
    private final CampingTypeRepository campingTypeRepository;
    private final CampingMapper campingMapper;

    /**
     * Obtiene una página de campings con soporte de paginación y búsqueda opcional.
     *
     * @param searchTerm Término de búsqueda opcional para filtrar por nombre o ubicación
     * @param pageable   Configuración de paginación (página, tamaño, ordenación)
     * @return Página de DTOs de campings
     */
    public Page<CampingDTO> getAllCampings(String searchTerm, Pageable pageable) {
        logger.info("Iniciando búsqueda de campings. Término: '{}', Página: {}, Tamaño: {}",
                searchTerm != null ? searchTerm : "SIN FILTRO",
                pageable.getPageNumber(),
                pageable.getPageSize());

        // Decisión: búsqueda por término o listado completo
        Page<Camping> campingPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? campingRepository.searchCampings(searchTerm, pageable)
                : campingRepository.findAll(pageable);

        logger.debug("Campings encontrados: {} de {} totales",
                campingPage.getNumberOfElements(),
                campingPage.getTotalElements());

        // Convertir entidades a DTOs usando el mapper
        return campingPage.map(campingMapper::toDTO);
    }

    /**
     * Obtiene un camping específico por su ID.
     *
     * @param id ID del camping
     * @return Optional con el DTO del camping o vacío si no existe
     */
    public Optional<CampingDTO> getCampingById(Long id) {
        logger.info("Buscando camping con ID: {}", id);

        Optional<CampingDTO> result = campingRepository.findById(id)
                .map(campingMapper::toDTO);

        if (result.isPresent()) {
            logger.debug("Camping encontrado: ID {}", result.get().getId());
        } else {
            logger.warn("No se encontró camping con ID: {}", id);
        }

        return result;
    }

    /**
     * Crea un nuevo camping en el sistema.
     * Valida que el tipo de camping especificado exista antes de crear.
     *
     * @param dto DTO con los datos del camping a crear
     * @return DTO del camping creado
     * @throws AppException si el tipo de camping no existe
     */
    @Transactional
    public CampingDTO createCamping(CampingCreateDTO dto) {
        logger.info("Iniciando creación de nuevo camping.");
        logger.debug("Tipo de camping solicitado ID: {}", dto.getCampingTypeId());

        // Validación: verificar que el tipo de camping existe
        CampingType type = campingTypeRepository.findById(dto.getCampingTypeId())
                .orElseThrow(() -> {
                    logger.error("Tipo de camping con ID {} no encontrado al crear camping",
                            dto.getCampingTypeId());
                    return new AppException("msg.camping.type-not-found", dto.getCampingTypeId());
                });

        logger.debug("Tipo de camping encontrado: {}", type.getName());

        // Conversión de DTO a entidad
        Camping camping = campingMapper.toEntity(dto);

        // Establecer la relación con el tipo de camping
        camping.setCampingType(type);
        logger.debug("Relación con tipo de camping establecida");

        // Persistencia en base de datos
        Camping savedCamping = campingRepository.save(camping);
        logger.info("Camping creado con éxito. ID: {}, Tipo: {}",
                savedCamping.getId(),
                savedCamping.getCampingType().getName());

        return campingMapper.toDTO(savedCamping);
    }

    /**
     * Actualiza los datos de un camping existente.
     * Valida que tanto el camping como el tipo de camping existan.
     *
     * @param id  ID del camping a actualizar
     * @param dto DTO con los nuevos datos
     * @return DTO del camping actualizado
     * @throws AppException si el camping o el tipo de camping no existen
     */
    @Transactional
    public CampingDTO updateCamping(Long id, CampingCreateDTO dto) {
        logger.info("Iniciando actualización del camping con ID: {}", id);

        // Buscar el camping existente
        Camping existing = campingRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Camping con ID {} no encontrado para actualización", id);
                    return new AppException("msg.camping.not-found", id);
                });

        logger.debug("Camping con ID {} encontrado.", id);

        // Validación: verificar que el nuevo tipo de camping existe
        CampingType type = campingTypeRepository.findById(dto.getCampingTypeId())
                .orElseThrow(() -> {
                    logger.error("Tipo de camping con ID {} no encontrado al actualizar camping {}",
                            dto.getCampingTypeId(), id);
                    return new AppException("msg.camping.type-not-found", dto.getCampingTypeId());
                });

        logger.debug("Nuevo tipo de camping encontrado: {}", type.getName());

        // Actualizar los campos de la entidad con los datos del DTO
        campingMapper.updateEntityFromDTO(dto, existing);

        // Actualizar la relación con el tipo de camping
        existing.setCampingType(type);
        logger.debug("Datos del camping actualizados en memoria");

        // Guardar cambios
        Camping updatedCamping = campingRepository.save(existing);
        logger.info("Camping con ID {} actualizado correctamente.", id);

        return campingMapper.toDTO(updatedCamping);
    }

    /**
     * Elimina un camping del sistema.
     * IMPORTANTE: Las relaciones asociadas se gestionarán según la configuración de cascada.
     *
     * @param id ID del camping a eliminar
     * @throws AppException si el camping no existe
     */
    @Transactional
    public void deleteCamping(Long id) {
        logger.info("Iniciando eliminación del camping con ID: {}", id);

        // Verificar que el camping existe antes de intentar eliminar
        if (!campingRepository.existsById(id)) {
            logger.error("Intento de eliminar camping inexistente con ID: {}", id);
            throw new AppException("msg.camping.not-found", id);
        }

        // Eliminar el camping
        campingRepository.deleteById(id);
        logger.info("Camping con ID {} eliminado correctamente del sistema", id);
    }
}