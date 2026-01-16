package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.ArtistService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    // --- ENDPOINTS DE CRUD BÁSICO ---

    @GetMapping("/all")
    public List<ArtistDTO> getArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping
    public ResponseEntity<Page<ArtistAdminDTO>> getAllArtists(
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
            @Valid @RequestBody ArtistCreateDTO artistCreateDTO) {
        ArtistDTO created = artistService.createArtist(artistCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(
            @PathVariable Long id,
            @Valid @RequestBody ArtistCreateDTO artistCreateDTO) {
        return ResponseEntity.ok(artistService.updateArtist(id, artistCreateDTO));
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
        String baseName = artistService.processAndSaveLogo(id, file);
        return ResponseEntity.ok(baseName);
    }

    /**
     * Sube múltiples imágenes a la galería del artista.
     * Se espera una petición multipart con una key "files" que contenga varios archivos.
     */
    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ArtistImageDTO>> uploadGalleryImages(
            @PathVariable Long id,
            @RequestParam("files") MultipartFile[] files) {

        List<ArtistImageDTO> newImages = artistService.uploadArtistImages(id, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(newImages);
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