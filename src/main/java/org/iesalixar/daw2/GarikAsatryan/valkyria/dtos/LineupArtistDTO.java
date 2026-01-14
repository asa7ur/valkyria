package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineupArtistDTO {
    private Long id;
    private String name;
    private String logo;
    private String genre;
}
