package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.SponsorDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Sponsor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StageMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SponsorMapper {

    SponsorDTO toDTO(Sponsor entity);

    SponsorDetailDTO toDetailDTO(Sponsor entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "stages", ignore = true)
    Sponsor toEntity(SponsorCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "stages", ignore = true)
    void updateEntityFromDTO(SponsorCreateDTO dto, @MappingTarget Sponsor entity);
}
