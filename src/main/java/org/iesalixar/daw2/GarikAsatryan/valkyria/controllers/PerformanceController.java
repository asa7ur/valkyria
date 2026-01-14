package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/performances")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping
    public ResponseEntity<List<PerformanceDTO>> getLineup() {
        List<PerformanceDTO> performances = performanceService.getAllPerformancesDTO();
        return ResponseEntity.ok(performances);
    }
}