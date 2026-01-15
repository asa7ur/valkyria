package org.iesalixar.daw2.GarikAsatryan.valkyria.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.services.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    /**
     * Obtiene una página de tickets.
     * Permite filtrar por término de búsqueda (nombre, documento, tipo, estado).
     */
    @GetMapping
    public ResponseEntity<Page<TicketDTO>> getAllTickets(
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(ticketService.getAllTickets(search, pageable));
    }

    /**
     * Obtiene la información detallada de un ticket específico.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo ticket.
     * La validación @IsAdult se dispara aquí automáticamente.
     */
    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@Valid @RequestBody TicketCreateDTO ticketCreateDTO) {
        TicketDTO created = ticketService.createTicket(ticketCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Actualiza los datos de un ticket existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketCreateDTO ticketCreateDTO) {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticketCreateDTO));
    }

    /**
     * Elimina un ticket del sistema.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}