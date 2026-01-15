package org.iesalixar.daw2.GarikAsatryan.valkyria.services;

import lombok.RequiredArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketTypeCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.TicketType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.exceptions.AppException;
import org.iesalixar.daw2.GarikAsatryan.valkyria.mappers.TicketTypeMapper;
import org.iesalixar.daw2.GarikAsatryan.valkyria.repositories.TicketTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketTypeService {
    private static final Logger logger = LoggerFactory.getLogger(TicketTypeService.class);
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketTypeMapper ticketTypeMapper;

    public List<TicketTypeDTO> getAllTicketTypes() {
        try {
            return ticketTypeMapper.toDTOList(ticketTypeRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener tipos de ticket: {}", e.getMessage());
            throw new RuntimeException("Error al listar tipos de ticket");
        }
    }

    public TicketTypeDTO getTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id)
                .map(ticketTypeMapper::toDTO)
                .orElseThrow(() -> new AppException("msg.ticket.not-found", id));
    }

    @Transactional
    public TicketTypeDTO createTicketType(TicketTypeCreateDTO dto) {
        try {
            TicketType entity = ticketTypeMapper.toEntity(dto);
            return ticketTypeMapper.toDTO(ticketTypeRepository.save(entity));
        } catch (Exception e) {
            logger.error("Error al crear tipo de ticket: {}", e.getMessage());
            throw new RuntimeException("No se pudo crear el tipo de ticket");
        }
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