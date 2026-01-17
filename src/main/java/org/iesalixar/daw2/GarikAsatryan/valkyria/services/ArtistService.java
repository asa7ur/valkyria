package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.ArtistImage;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.ArtistMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.ArtistImageRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para la gestión de artistas y sus imágenes.
 * Proporciona operaciones CRUD completas, gestión de logos y galería de imágenes.
 */
@Service
@RequiredArgsConstructor
public class ArtistService {
    private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);

    // Inyección de dependencias mediante constructor (Lombok @RequiredArgsConstructor)
    private final ArtistRepository artistRepository;
    private final ArtistImageRepository artistImageRepository;
    private final ArtistMapper artistMapper;
    private final FileService fileService;

    // Constante que define el directorio donde se almacenan las imágenes de artistas
    private static final String ARTISTS_FOLDER = "artists";

    /**
     * Obtiene una página de artistas con soporte de paginación y búsqueda opcional.
     *
     * @param searchTerm Término de búsqueda opcional para filtrar por nombre
     * @param pageable   Configuración de paginación (página, tamaño, ordenación)
     * @return Página de DTOs de artistas
     */
    public Page<ArtistDTO> getAllArtists(String searchTerm, Pageable pageable) {
        logger.info("Iniciando búsqueda de artistas. Término: '{}', Página: {}, Tamaño: {}",
                searchTerm != null ? searchTerm : "SIN FILTRO",
                pageable.getPageNumber(),
                pageable.getPageSize());

        // Decisión: búsqueda por término o listado completo
        Page<Artist> artistPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? artistRepository.searchArtists(searchTerm, pageable)
                : artistRepository.findAll(pageable);

        logger.debug("Artistas encontrados: {} de {} totales",
                artistPage.getNumberOfElements(),
                artistPage.getTotalElements());

        // Convertir entidades a DTOs usando el mapper
        return artistPage.map(artistMapper::toDTO);
    }

    /**
     * Obtiene el detalle completo de un artista por su ID.
     * Incluye información adicional como galería de imágenes.
     *
     * @param id ID del artista
     * @return Optional con el DTO detallado del artista o vacío si no existe
     */
    public Optional<ArtistDetailDTO> getArtistById(Long id) {
        logger.info("Buscando detalle del artista con ID: {}", id);

        Optional<ArtistDetailDTO> result = artistRepository.findById(id)
                .map(artistMapper::toDetailDTO);

        if (result.isPresent()) {
            logger.debug("Artista encontrado: {}", result.get().getName());
        } else {
            logger.warn("No se encontró artista con ID: {}", id);
        }

        return result;
    }

    public List<ArtistLogoDTO> getArtistLogo() {
        logger.info("Iniciando recuperación de logos de todos los artistas");

        List<Artist> artists = artistRepository.findByLogoIsNotNull();

        logger.debug("Mapeando {} artistas filtrados a ArtistLogoDTO", artists.size());
        return artistMapper.toLogoDTOList(artists);
    }

    /**
     * Crea un nuevo artista en el sistema.
     * Valida que el email no esté duplicado antes de crear.
     *
     * @param artistCreateDTO DTO con los datos del artista a crear
     * @return DTO del artista creado
     * @throws AppException si el email ya existe
     */
    @Transactional
    public ArtistDTO createArtist(ArtistCreateDTO artistCreateDTO) {
        logger.info("Iniciando creación de nuevo artista: {}", artistCreateDTO.getName());

        // Validación: verificar que el email no esté en uso
        if (artistRepository.existsByEmail(artistCreateDTO.getEmail())) {
            logger.error("Intento de crear artista con email duplicado: {}", artistCreateDTO.getEmail());
            throw new AppException("msg.artist.email-exists", artistCreateDTO.getEmail());
        }

        // Conversión de DTO a entidad
        Artist artist = artistMapper.toEntity(artistCreateDTO);
        logger.debug("Entidad Artist mapeada desde DTO");

        // Persistencia en base de datos
        Artist savedArtist = artistRepository.save(artist);
        logger.info("Artista creado con éxito. ID: {}, Nombre: {}",
                savedArtist.getId(), savedArtist.getName());

        return artistMapper.toDTO(savedArtist);
    }

    /**
     * Actualiza los datos de un artista existente.
     * Valida que el nuevo email no esté en uso por otro artista.
     *
     * @param id              ID del artista a actualizar
     * @param artistCreateDTO DTO con los nuevos datos
     * @return DTO del artista actualizado
     * @throws AppException si el artista no existe o el email está duplicado
     */
    @Transactional
    public ArtistDTO updateArtist(Long id, ArtistCreateDTO artistCreateDTO) {
        logger.info("Iniciando actualización del artista con ID: {}", id);

        // Buscar el artista existente
        Artist existingArtist = artistRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Artista con ID {} no encontrado para actualización", id);
                    return new AppException("msg.artist.not-found", id);
                });

        logger.debug("Artista encontrado: {}. Datos actuales: Email={}, Nombre={}",
                id, existingArtist.getEmail(), existingArtist.getName());

        // Validación: verificar que el nuevo email no lo tenga otro artista
        if (artistRepository.existsByEmailAndIdNot(artistCreateDTO.getEmail(), id)) {
            logger.error("Email {} ya está en uso por otro artista", artistCreateDTO.getEmail());
            throw new AppException("msg.artist.email-exists", artistCreateDTO.getEmail());
        }

        // Actualizar los campos de la entidad con los datos del DTO
        artistMapper.updateEntityFromDTO(artistCreateDTO, existingArtist);
        logger.debug("Datos del artista actualizados en memoria");

        // Guardar cambios
        Artist updatedArtist = artistRepository.save(existingArtist);
        logger.info("Artista con ID {} actualizado correctamente. Nuevo nombre: {}",
                id, updatedArtist.getName());

        return artistMapper.toDTO(updatedArtist);
    }

    /**
     * Procesa y guarda el logo de un artista.
     * Gestión completa: elimina el logo anterior si existe, guarda el nuevo y actualiza la BD.
     * Método transaccional para garantizar consistencia entre archivo y base de datos.
     *
     * @param id   ID del artista
     * @param file Archivo del nuevo logo
     * @return Nombre del archivo guardado
     * @throws AppException si el artista no existe
     */
    @Transactional
    public String processAndSaveLogo(Long id, MultipartFile file) {
        logger.info("Procesando nuevo logo para artista con ID: {}", id);

        // Buscar el artista
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Artista con ID {} no encontrado al intentar guardar logo", id);
                    return new AppException("msg.artist.not-found", id);
                });

        // Paso 1: Eliminar el logo anterior si existe
        if (artist.getLogo() != null) {
            logger.debug("Eliminando logo anterior: {}", artist.getLogo());
            fileService.deleteFile(artist.getLogo(), ARTISTS_FOLDER);
            logger.info("Logo anterior eliminado correctamente");
        } else {
            logger.debug("No existía logo anterior para este artista");
        }

        // Paso 2: Guardar el nuevo archivo físicamente
        logger.debug("Guardando nuevo archivo de logo...");
        String fileName = fileService.saveFile(file, ARTISTS_FOLDER);
        logger.info("Archivo guardado con nombre: {}", fileName);

        // Paso 3: Actualizar la referencia en la base de datos
        artist.setLogo(fileName);
        artistRepository.save(artist);
        logger.info("Logo actualizado en BD para artista ID: {}. Nuevo logo: {}", id, fileName);

        return fileName;
    }

    /**
     * Sube múltiples imágenes a la galería de un artista.
     * Procesa un array de archivos, los guarda físicamente y crea las entidades correspondientes.
     *
     * @param artistId ID del artista propietario de las imágenes
     * @param files    Array de archivos multipart a subir
     * @return Lista de DTOs de las imágenes recién creadas
     * @throws AppException si el artista no existe
     */
    @Transactional
    public List<ArtistImageDTO> uploadArtistImages(Long artistId, MultipartFile[] files) {
        logger.info("Iniciando subida de imágenes para artista ID: {}. Total archivos: {}",
                artistId, files.length);

        // Paso 1: Verificar que el artista existe
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> {
                    logger.error("Artista con ID {} no encontrado al intentar subir imágenes", artistId);
                    return new AppException("msg.artist.not-found", artistId);
                });

        logger.debug("Artista encontrado: {}", artist.getName());

        List<ArtistImage> newImages = new ArrayList<>();
        int processedFiles = 0;
        int skippedFiles = 0;

        // Paso 2: Iterar sobre cada archivo recibido
        for (MultipartFile file : files) {
            // Ignorar archivos vacíos (pueden enviarse por error desde formularios)
            if (file.isEmpty()) {
                logger.warn("Archivo vacío detectado, se omite");
                skippedFiles++;
                continue;
            }

            logger.debug("Procesando archivo: {} ({})",
                    file.getOriginalFilename(),
                    file.getSize() + " bytes");

            // Paso 3: Guardar el archivo físicamente usando el servicio de archivos
            String fileName = fileService.saveFile(file, ARTISTS_FOLDER);
            logger.debug("Archivo guardado como: {}", fileName);

            // Paso 4: Crear la entidad ArtistImage y establecer la relación bidireccional
            ArtistImage image = new ArtistImage();
            image.setImageUrl(fileName);
            image.setArtist(artist); // Crucial: establecer la relación con el artista

            newImages.add(image);
            processedFiles++;
        }

        logger.info("Archivos procesados: {}, Archivos omitidos: {}", processedFiles, skippedFiles);

        // Paso 5: Guardar todas las entidades de imagen en lote (optimización)
        List<ArtistImage> savedImages = artistImageRepository.saveAll(newImages);
        logger.info("Se han guardado {} nuevas imágenes en BD para el artista ID: {}",
                savedImages.size(), artistId);

        // Paso 6: Convertir entidades a DTOs y devolver
        return artistMapper.toImageDTOList(savedImages);
    }

    /**
     * Elimina un artista del sistema.
     * IMPORTANTE: Las imágenes asociadas se eliminarán en cascada si está configurado en la entidad.
     *
     * @param id ID del artista a eliminar
     * @throws AppException si el artista no existe
     */
    @Transactional
    public void deleteArtist(Long id) {
        logger.info("Iniciando eliminación del artista con ID: {}", id);

        // Verificar que el artista existe antes de intentar eliminar
        if (!artistRepository.existsById(id)) {
            logger.error("Intento de eliminar artista inexistente con ID: {}", id);
            throw new AppException("msg.artist.not-found", id);
        }

        // Eliminar el artista (las relaciones en cascada se gestionan automáticamente)
        artistRepository.deleteById(id);
        logger.info("Artista con ID {} eliminado correctamente del sistema", id);
    }

    /**
     * Elimina el logo de un artista tanto del sistema de archivos como de la base de datos.
     * No elimina al artista, solo su logo.
     *
     * @param id ID del artista
     * @throws AppException si el artista no existe
     */
    @Transactional
    public void deleteLogo(Long id) {
        logger.info("Iniciando eliminación del logo del artista con ID: {}", id);

        // Buscar el artista
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Artista con ID {} no encontrado al intentar eliminar logo", id);
                    return new AppException("msg.artist.not-found", id);
                });

        // Verificar si realmente tiene un logo
        if (artist.getLogo() != null) {
            String logoFileName = artist.getLogo();
            logger.debug("Eliminando archivo de logo: {}", logoFileName);

            // Eliminar archivo físico
            fileService.deleteFile(logoFileName, ARTISTS_FOLDER);

            // Eliminar referencia en base de datos
            artist.setLogo(null);
            artistRepository.save(artist);

            logger.info("Logo del artista ID {} eliminado correctamente (archivo: {})", id, logoFileName);
        } else {
            logger.info("El artista ID {} no tiene logo asignado, no hay nada que eliminar", id);
        }
    }

    /**
     * Elimina una imagen específica de la galería de un artista.
     * Elimina tanto el archivo físico como el registro en la base de datos.
     *
     * @param imageId ID de la imagen a eliminar
     * @throws AppException si la imagen no existe
     */
    @Transactional
    public void deleteArtistImage(Long imageId) {
        logger.info("Iniciando eliminación de imagen de galería con ID: {}", imageId);

        // Buscar la imagen y procesarla si existe
        artistImageRepository.findById(imageId).ifPresentOrElse(
                image -> {
                    String imageUrl = image.getImageUrl();
                    logger.debug("Imagen encontrada: {}. Artista asociado: {}",
                            imageUrl, image.getArtist().getName());

                    // Eliminar archivo físico
                    logger.debug("Eliminando archivo físico: {}", imageUrl);
                    fileService.deleteFile(imageUrl, ARTISTS_FOLDER);

                    // Eliminar registro de base de datos
                    artistImageRepository.delete(image);
                    logger.info("Imagen de galería ID {} eliminada correctamente (archivo: {})",
                            imageId, imageUrl);
                },
                () -> {
                    // Si no existe, lanzar excepción
                    logger.error("Imagen con ID {} no encontrada en la base de datos", imageId);
                    throw new AppException("msg.image.not-found", imageId);
                }
        );
    }
}