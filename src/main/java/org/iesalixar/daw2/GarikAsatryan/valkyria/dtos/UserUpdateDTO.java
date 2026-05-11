package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.iesalixar.daw2.GarikAsatryan.valkyria.validation.IsAdult;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserUpdateDTO {

    @NotBlank(message = "{msg.validation.required}")
    @Email(message = "{msg.validation.email}")
    @Size(max = 100, message = "{msg.validation.size}")
    private String email;

    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 100, message = "{msg.validation.size}")
    private String firstName;

    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 100, message = "{msg.validation.size}")
    private String lastName;

    @NotNull(message = "{msg.validation.required}")
    @IsAdult
    private LocalDate birthDate;

    @NotBlank(message = "{msg.validation.required}")
    @Size(max = 30, message = "{msg.validation.size}")
    private String phone;

    boolean enabled;

    List<String> roles;
}
