package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingTypeDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.CampingType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CampingTypeMapper {
    CampingTypeDTO toDTO(CampingType entity);

    @Mapping(target = "id", ignore = true)
    CampingType toEntity(CampingTypeCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(CampingTypeCreateDTO dto, @MappingTarget CampingType entity);

    List<CampingTypeDTO> toDTOList(List<CampingType> entities);
}