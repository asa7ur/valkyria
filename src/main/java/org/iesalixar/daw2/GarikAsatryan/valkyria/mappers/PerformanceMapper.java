package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.PerformanceDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Performance;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PerformanceMapper {

    /**
     * Convierte la entidad a DTO de visualización (incluye artista y escenario).
     */
    PerformanceDTO toDTO(Performance entity);

    /**
     * Convierte una lista de entidades a una lista de DTOs.
     */
    List<PerformanceDTO> toDTOList(List<Performance> entities);

    /**
     * Convierte el DTO de creación a entidad.
     * Ignoramos artist y stage aquí porque el Service los buscará
     * por ID en el repositorio para asegurar que existen.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artist", ignore = true)
    @Mapping(target = "stage", ignore = true)
    Performance toEntity(PerformanceCreateDTO dto);

    /**
     * Actualiza una entidad existente desde un DTO.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artist", ignore = true)
    @Mapping(target = "stage", ignore = true)
    void updateEntityFromDTO(PerformanceCreateDTO dto, @MappingTarget Performance entity);
}