package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.UserDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.UserRegistrationDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Role;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.User;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    /**
     * Mapea de Entidad a DTO.
     * Ignoramos los roles en el mapeo automático para hacerlo manualmente en el AfterMapping.
     */
    @Mapping(target = "roles", ignore = true)
    UserDTO toDTO(User entity);

    List<UserDTO> toDTOList(List<User> entities);

    /**
     * Mapea del DTO de registro a la Entidad.
     * Ignoramos campos que se gestionan en el servicio de registro (password cifrada, enabled, roles iniciales).
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    User toEntity(UserRegistrationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntityFromDTO(UserRegistrationDTO dto, @MappingTarget User entity);

    /**
     * Convierte la lista de entidades Role a una lista de Strings con el nombre del rol.
     */
    @AfterMapping
    default void mapRoles(User entity, @MappingTarget UserDTO dto) {
        if (entity.getRoles() != null) {
            dto.setRoles(entity.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toList()));
        }
    }
}