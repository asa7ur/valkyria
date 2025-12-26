package org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.iesalixar.daw2.GarikAsatryan.valkyrfest.validation.IsAdult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles"})
@EqualsAndHashCode(exclude = {"roles"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{msg.user.email.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "{msg.user.email.notValid}")
    @Size(max = 100, message = "{msg.user.email.size}")
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @NotEmpty(message = "{msg.user.password.notEmpty}")
    @Column(name = "password", nullable = false)
    private String password;

    private boolean enabled;

    @NotEmpty(message = "{msg.user.firstName.notEmpty}")
    @Size(max = 100, message = "{msg.user.firstName.size}")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotEmpty(message = "{msg.user.lastName.notEmpty}")
    @Size(max = 100, message = "{msg.user.lastName.size}")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotNull(message = "{msg.user.birthDate.notNull}")
    @IsAdult
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotEmpty(message = "{msg.user.phone.notEmpty}")
    @Size(max = 30, message = "{msg.user.phone.size}")
    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @Column(name = "created_date", insertable = false, updatable = false)
    private LocalDateTime createdDate;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();
}
