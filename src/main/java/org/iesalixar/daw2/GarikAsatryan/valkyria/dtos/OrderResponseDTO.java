package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.Data;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private List<TicketOrderDTO> tickets;
    private List<CampingOrderDTO> campings;
}