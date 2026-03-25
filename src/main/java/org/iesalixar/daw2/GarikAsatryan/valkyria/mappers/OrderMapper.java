package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.OrderDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapper para la entidad Order.
 * 'uses' permite que MapStruct utilice TicketMapper y CampingMapper para las listas internas.
 */
@Mapper(
        componentModel = "spring",
        uses = {TicketMapper.class, CampingMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {

    /**
     * Convierte una entidad Order en un OrderDTO.
     * Automáticamente convertirá List<Ticket> -> List<TicketDTO>
     * y List<Camping> -> List<CampingDTO> gracias a los mappers en 'uses'.
     */
    OrderDTO toDTO(Order entity);

    /**
     * Convierte una lista de entidades en una lista de DTOs de respuesta.
     */
    List<OrderDTO> toDTOList(List<Order> entities);

    /**
     * Actualiza la entidad Order existente con los datos del DTO.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "campings", ignore = true)
    void updateEntityFromDTO(OrderCreateDTO dto, @MappingTarget Order entity);
}