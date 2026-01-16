package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistAdminDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String genre;
    private String country;
}