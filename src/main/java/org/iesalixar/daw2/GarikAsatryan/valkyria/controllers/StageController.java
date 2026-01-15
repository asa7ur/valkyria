package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.StageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stages")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;

    @GetMapping
    public ResponseEntity<Page<StageDTO>> getAllStages(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(stageService.getAllStages(search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageDTO> getStageById(@PathVariable Long id) {
        return stageService.getStageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StageDTO> createStage(@Valid @RequestBody StageCreateDTO stageCreateDTO) {
        StageDTO created = stageService.createStage(stageCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StageDTO> updateStage(
            @PathVariable Long id,
            @Valid @RequestBody StageCreateDTO stageCreateDTO) {
        return ResponseEntity.ok(stageService.updateStage(id, stageCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }
}