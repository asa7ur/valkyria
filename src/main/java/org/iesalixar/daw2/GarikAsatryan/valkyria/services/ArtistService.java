package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.ArtistMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.ArtistImageRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;
    private final ArtistImageRepository artistImageRepository;
    private final ArtistMapper artistMapper;
    private final MessageSource messageSource;
    private final FileService fileService;

    private static final String ARTISTS_FOLDER = "artists";

    /**
     * Obtiene una página de artistas, opcionalmente filtrada por nombre.
     */
    public Page<ArtistDTO> getAllArtists(String searchTerm, Pageable pageable) {
        try {
            logger.info("Buscando artistas con término: {}", searchTerm);
            Page<Artist> artistPage;

            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                artistPage = artistRepository.searchArtists(searchTerm, pageable);
            } else {
                artistPage = artistRepository.findAll(pageable);
            }

            return artistPage.map(artistMapper::toDTO);
        } catch (Exception e) {
            logger.error("Error al obtener la lista de artistas: {}", e.getMessage());
            throw new RuntimeException("Error al obtener los artistas.", e);
        }
    }

    /**
     * Obtiene el detalle completo de un artista por su ID.
     */
    public Optional<ArtistDetailDTO> getArtistById(Long id) {
        try {
            logger.info("Buscando detalle del artista con ID: {}", id);
            return artistRepository.findById(id).map(artistMapper::toDetailDTO);
        } catch (Exception e) {
            logger.error("Error al buscar artista con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al buscar el artista.", e);
        }
    }

    /**
     * Crea un nuevo artista verificando que el email no esté duplicado.
     */
    @Transactional
    public ArtistDTO createArtist(ArtistCreateDTO artistCreateDTO, Locale locale) {
        try {
            if (artistRepository.existsByEmail(artistCreateDTO.getEmail())) {
                throw new AppException("msg.artist.email-exists", artistCreateDTO.getEmail());
            }

            Artist artist = artistMapper.toEntity(artistCreateDTO);
            Artist savedArtist = artistRepository.save(artist);
            logger.info("Artista creado con éxito: {}", savedArtist.getName());
            return artistMapper.toDTO(savedArtist);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error al crear artista: {}", e.getMessage());
            throw new RuntimeException("No se pudo crear el artista.", e);
        }
    }

    /**
     * Actualiza un artista existente usando el Mapper para volcar los datos.
     */
    @Transactional
    public ArtistDTO updateArtist(Long id, ArtistCreateDTO artistCreateDTO, Locale locale) {
        try {
            Artist existingArtist = artistRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El artista no existe."));

            // Validar si el nuevo email ya lo tiene otro artista distinto al actual
            if (artistRepository.existsByEmailAndIdNot(artistCreateDTO.getEmail(), id)) {
                String errorMessage = messageSource.getMessage("msg.artist.email-exists", null, locale);
                throw new IllegalArgumentException(errorMessage);
            }

            // Usamos el mapper para actualizar la entidad existente
            artistMapper.updateEntityFromDTO(artistCreateDTO, existingArtist);

            Artist updatedArtist = artistRepository.save(existingArtist);
            logger.info("Artista con ID {} actualizado correctamente", id);
            return artistMapper.toDTO(updatedArtist);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error al actualizar artista con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("No se pudo actualizar el artista.", e);
        }
    }

    @Transactional
    public void updateLogo(Long id, String baseName) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artista no encontrado"));
        artist.setLogo(baseName);
        artistRepository.save(artist);
    }

    /**
     * Elimina un artista por su ID.
     */
    @Transactional
    public void deleteArtist(Long id) {
        try {
            if (!artistRepository.existsById(id)) {
                throw new AppException("msg.artist.not-found", id);
            }
            artistRepository.deleteById(id);
            logger.info("Artista con ID {} eliminado", id);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error al eliminar artista con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("No se pudo eliminar el artista.", e);
        }
    }

    /**
     * Elimina el logo de un artista tanto del sistema de archivos como de la base de datos.
     */
    @Transactional
    public void deleteLogo(Long id) {
        try {
            logger.info("Intentando eliminar logo del artista con ID: {}", id);
            Artist artist = artistRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("El artista no existe."));

            if (artist.getLogo() != null) {
                // 1. Borrado físico del archivo
                fileService.deleteFile(artist.getLogo(), ARTISTS_FOLDER);

                // 2. Actualización de la entidad
                artist.setLogo(null);
                artistRepository.save(artist);
                logger.info("Logo del artista {} eliminado correctamente", id);
            } else {
                logger.warn("El artista {} no tenía logo asignado", id);
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error al eliminar el logo del artista {}: {}", id, e.getMessage());
            throw new RuntimeException("No se pudo eliminar el logo.");
        }
    }

    /**
     * Elimina una imagen específica de la galería de un artista.
     */
    @Transactional
    public void deleteArtistImage(Long imageId) {
        try {
            logger.info("Intentando eliminar imagen de galería con ID: {}", imageId);

            artistImageRepository.findById(imageId).ifPresentOrElse(img -> {
                // 1. Borrado físico
                fileService.deleteFile(img.getImageUrl(), ARTISTS_FOLDER);

                // 2. Borrado del registro (esto rompe la relación en la base de datos)
                artistImageRepository.delete(img);
                logger.info("Imagen de galería {} eliminada correctamente", imageId);
            }, () -> {
                throw new IllegalArgumentException("La imagen no existe.");
            });

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error al eliminar la imagen de galería {}: {}", imageId, e.getMessage());
            throw new RuntimeException("No se pudo eliminar la imagen de la galería.");
        }
    }
}