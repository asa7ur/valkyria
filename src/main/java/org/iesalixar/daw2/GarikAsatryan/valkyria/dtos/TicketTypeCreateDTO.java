package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.iesalixar.daw2.GarikAsatryan.valkyria.validation.FieldsComparison;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldsComparison(
        first = "stockAvailable",
        second = "stockTotal",
        message = "{msg.validation.comparison.invalid}"
)
public class TicketTypeCreateDTO {
    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 50, message = "{msg.validation.size}")
    private String name;

    @NotNull(message = "{msg.validation.required}")
    @PositiveOrZero(message = "{msg.validation.positive}")
    private BigDecimal price;

    @NotNull(message = "{msg.validation.required}")
    @PositiveOrZero(message = "{msg.validation.positive}")
    private Integer stockTotal;

    @NotNull(message = "{msg.validation.required}")
    @PositiveOrZero(message = "{msg.validation.positive}")
    private Integer stockAvailable;
}
