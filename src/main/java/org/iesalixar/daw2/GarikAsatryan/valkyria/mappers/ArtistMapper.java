package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Artist;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.ArtistImage;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArtistMapper {

    ArtistDTO toDTO(Artist entity);

    ArtistDetailDTO toDetailDTO(Artist entity);

    List<ArtistDTO> toDTOList(List<Artist> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "performances", ignore = true)
    Artist toEntity(ArtistCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "performances", ignore = true)
    void updateEntityFromDTO(ArtistCreateDTO dto, @MappingTarget Artist entity);

    ArtistImageDTO toImageDTO(ArtistImage entity);

    @Mapping(target = "artist", ignore = true)
        // Importante para evitar recursión infinita
    ArtistImage toImageEntity(ArtistImageDTO dto);

    /**
     * Este método asegura que cada imagen de la lista tenga la referencia
     * al objeto Artist (la "parent side" de la relación).
     */
    @AfterMapping
    default void linkImages(@MappingTarget Artist artist) {
        if (artist.getImages() != null) {
            artist.getImages().forEach(image -> image.setArtist(artist));
        }
    }
}