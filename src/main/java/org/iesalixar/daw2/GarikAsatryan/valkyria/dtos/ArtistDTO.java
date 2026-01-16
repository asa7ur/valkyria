package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String genre;
    private String country;
    private String logo;
    private List<ArtistImageDTO> images;
}