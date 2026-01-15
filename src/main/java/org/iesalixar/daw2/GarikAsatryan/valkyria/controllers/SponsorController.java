package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.SponsorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/sponsors")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SponsorController {

    private final SponsorService sponsorService;

    @GetMapping
    public ResponseEntity<Page<SponsorDTO>> getAllSponsors(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(sponsorService.getAllSponsors(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorDetailDTO> getSponsorById(@PathVariable Long id) {
        return sponsorService.getSponsorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SponsorDTO> createSponsor(@Valid @RequestBody SponsorCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sponsorService.createSponsor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SponsorDTO> updateSponsor(@PathVariable Long id, @Valid @RequestBody SponsorCreateDTO dto) {
        return ResponseEntity.ok(sponsorService.updateSponsor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
        sponsorService.deleteSponsor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(sponsorService.processAndSaveImage(id, file));
    }

    @DeleteMapping("/{id}/logo")
    public ResponseEntity<Void> deleteLogo(@PathVariable Long id) {
        sponsorService.deleteLogo(id);
        return ResponseEntity.noContent().build();
    }
}