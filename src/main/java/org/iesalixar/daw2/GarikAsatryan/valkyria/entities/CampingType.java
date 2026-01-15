package org.iesalixar.daw2.GarikAsatryan.valkyria.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.iesalixar.daw2.GarikAsatryan.valkyria.validation.FieldsComparison;

import java.math.BigDecimal;

@Entity
@Table(name = "camping_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_total", nullable = false)
    private Integer stockTotal;

    @Column(name = "stock_available", nullable = false)
    private Integer stockAvailable;
}