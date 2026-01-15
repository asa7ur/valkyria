package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketTypeCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.TicketType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.TicketTypeMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketTypeMapper ticketTypeMapper;

    public List<TicketTypeDTO> getAllTicketTypes() {
        return ticketTypeMapper.toDTOList(ticketTypeRepository.findAll());
    }

    public TicketTypeDTO getTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id)
                .map(ticketTypeMapper::toDTO)
                .orElseThrow(() -> new AppException("msg.ticket.not-found", id));
    }

    @Transactional
    public TicketTypeDTO createTicketType(TicketTypeCreateDTO dto) {
        TicketType entity = ticketTypeMapper.toEntity(dto);
        return ticketTypeMapper.toDTO(ticketTypeRepository.save(entity));
    }

    @Transactional
    public TicketTypeDTO updateTicketType(Long id, TicketTypeCreateDTO dto) {
        TicketType existing = ticketTypeRepository.findById(id)
                .orElseThrow(() -> new AppException("msg.ticket.not-found", id));

        ticketTypeMapper.updateEntityFromDTO(dto, existing);
        return ticketTypeMapper.toDTO(ticketTypeRepository.save(existing));
    }

    @Transactional
    public void deleteTicketType(Long id) {
        if (!ticketTypeRepository.existsById(id)) {
            throw new AppException("msg.ticket.not-found", id);
        }
        ticketTypeRepository.deleteById(id);
    }
}