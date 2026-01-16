package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Sponsor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Stage;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.SponsorMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.SponsorRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.StageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de patrocinadores (sponsors).
 * Proporciona operaciones CRUD completas, gestión de imágenes y asociación con escenarios (stages).
 */
@Service
@RequiredArgsConstructor
public class SponsorService {
    private static final Logger logger = LoggerFactory.getLogger(SponsorService.class);

    // Constante que define el directorio donde se almacenan las imágenes de patrocinadores
    private static final String SPONSORS_FOLDER = "sponsors";

    // Inyección de dependencias mediante constructor (Lombok @RequiredArgsConstructor)
    private final SponsorRepository sponsorRepository;
    private final StageRepository stageRepository;
    private final SponsorMapper sponsorMapper;
    private final FileService fileService;

    /**
     * Obtiene una página de patrocinadores con soporte de paginación y búsqueda opcional.
     *
     * @param searchTerm Término de búsqueda opcional para filtrar por nombre o descripción
     * @param pageable   Configuración de paginación (página, tamaño, ordenación)
     * @return Página de DTOs de patrocinadores
     */
    public Page<SponsorDTO> getAllSponsors(String searchTerm, Pageable pageable) {
        logger.info("Iniciando búsqueda de patrocinadores. Término: '{}', Página: {}, Tamaño: {}",
                searchTerm != null ? searchTerm : "SIN FILTRO",
                pageable.getPageNumber(),
                pageable.getPageSize());

        // Decisión: búsqueda por término o listado completo
        Page<Sponsor> sponsorPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? sponsorRepository.searchSponsors(searchTerm, pageable)
                : sponsorRepository.findAll(pageable);

        logger.debug("Patrocinadores encontrados: {} de {} totales",
                sponsorPage.getNumberOfElements(),
                sponsorPage.getTotalElements());

        // Convertir entidades a DTOs usando el mapper
        return sponsorPage.map(sponsorMapper::toDTO);
    }

    /**
     * Obtiene la lista completa de patrocinadores sin paginación.
     * Útil para selectores o dropdowns en la interfaz.
     *
     * @return Lista de todos los patrocinadores como DTOs
     */
    public List<SponsorDTO> getAllSponsors() {
        logger.info("Recuperando lista completa de patrocinadores");

        List<SponsorDTO> sponsors = sponsorRepository.findAll().stream()
                .map(sponsorMapper::toDTO)
                .toList();

        logger.debug("Total de patrocinadores recuperados: {}", sponsors.size());
        return sponsors;
    }

    /**
     * Obtiene el detalle completo de un patrocinador por su ID.
     * Incluye información adicional como los escenarios asociados.
     *
     * @param id ID del patrocinador
     * @return Optional con el DTO detallado del patrocinador o vacío si no existe
     */
    public Optional<SponsorDetailDTO> getSponsorById(Long id) {
        logger.info("Buscando detalle del patrocinador con ID: {}", id);

        Optional<SponsorDetailDTO> result = sponsorRepository.findById(id)
                .map(sponsorMapper::toDetailDTO);

        if (result.isPresent()) {
            logger.debug("Patrocinador encontrado: {}", result.get().getName());
        } else {
            logger.warn("No se encontró patrocinador con ID: {}", id);
        }

        return result;
    }

    /**
     * Crea un nuevo patrocinador en el sistema.
     * Asocia automáticamente los escenarios (stages) especificados en el DTO.
     *
     * @param dto DTO con los datos del patrocinador a crear
     * @return DTO del patrocinador creado
     */
    @Transactional
    public SponsorDTO createSponsor(SponsorCreateDTO dto) {
        logger.info("Iniciando creación de nuevo patrocinador: {}", dto.getName());

        // Conversión de DTO a entidad
        Sponsor sponsor = sponsorMapper.toEntity(dto);
        logger.debug("Entidad Sponsor mapeada desde DTO");

        // Actualizar relaciones con escenarios (stages) si se proporcionaron
        updateSponsorStages(sponsor, dto.getStageIds());

        // Persistencia en base de datos
        Sponsor savedSponsor = sponsorRepository.save(sponsor);
        logger.info("Patrocinador creado con éxito. ID: {}, Nombre: {}",
                savedSponsor.getId(), savedSponsor.getName());

        return sponsorMapper.toDTO(savedSponsor);
    }

