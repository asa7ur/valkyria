package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para devolver la información completa de un pedido.
 * Se usa tanto en el historial de pedidos como tras finalizar una compra.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private List<TicketDTO> tickets;
    private List<CampingDTO> campings;
}