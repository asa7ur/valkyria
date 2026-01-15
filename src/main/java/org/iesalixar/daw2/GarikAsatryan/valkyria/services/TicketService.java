package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Ticket;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.TicketType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.TicketMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketRepository;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketMapper ticketMapper;

    /**
     * Obtiene tickets paginados con búsqueda opcional.
     */
    public Page<TicketDTO> getAllTickets(String searchTerm, Pageable pageable) {
        logger.info("Buscando entradas...");
        Page<Ticket> ticketPage;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            ticketPage = ticketRepository.searchTickets(searchTerm, pageable);
        } else {
            ticketPage = ticketRepository.findAll(pageable);
        }
        return ticketPage.map(ticketMapper::toDTO);
    }

    /**
     * Obtiene el detalle de un ticket por ID.
     */
    public Optional<TicketDTO> getTicketById(Long id) {
        logger.info("Buscando detalle de la entrada con ID: {}", id);
        return ticketRepository.findById(id).map(ticketMapper::toDTO);
    }

    /**
     * Crea un nuevo ticket asociándolo a un TicketType.
     */
    @Transactional
    public TicketDTO createTicket(TicketCreateDTO dto) {
        TicketType type = ticketTypeRepository.findById(dto.getTicketTypeId())
                .orElseThrow(() -> new AppException("msg.ticket.type-not-found", dto.getTicketTypeId()));

        Ticket ticket = ticketMapper.toEntity(dto);
        ticket.setTicketType(type);

        Ticket saved = ticketRepository.save(ticket);
        logger.info("Ticket creado para: {} {}", saved.getFirstName(), saved.getLastName());
        return ticketMapper.toDTO(saved);
    }

    /**
     * Actualiza un ticket existente.
     */
    @Transactional
    public TicketDTO updateTicket(Long id, TicketCreateDTO dto) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.ticket.not-found", id));

        TicketType type = ticketTypeRepository.findById(dto.getTicketTypeId())
                .orElseThrow(() -> new AppException("msg.ticket.type-not-found", dto.getTicketTypeId()));

        ticketMapper.updateEntityFromDTO(dto, existing);
        existing.setTicketType(type);

        return ticketMapper.toDTO(ticketRepository.save(existing));
    }

    /**
     * Elimina un ticket.
     */
    @Transactional
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new AppException("msg.ticket.not-found", id);
        }
        ticketRepository.deleteById(id);
        logger.info("Ticket con ID {} eliminado", id);
    }
}