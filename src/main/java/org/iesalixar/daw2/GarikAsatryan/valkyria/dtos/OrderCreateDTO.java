package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Este DTO representa la solicitud de compra que llega desde el frontend.
 * Contiene las listas de tickets y campings que el usuario desea adquirir.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {
    @NotEmpty(message = "{msg.validation.email}")
    @Valid
    private List<TicketCreateDTO> tickets = new ArrayList<>();

    @Valid
    private List<CampingCreateDTO> campings = new ArrayList<>();

    private String guestEmail;
}