package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public List<ArtistDTO> getArtists() {
        return artistService.getAllArtistsDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDetailDTO> getArtistById(@PathVariable Long id) {
        return artistService.getArtistDetailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}