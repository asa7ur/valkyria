package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StageCreateDTO {
    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 100, message = "{msg.validation.size}")
    private String name;

    @NotNull(message = "{msg.validation.required}")
    @Min(value = 1, message = "{msg.validation.positive}")
    private Long capacity;
}
