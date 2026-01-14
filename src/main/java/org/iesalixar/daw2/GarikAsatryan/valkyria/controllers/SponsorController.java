package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.SponsorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SponsorController {

    private final SponsorService sponsorService;

    @GetMapping
    public List<SponsorDTO> getSponsors() {
        return sponsorService.getAllSponsorsDTO();
    }
}