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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;
    private final ArtistImageRepository artistImageRepository;
    private final ArtistMapper artistMapper;
    private final FileService fileService;

    private static final String ARTISTS_FOLDER = "artists";

    /**
     * Obtiene una página de artistas, opcionalmente filtrada por nombre.
     */
    public Page<ArtistDTO> getAllArtists(String searchTerm, Pageable pageable) {
        logger.info("Buscando artistas con término: {}", searchTerm);
        Page<Artist> artistPage;

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            artistPage = artistRepository.searchArtists(searchTerm, pageable);
        } else {
            artistPage = artistRepository.findAll(pageable);
        }

        return artistPage.map(artistMapper::toDTO);
    }

    /**
     * Obtiene el detalle completo de un artista por su ID.
     */
    public Optional<ArtistDetailDTO> getArtistById(Long id) {
        logger.info("Buscando detalle del artista con ID: {}", id);
        return artistRepository.findById(id).map(artistMapper::toDetailDTO);
    }

    /**
     * Crea un nuevo artista verificando que el email no esté duplicado.
     */
    @Transactional
    public ArtistDTO createArtist(ArtistCreateDTO artistCreateDTO, Locale locale) {
        if (artistRepository.existsByEmail(artistCreateDTO.getEmail())) {
            throw new AppException("msg.artist.email-exists", artistCreateDTO.getEmail());
        }

        Artist artist = artistMapper.toEntity(artistCreateDTO);
        Artist savedArtist = artistRepository.save(artist);
        logger.info("Artista creado con éxito: {}", savedArtist.getName());
        return artistMapper.toDTO(savedArtist);
    }

    /**
     * Actualiza un artista existente.
     */
    @Transactional
    public ArtistDTO updateArtist(Long id, ArtistCreateDTO artistCreateDTO, Locale locale) {
        Artist existingArtist = artistRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.artist.not-found", id));

        // Validar si el nuevo email ya lo tiene otro artista distinto al actual
        if (artistRepository.existsByEmailAndIdNot(artistCreateDTO.getEmail(), id)) {
            throw new AppException("msg.artist.email-exists", artistCreateDTO.getEmail());
        }

        artistMapper.updateEntityFromDTO(artistCreateDTO, existingArtist);
        Artist updatedArtist = artistRepository.save(existingArtist);

        logger.info("Artista con ID {} actualizado correctamente", id);
        return artistMapper.toDTO(updatedArtist);
    }

    /**
     * Proceso completo de subida de logo: borra el anterior, guarda el nuevo y actualiza BD.
     * Centralizado aquí para asegurar la transaccionalidad.
     */
    @Transactional
    public String processAndSaveLogo(Long id, MultipartFile file) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.artist.not-found", id));

        // 1. Si ya tenía un logo, lo borramos físicamente
        if (artist.getLogo() != null) {
            fileService.deleteFile(artist.getLogo(), ARTISTS_FOLDER);
        }

        // 2. Guardamos el nuevo archivo
        String fileName = fileService.saveFile(file, ARTISTS_FOLDER);

        // 3. Actualizamos el nombre en la base de datos
        artist.setLogo(fileName);
        artistRepository.save(artist);

        logger.info("Nuevo logo guardado para el artista con ID: {}", id);
        return fileName;
    }

    /**
     * Elimina un artista por su ID.
     */
    @Transactional
    public void deleteArtist(Long id) {
        if (!artistRepository.existsById(id)) {
            throw new AppException("msg.artist.not-found", id);
        }
        artistRepository.deleteById(id);
        logger.info("Artista con ID {} eliminado", id);
    }

    /**
     * Elimina el logo de un artista tanto del sistema de archivos como de la base de datos.
     */
    @Transactional
    public void deleteLogo(Long id) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.artist.not-found", id));

        if (artist.getLogo() != null) {
            fileService.deleteFile(artist.getLogo(), ARTISTS_FOLDER);
            artist.setLogo(null);
            artistRepository.save(artist);
            logger.info("Logo del artista {} eliminado correctamente", id);
        }
    }

    /**
     * Elimina una imagen específica de la galería de un artista.
     */
    @Transactional
    public void deleteArtistImage(Long imageId) {
        artistImageRepository.findById(imageId).ifPresentOrElse(img -> {
            fileService.deleteFile(img.getImageUrl(), ARTISTS_FOLDER);
            artistImageRepository.delete(img);
            logger.info("Imagen de galería {} eliminada correctamente", imageId);
        }, () -> {
            throw new AppException("msg.image.not-found", imageId);
        });
    }
}