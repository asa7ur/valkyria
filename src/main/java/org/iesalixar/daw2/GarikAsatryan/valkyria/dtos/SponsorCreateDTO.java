package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SponsorCreateDTO {
    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 100, message = "{msg.validation.size}")
    private String name;

    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 20, message = "{msg.validation.size}")
    private String phone;

    @NotBlank(message = "{msg.validation.required}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "{msg.validation.email}")
    private String email;

    @NotNull(message = "{msg.validation.required}")
    private BigDecimal contribution;

    // Recibimos solo los IDs de los escenarios asociados
    private List<Long> stageIds;
}
