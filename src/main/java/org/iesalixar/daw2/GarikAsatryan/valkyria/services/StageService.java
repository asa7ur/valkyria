package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Stage;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.StageMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.StageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio de negocio para la gestión de escenarios (stages) del festival.
 * Los escenarios son los espacios físicos donde tienen lugar las actuaciones.
 * <p>
 * Responsabilidades:
 * - Gestión CRUD de escenarios (creación, consulta, actualización, eliminación)
 * - Consultas del mapa de escenarios del festival
 * - Validación de existencia de escenarios para programación de actuaciones
 * <p>
 * Relaciones importantes:
 * - Un escenario puede tener múltiples actuaciones (performances)
 * - Un escenario puede tener múltiples patrocinadores (sponsors) asociados
 * - Los escenarios son referenciados en el programa del festival
 * <p>
 * IMPORTANTE: No se puede eliminar un escenario si tiene:
 * - Actuaciones programadas (constraint FK en Performance)
 * - Patrocinadores asociados (relación Many-to-Many)
 * En estos casos, la BD rechazará la operación con DataIntegrityViolationException
 */
@Service
@RequiredArgsConstructor
public class StageService {
    private static final Logger logger = LoggerFactory.getLogger(StageService.class);

    // Inyección de dependencias mediante constructor (Lombok @RequiredArgsConstructor)
    private final StageRepository stageRepository;
    private final StageMapper stageMapper;

    /**
     * Obtiene escenarios paginados con búsqueda opcional.
     * Permite filtrar por nombre o ubicación del escenario.
     *
     * @param searchTerm Término de búsqueda opcional (nombre, ubicación)
     * @param pageable   Configuración de paginación (página, tamaño, ordenación)
     * @return Página de DTOs de escenarios
     */
    public Page<StageDTO> getAllStages(String searchTerm, Pageable pageable) {
        logger.info("Iniciando búsqueda de escenarios. Término: '{}', Página: {}, Tamaño: {}",
                searchTerm != null ? searchTerm : "SIN FILTRO",
                pageable.getPageNumber(),
                pageable.getPageSize());

        // Decisión: búsqueda filtrada o listado completo
        Page<Stage> stagePage;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            logger.debug("Aplicando búsqueda con término: '{}'", searchTerm);
            stagePage = stageRepository.searchStages(searchTerm, pageable);
        } else {
            logger.debug("Recuperando todos los escenarios sin filtro");
            stagePage = stageRepository.findAll(pageable);
        }

        logger.debug("Escenarios encontrados: {} de {} totales",
                stagePage.getNumberOfElements(),
                stagePage.getTotalElements());

        // Convertir entidades a DTOs usando el mapper
        return stagePage.map(stageMapper::toDTO);
    }

    /**
     * Obtiene un escenario específico por su ID.
     * Incluye toda la información del escenario (nombre, capacidad, ubicación, etc.).
     *
     * @param id ID del escenario
     * @return Optional con el DTO del escenario o vacío si no existe
     */
    public Optional<StageDTO> getStageById(Long id) {
        logger.info("Buscando detalle del escenario con ID: {}", id);

        Optional<StageDTO> result = stageRepository.findById(id)
                .map(stageMapper::toDTO);

        if (result.isPresent()) {
            logger.debug("Escenario encontrado: {}",
                    result.get().getName());
        } else {
            logger.warn("No se encontró escenario con ID: {}", id);
        }

        return result;
    }

    /**
     * Crea un nuevo escenario en el sistema.
     * El escenario estará disponible para programar actuaciones inmediatamente.
     *
     * @param stageCreateDTO DTO con los datos del escenario a crear
     * @return DTO del escenario creado
     */
    @Transactional
    public StageDTO createStage(StageCreateDTO stageCreateDTO) {
        logger.info("Iniciando creación de escenario: {}", stageCreateDTO.getName());
        logger.debug("Datos del escenario: Nombre={}, Capacidad={}",
                stageCreateDTO.getName(),
                stageCreateDTO.getCapacity());

        // Conversión de DTO a entidad
        Stage stage = stageMapper.toEntity(stageCreateDTO);
        logger.debug("Entidad Stage mapeada desde DTO");

        // Persistencia en base de datos
        Stage saved = stageRepository.save(stage);

        logger.info("✓ Escenario creado con éxito. ID: {}, Nombre: '{}', Capacidad: {}",
                saved.getId(),
                saved.getName(),
                saved.getCapacity());

        return stageMapper.toDTO(saved);
    }

    /**
     * Actualiza los datos de un escenario existente.
     * IMPORTANTE: Si hay actuaciones programadas en este escenario, se actualizarán
     * automáticamente para reflejar el nuevo nombre/información del escenario.
     *
     * @param id             ID del escenario a actualizar
     * @param stageCreateDTO DTO con los nuevos datos
     * @return DTO del escenario actualizado
     * @throws AppException si el escenario no existe
     */
    @Transactional
    public StageDTO updateStage(Long id, StageCreateDTO stageCreateDTO) {
        logger.info("Iniciando actualización del escenario con ID: {}", id);

        // Buscar el escenario existente
        Stage existingStage = stageRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Escenario con ID {} no encontrado para actualización", id);
                    return new AppException("msg.stage.not-found", id);
                });

        logger.debug("Escenario encontrado: {}. Datos actuales: Nombre={}, Capacidad={}",
                id,
                existingStage.getName(),
                existingStage.getCapacity());

        // Actualizar los campos de la entidad con los datos del DTO
        stageMapper.updateEntityFromDTO(stageCreateDTO, existingStage);
        logger.debug("Datos del escenario actualizados en memoria");

        // Guardar cambios
        Stage updatedStage = stageRepository.save(existingStage);

        logger.info("✓ Escenario con ID {} actualizado correctamente. Nuevo nombre: '{}', Nueva capacidad: {}",
                id,
                updatedStage.getName(),
                updatedStage.getCapacity());

        return stageMapper.toDTO(updatedStage);
    }

    /**
     * Elimina un escenario del sistema.
     * <p>
     * RESTRICCIONES IMPORTANTES:
     * - No se puede eliminar si tiene actuaciones programadas (FK constraint)
     * - No se puede eliminar si tiene patrocinadores asociados (M2M relationship)
     * <p>
     * En estos casos, la base de datos rechazará la operación lanzando
     * DataIntegrityViolationException, que debe ser manejada por el controlador.
     * <p>
     * Estrategia recomendada:
     * - Primero eliminar/reasignar todas las actuaciones del escenario
     * - Luego eliminar las asociaciones con patrocinadores
     * - Finalmente eliminar el escenario
     *
     * @param id ID del escenario a eliminar
     * @throws AppException                                            si el escenario no existe
     * @throws org.springframework.dao.DataIntegrityViolationException si hay relaciones activas
     */
    @Transactional
    public void deleteStage(Long id) {
        logger.info("Iniciando eliminación del escenario con ID: {}", id);

        // Verificar que el escenario existe antes de intentar eliminar
        if (!stageRepository.existsById(id)) {
            logger.error("Intento de eliminar escenario inexistente con ID: {}", id);
            throw new AppException("msg.stage.not-found", id);
        }

        logger.debug("Escenario encontrado, procediendo a eliminar");
        logger.warn("ADVERTENCIA: Si el escenario tiene actuaciones o patrocinadores asociados, " +
                "la eliminación fallará por restricción de integridad referencial");

        // Intentar eliminar el escenario
        // Nota: Si hay relaciones activas, esto lanzará DataIntegrityViolationException
        stageRepository.deleteById(id);

        logger.info("✓ Escenario con ID {} eliminado correctamente del sistema", id);
    }
}