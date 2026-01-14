package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LineupArtistDTO artist;
    private StageDTO stage;
}