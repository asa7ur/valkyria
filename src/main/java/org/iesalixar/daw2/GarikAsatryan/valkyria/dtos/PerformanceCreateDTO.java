package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.validation.FieldsComparison;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldsComparison(
        first = "startTime",
        second = "endTime",
        message = "{msg.validation.performance.time}"
)
public class PerformanceCreateDTO {
    // Ya no necesitamos el ID aquí

    @NotNull(message = "{msg.validation.required}")
    private LocalDateTime startTime;

    @NotNull(message = "{msg.validation.required}")
    private LocalDateTime endTime;

    @NotNull(message = "{msg.validation.required}")
    private Long artistId;

    @NotNull(message = "{msg.validation.required}")
    private Long stageId;
}