package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.CampingDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Camping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CampingMapper {

    @Mapping(target = "campingTypeName", source = "campingType.name")
    @Mapping(target = "status", source = "status")
    CampingDTO toDTO(Camping entity);

    List<CampingDTO> toDTOList(List<Camping> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "campingType", ignore = true)
    @Mapping(target = "order", ignore = true)
    Camping toEntity(CampingCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "campingType", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updateEntityFromDTO(CampingCreateDTO dto, @MappingTarget Camping entity);
}
