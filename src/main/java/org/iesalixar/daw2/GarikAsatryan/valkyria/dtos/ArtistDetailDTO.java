package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDetailDTO {
    private Long id;
    private String name;
    private String genre;
    private String country;
    private String logo;
    private String description;
    private String officialUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;
    private String tidalUrl;
    private String spotifyUrl;
    private List<ArtistImageDTO> images;
}