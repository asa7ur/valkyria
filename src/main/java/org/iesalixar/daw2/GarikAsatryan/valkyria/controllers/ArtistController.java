package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.ArtistService;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/artists")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ArtistController {

    private static final String ARTISTS_FOLDER = "artists";

    private final ArtistService artistService;
    private final FileService fileService;

    // --- ENDPOINTS DE CRUD BÁSICO ---

    @GetMapping
    public ResponseEntity<Page<ArtistDTO>> getAllArtists(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(artistService.getAllArtists(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDetailDTO> getArtistById(@PathVariable Long id) {
        return artistService.getArtistById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(
            @Valid @RequestBody ArtistCreateDTO artistCreateDTO,
            Locale locale) {
        ArtistDTO created = artistService.createArtist(artistCreateDTO, locale);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(
            @PathVariable Long id,
            @Valid @RequestBody ArtistCreateDTO artistCreateDTO,
            Locale locale) {
        return ResponseEntity.ok(artistService.updateArtist(id, artistCreateDTO, locale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    // --- ENDPOINTS DE GESTIÓN DE IMÁGENES ---

    /**
     * Sube o actualiza el logo del artista.
     */
    @PostMapping("/{id}/logo")
    public ResponseEntity<String> uploadLogo(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            // 1. Borramos el logo anterior si existe
            artistService.deleteLogo(id);

            // 2. Guardamos el nuevo archivo físico
            String baseName = fileService.saveFile(file, ARTISTS_FOLDER);

            artistService.updateLogo(id, baseName);

            return ResponseEntity.ok(baseName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir el logo");
        }
    }

    /**
     * Elimina el logo actual.
     */
    @DeleteMapping("/{id}/logo")
    public ResponseEntity<Void> deleteLogo(@PathVariable Long id) {
        artistService.deleteLogo(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina una imagen específica de la galería.
     */
    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Void> deleteGalleryImage(@PathVariable Long imageId) {
        artistService.deleteArtistImage(imageId);
        return ResponseEntity.noContent().build();
    }
}