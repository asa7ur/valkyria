package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Este DTO representa la solicitud de compra que llega desde el frontend.
 * Contiene las listas de tickets y campings que el usuario desea adquirir.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    // Validamos que al menos se intente comprar algo
    @NotEmpty(message = "{msg.validation.at-least-one}")
    @Valid
    private List<TicketCreateDTO> tickets = new ArrayList<>();

    @Valid
    private List<CampingCreateDTO> campings = new ArrayList<>();

    private String guestEmail;
}