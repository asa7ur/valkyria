package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.iesalixar.daw2.GarikAsatryan.valkyria.validation.FieldMatch;

/**
 * DTO para el proceso de cambio de contraseña.
 * Valida que la nueva contraseña y su confirmación coincidan.
 */
@Data
@FieldMatch(first = "newPassword", second = "confirmPassword", message = "msg.register.error.passwords-match")
public class PasswordChangeDTO {

    @NotBlank(message = "{msg.validation.password.required}")
    private String currentPassword;

    @NotBlank(message = "{msg.validation.password.required}")
    @Size(min = 8, message = "{msg.validation.password.size}")
    private String newPassword;

    @NotBlank(message = "{msg.validation.password.required}")
    private String confirmPassword;
}