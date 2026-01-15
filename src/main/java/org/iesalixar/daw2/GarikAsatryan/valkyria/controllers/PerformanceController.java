package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.PerformanceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/performances")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    /**
     * Obtiene todas las actuaciones con paginación y búsqueda.
     * GET /api/v1/performances?search=rock&page=0&size=10
     */
    @GetMapping
    public ResponseEntity<Page<PerformanceDTO>> getAllPerformances(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(performanceService.getAllPerformances(search, pageable));
    }

    /**
     * Obtiene una actuación por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceDTO> getPerformanceById(@PathVariable Long id) {
        return performanceService.getPerformanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva actuación.
     * El @Valid disparará FieldsComparison y PerformanceOverlap.
     */
    @PostMapping
    public ResponseEntity<PerformanceDTO> createPerformance(
            @Valid @RequestBody PerformanceCreateDTO performanceCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(performanceService.createPerformance(performanceCreateDTO));
    }

    /**
     * Actualiza una actuación existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceDTO> updatePerformance(
            @PathVariable Long id,
            @Valid @RequestBody PerformanceCreateDTO performanceCreateDTO) {
        return ResponseEntity.ok(performanceService.updatePerformance(id, performanceCreateDTO));
    }

    /**
     * Elimina una actuación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformance(@PathVariable Long id) {
        performanceService.deletePerformance(id);
        return ResponseEntity.noContent().build();
    }
}