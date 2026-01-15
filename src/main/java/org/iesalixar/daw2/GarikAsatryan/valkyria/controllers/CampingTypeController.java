package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.CampingTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/camping-types")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CampingTypeController {

    private final CampingTypeService campingTypeService;

    @GetMapping
    public ResponseEntity<List<CampingTypeDTO>> getAllCampingTypes() {
        return ResponseEntity.ok(campingTypeService.getAllCampingTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampingTypeDTO> getCampingTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(campingTypeService.getCampingTypeById(id));
    }

    @PostMapping
    public ResponseEntity<CampingTypeDTO> createCampingType(@Valid @RequestBody CampingTypeCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(campingTypeService.createCampingType(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampingTypeDTO> updateCampingType(
            @PathVariable Long id,
            @Valid @RequestBody CampingTypeCreateDTO dto) {
        return ResponseEntity.ok(campingTypeService.updateCampingType(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampingType(@PathVariable Long id) {
        campingTypeService.deleteCampingType(id);
        return ResponseEntity.noContent().build();
    }
}