    /**
     * Actualiza los datos de un patrocinador existente.
     * Actualiza también las asociaciones con escenarios (stages).
     *
     * @param id  ID del patrocinador a actualizar
     * @param dto DTO con los nuevos datos
     * @return DTO del patrocinador actualizado
     * @throws AppException si el patrocinador no existe
     */
    @Transactional
    public SponsorDTO updateSponsor(Long id, SponsorCreateDTO dto) {
        logger.info("Iniciando actualización del patrocinador con ID: {}", id);

        // Buscar el patrocinador existente
        Sponsor existingSponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patrocinador con ID {} no encontrado para actualización", id);
                    return new AppException("msg.sponsor.not-found", id);
                });

        logger.debug("Patrocinador encontrado: {}. Datos actuales: Nombre={}",
                id, existingSponsor.getName());

        // Actualizar los campos de la entidad con los datos del DTO
        sponsorMapper.updateEntityFromDTO(dto, existingSponsor);
        logger.debug("Datos del patrocinador actualizados desde DTO");

        // Actualizar relaciones con escenarios
        updateSponsorStages(existingSponsor, dto.getStageIds());

        // Guardar cambios
        Sponsor updatedSponsor = sponsorRepository.save(existingSponsor);
        logger.info("Patrocinador con ID {} actualizado correctamente. Nuevo nombre: {}",
                id, updatedSponsor.getName());

        return sponsorMapper.toDTO(updatedSponsor);
    }

    /**
     * Método privado helper para actualizar las relaciones Many-to-Many con escenarios.
     * Busca los escenarios por sus IDs y los asocia al patrocinador.
     *
     * @param sponsor  Entidad del patrocinador a actualizar
     * @param stageIds Lista de IDs de escenarios a asociar (puede ser null)
     */
    private void updateSponsorStages(Sponsor sponsor, List<Long> stageIds) {
        if (stageIds != null) {
            logger.debug("Actualizando relación con {} escenarios", stageIds.size());

            // Buscar todos los escenarios por sus IDs
            List<Stage> stages = stageRepository.findAllById(stageIds);

            // Verificar si se encontraron todos los escenarios solicitados
            if (stages.size() != stageIds.size()) {
                logger.warn("Se solicitaron {} escenarios pero solo se encontraron {}. " +
                        "Algunos IDs pueden no existir", stageIds.size(), stages.size());
            }

            // Establecer la nueva lista de escenarios
            sponsor.setStages(stages);
            logger.debug("Relación con escenarios actualizada. Total escenarios asociados: {}",
                    stages.size());
        } else {
            logger.debug("No se proporcionaron IDs de escenarios, se mantiene la relación actual");
        }
    }

    /**
     * Procesa y guarda la imagen de un patrocinador.
     * Gestión completa: elimina la imagen anterior si existe, guarda la nueva y actualiza la BD.
     * Método transaccional para garantizar consistencia entre archivo y base de datos.
     *
     * @param id   ID del patrocinador
     * @param file Archivo de la nueva imagen
     * @return Nombre del archivo guardado
     * @throws AppException si el patrocinador no existe
     */
    @Transactional
    public String processAndSaveImage(Long id, MultipartFile file) {
        logger.info("Procesando nueva imagen para patrocinador con ID: {}", id);

        // Buscar el patrocinador
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patrocinador con ID {} no encontrado al intentar guardar imagen", id);
                    return new AppException("msg.sponsor.not-found", id);
                });

        // Paso 1: Eliminar la imagen anterior si existe
        if (sponsor.getImage() != null) {
            logger.debug("Eliminando imagen anterior: {}", sponsor.getImage());
            fileService.deleteFile(sponsor.getImage(), SPONSORS_FOLDER);
            logger.info("Imagen anterior eliminada correctamente");
        } else {
            logger.debug("No existía imagen anterior para este patrocinador");
        }

        // Paso 2: Guardar el nuevo archivo físicamente
        logger.debug("Guardando nuevo archivo de imagen...");
        String fileName = fileService.saveFile(file, SPONSORS_FOLDER);
        logger.info("Archivo guardado con nombre: {}", fileName);

        // Paso 3: Actualizar la referencia en la base de datos
        sponsor.setImage(fileName);
        sponsorRepository.save(sponsor);
        logger.info("Imagen actualizada en BD para patrocinador ID: {}. Nueva imagen: {}",
                id, fileName);

        return fileName;
    }

    /**
     * Elimina la imagen de un patrocinador tanto del sistema de archivos como de la base de datos.
     * No elimina al patrocinador, solo su imagen.
     *
     * @param id ID del patrocinador
     * @throws AppException si el patrocinador no existe
     */
    @org.springframework.transaction.annotation.Transactional
    public void deleteLogo(Long id) {
        logger.info("Iniciando eliminación de imagen del patrocinador con ID: {}", id);

        // Buscar el patrocinador
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patrocinador con ID {} no encontrado al intentar eliminar imagen", id);
                    return new AppException("msg.sponsor.not-found", id);
                });

        // Verificar si realmente tiene una imagen
        if (sponsor.getImage() != null) {
            String imageFileName = sponsor.getImage();
            logger.debug("Eliminando archivo de imagen: {}", imageFileName);

            // Eliminar archivo físico
            fileService.deleteFile(imageFileName, SPONSORS_FOLDER);

            // Eliminar referencia en base de datos
            sponsor.setImage(null);
            sponsorRepository.save(sponsor);

            logger.info("Imagen del patrocinador ID {} eliminada correctamente (archivo: {})",
                    id, imageFileName);
        } else {
            logger.info("El patrocinador ID {} no tiene imagen asignada, no hay nada que eliminar",
                    id);
        }
    }

    /**
     * Elimina un patrocinador del sistema.
     * Elimina primero la imagen asociada si existe antes de eliminar el registro.
     * IMPORTANTE: Las relaciones Many-to-Many con escenarios se eliminarán automáticamente.
     *
     * @param id ID del patrocinador a eliminar
     * @throws AppException si el patrocinador no existe
     */
    @Transactional
    public void deleteSponsor(Long id) {
        logger.info("Iniciando eliminación del patrocinador con ID: {}", id);

        // Buscar el patrocinador
        Sponsor sponsor = sponsorRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Patrocinador con ID {} no encontrado para eliminación", id);
                    return new AppException("msg.sponsor.not-found", id);
                });

        logger.debug("Patrocinador encontrado: {}", sponsor.getName());

        // Paso 1: Eliminar la imagen física si existe
        if (sponsor.getImage() != null) {
            logger.debug("Eliminando imagen asociada: {}", sponsor.getImage());
            fileService.deleteFile(sponsor.getImage(), SPONSORS_FOLDER);
            logger.info("Imagen del patrocinador eliminada del sistema de archivos");
        } else {
            logger.debug("El patrocinador no tiene imagen asociada");
        }

        // Paso 2: Eliminar el registro del patrocinador
        // Las relaciones Many-to-Many se eliminarán automáticamente de la tabla intermedia
        sponsorRepository.delete(sponsor);
        logger.info("Patrocinador con ID {} eliminado correctamente del sistema. " +
                "Relaciones con escenarios también eliminadas", id);
    }
}