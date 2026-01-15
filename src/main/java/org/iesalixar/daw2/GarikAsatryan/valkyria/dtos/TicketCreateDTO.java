package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesalixar.daw2.GarikAsatryan.valkyria.entities.DocumentType;
import org.iesalixar.daw2.GarikAsatryan.valkyria.validation.IsAdult;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreateDTO {

    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 100, message = "{msg.validation.size}")
    private String firstName;

    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 100, message = "{msg.validation.size}")
    private String lastName;

    @NotNull(message = "{msg.validation.required}")
    private DocumentType documentType;

    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 20, message = "{msg.validation.size}")
    private String documentNumber;

    @NotNull(message = "{msg.validation.required}")
    @IsAdult
    private LocalDate birthDate;

    @NotNull(message = "{msg.validation.required}")
    private Long ticketTypeId;
}