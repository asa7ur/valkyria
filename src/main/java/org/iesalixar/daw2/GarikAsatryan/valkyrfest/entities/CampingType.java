package org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.validation.FieldsComparison;

import java.math.BigDecimal;

@Entity
@Table(name = "camping_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldsComparison(
        first = "stockAvailable",
        second = "stockTotal",
        message = "{msg.campingType.stock.invalid}"
)
public class CampingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{msg.campingType.name.notBlank}")
    @Size(max = 50, message = "{msg.campingType.name.size}")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull(message = "{msg.campingType.price.notNull}")
    @PositiveOrZero(message = "{msg.campingType.price.positive}")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "{msg.campingType.stockTotal.notNull}")
    @PositiveOrZero(message = "{msg.campingType.stockTotal.positive}")
    @Column(name = "stock_total", nullable = false)
    private Integer stockTotal;

    @NotNull(message = "{msg.campingType.stockAvailable.notNull}")
    @PositiveOrZero(message = "{msg.campingType.stockAvailable.positive}")
    @Column(name = "stock_available", nullable = false)
    private Integer stockAvailable;
}
