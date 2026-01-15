package org.iesalixar.daw2.GarikAsatryan.valkyria.mappers;

import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketCreateDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.dtos.TicketDTO;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Order;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.Ticket;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.TicketType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {

    /**
     * Mapea la entidad al DTO de visualización.
     * Extraemos el nombre del tipo de ticket directamente.
     */
    @Mapping(target = "ticketTypeName", source = "ticketType.name")
    TicketDTO toDTO(Ticket entity);

    /**
     * Mapea una lista de entidades a una lista de DTOs.
     */
    List<TicketDTO> toDTOList(List<Ticket> entities);

    /**
     * Mapea el DTO de creación a la entidad.
     * Ignoramos los campos que se gestionan en el Service.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "ticketType", ignore = true)
    @Mapping(target = "order", ignore = true)
    Ticket toEntity(TicketCreateDTO dto);

    /**
     * Actualiza una entidad existente.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "ticketType", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updateEntityFromDTO(TicketCreateDTO dto, @MappingTarget Ticket entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ticketType", source = "type")
    @Mapping(target = "order", source = "order")
    @Mapping(target = "qrCode", source = "qrCode")
    @Mapping(target = "status", constant = "ACTIVE")
        // Mapeamos los campos del DTO automáticamente y los extras manualmente
    Ticket toEntityFromOrder(TicketCreateDTO dto, TicketType type, Order order, String qrCode);
}