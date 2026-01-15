package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

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
public class SponsorDetailDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private BigDecimal contribution;
    private String image;
    private List<StageDTO> stages;
}
