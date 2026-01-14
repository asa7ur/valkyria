package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.CampingTypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/camping-types")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CampingTypeController {

    private final CampingTypeService campingTypeService;

    @GetMapping
    public List<CampingTypeDTO> getCampingTypes() {
        return campingTypeService.getAllCampingTypes().stream()
                .map(c -> new CampingTypeDTO(c.getId(), c.getName(), c.getPrice()))
                .toList();
    }
}