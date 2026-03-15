package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private String message;
    private String username;
    private String firstName;
    private Collection<? extends GrantedAuthority> roles;
    private String redirectUrl;

    public AuthResponseDTO(String token, String message) {
        this.token = token;
        this.message = message;
    }
}