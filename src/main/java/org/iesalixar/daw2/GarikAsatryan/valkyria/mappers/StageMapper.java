package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.StageDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StageMapper {

    StageDTO toDTO(Stage entity);

    List<StageDTO> toDTOList(List<Stage> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "performances", ignore = true)
    @Mapping(target = "sponsors", ignore = true)
    Stage toEntity(StageCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "performances", ignore = true)
    @Mapping(target = "sponsors", ignore = true)
    void updateEntityFromDTO(StageCreateDTO dto, @MappingTarget Stage entity);
}
