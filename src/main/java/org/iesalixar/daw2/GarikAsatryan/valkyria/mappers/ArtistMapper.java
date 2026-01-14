package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistDetailDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.ArtistImageDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.ArtistImage;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistDTO toDTO(Artist artist);

    ArtistDetailDTO toDetailDTO(Artist artist);

    Artist toEntity(ArtistDTO dto);

    @Mapping(target = "id", ignore = true)
    Artist toEntity(ArtistCreateDTO createDTO);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateArtistFromDto(ArtistCreateDTO dto, @MappingTarget Artist entity);

    ArtistImageDTO toImageDTO(ArtistImage artistImage);

    @Mapping(target = "artist", ignore = true)
    ArtistImage toImageEntity(ArtistImageDTO artistImageDTO);
}
