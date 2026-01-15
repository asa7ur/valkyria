package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistImageDTO;
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
import java.util.stream.Collectors;

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
        logger.info("Buscando artistas...");

        Page<Artist> artistPage = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? artistRepository.searchArtists(searchTerm, pageable)
                : artistRepository.findAll(pageable);

        return artistPage.map(artistMapper::toDTO);
    }

    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(artistMapper::toDTO)
                .toList();
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
    public ArtistDTO createArtist(ArtistCreateDTO artistCreateDTO) {
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
    public ArtistDTO updateArtist(Long id, ArtistCreateDTO artistCreateDTO) {
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
     * Sube múltiples imágenes a la galería de un artista.
     *
     * @param artistId ID del artista.
     * @param files    Array de archivos multipart.
     * @return Lista de DTOs de las imágenes recién creadas.
     */
    @Transactional
    public List<ArtistImageDTO> uploadArtistImages(Long artistId, MultipartFile[] files) {
        // 1. Buscar el artista. Si no existe, lanza excepción.
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new AppException("msg.artist.not-found", artistId));

        List<ArtistImage> newImages = new ArrayList<>();

        // 2. Iterar sobre cada archivo recibido
        for (MultipartFile file : files) {
            // Ignorar archivos vacíos si se enviaron por error
            if (file.isEmpty()) {
                continue;
            }

            // 3. Guardar el archivo físicamente usando el servicio existente
            String fileName = fileService.saveFile(file, ARTISTS_FOLDER);

            // 4. Crear la entidad ArtistImage
            ArtistImage image = new ArtistImage();
            image.setImageUrl(fileName);
            image.setArtist(artist); // Asociación crucial (lado propietario de la relación)

            newImages.add(image);
        }

        // 5. Guardar todas las entidades de imagen en lote
        List<ArtistImage> savedImages = artistImageRepository.saveAll(newImages);
        logger.info("Se han subido {} imágenes para el artista con ID: {}", savedImages.size(), artistId);

        // 6. Devolver los DTOs mapeados
        return artistMapper.toImageDTOList(savedImages);
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