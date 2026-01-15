package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketTypeCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketTypeMapper {
    TicketTypeDTO toDTO(TicketType entity);

    @Mapping(target = "id", ignore = true)
    TicketType toEntity(TicketTypeCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(TicketTypeCreateDTO dto, @MappingTarget TicketType entity);

    List<TicketTypeDTO> toDTOList(List<TicketType> entities);
}
