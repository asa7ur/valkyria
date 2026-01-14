package org.iesalixar.daw2.GarikAsatryan.valkyria.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"performances", "images"})
@EqualsAndHashCode(exclude = {"performances", "images"})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "genre", nullable = false, length = 100)
    private String genre;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "official_url")
    private String officialUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "tiktok_url")
    private String tiktokUrl;

    @Column(name = "youtube_url")
    private String youtubeUrl;

    @Column(name = "tidal_url")
    private String tidalUrl;

    @Column(name = "spotify_url")
    private String spotifyUrl;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Performance> performances = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArtistImage> images = new ArrayList<>();
}