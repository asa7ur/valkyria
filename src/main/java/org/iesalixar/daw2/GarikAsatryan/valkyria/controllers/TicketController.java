package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketTypeRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-types")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TicketController {
    private final TicketTypeRepository ticketTypeRepository;

    @GetMapping
    public List<TicketTypeDTO> getTickets() {
        return ticketTypeRepository.findAll().stream()
                .map(t -> new TicketTypeDTO(t.getId(), t.getName(), t.getPrice()))
                .toList();
    }
}