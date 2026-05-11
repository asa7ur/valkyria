package org.iesalixar.daw2.GarikAsatryan.valkyria.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeDTO {
    private Long id;
    private String name;
    private String nameEn;
    private BigDecimal price;
    private Integer stockTotal;
    private Integer stockAvailable;
}