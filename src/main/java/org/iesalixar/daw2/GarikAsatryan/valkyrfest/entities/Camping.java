package org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.validation.IsAdult;

import java.time.LocalDate;

@Entity
@Table(name = "campings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"campingType", "order"})
@EqualsAndHashCode(exclude = {"campingType", "order"})
public class Camping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{msg.camping.firstName.notBlank}")
    @Size(max = 100)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "{msg.camping.lastName.notBlank}")
    @Size(max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotNull(message = "{msg.camping.documentType.notNull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @NotBlank(message = "{msg.camping.documentNumber.notBlank}")
    @Size(max = 20)
    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @NotNull(message = "{msg.camping.birthDate.notNull}")
    @IsAdult
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "qr_code", unique = true)
    private String qrCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AttendeeStatus status = AttendeeStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_type_id")
    private CampingType campingType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
