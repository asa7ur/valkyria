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
    private static final Logger logger = LoggerFactory.getLogger(CampingTypeService.class);

    private final TicketTypeRepository ticketTypeRepository;
    private final TicketTypeMapper ticketTypeMapper;

    public List<TicketTypeDTO> getAllTicketTypes() {
        logger.info("Recuperando lista completa de tipos de entrada");

        List<TicketTypeDTO> ticketTypes = ticketTypeMapper.toDTOList(
                ticketTypeRepository.findAll()
        );

        logger.debug("Total de tipos de entrada recuperados: {}", ticketTypes.size());
        return ticketTypes;
    }

    public TicketTypeDTO getTicketTypeById(Long id) {
        logger.info("Buscando tipo de entrada con ID: {}", id);

        TicketTypeDTO result = ticketTypeRepository.findById(id)
                .map(ticketTypeMapper::toDTO)
                .orElseThrow(() -> {
                    logger.error("Tipo de entrada con ID {} no encontrado", id);
                    return new AppException("msg.ticket.not-found", id);
                });

        logger.debug("Tipo de entrada encontrado: {}", result.getName());
        return result;
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