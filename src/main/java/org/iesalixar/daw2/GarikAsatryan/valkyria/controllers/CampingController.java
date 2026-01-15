package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.CampingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/campings")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CampingController {

    private final CampingService campingService;

    @GetMapping
    public ResponseEntity<Page<CampingDTO>> getAllCampings(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(campingService.getAllCampings(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampingDTO> getCampingById(@PathVariable Long id) {
        return campingService.getCampingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CampingDTO> createCamping(@Valid @RequestBody CampingCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(campingService.createCamping(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampingDTO> updateCamping(
            @PathVariable Long id,
            @Valid @RequestBody CampingCreateDTO dto) {
        return ResponseEntity.ok(campingService.updateCamping(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamping(@PathVariable Long id) {
        campingService.deleteCamping(id);
        return ResponseEntity.noContent().build();
    }
}
