package pl.petersen.cinemamanager.entity;

import lombok.Getter;
import lombok.Setter;
import pl.petersen.cinemamanager.validators.Email;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "cnm_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Pattern(regexp = "(^[A-Z\\p{Lu}][a-z\\p{Ll}]{2,15})",
            message = "Must starts with capital letter, cannot contain whitespaces")
    @Size(min = 3, max = 30)
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "(^[A-Z\\p{Lu}][a-z\\p{Ll}]{2,25})[-]*([A-Z\\p{Lu}][a-z\\p{Ll}]{2,25})*",
            message = "Must starts with capital letter, cannot contain whitespaces")
    @Size(min = 3, max = 30)
    private String lastName;

    @Email
    @Column(unique = true)
    @Size(min = 5, max = 50)
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password should contains minimum eight characters, " +
                    "at least one uppercase letter, one lowercase letter, one number and one special character.")
    @Size(min = 8, max = 90)
    private String password;

    @Transient
    private String passwordMatcher;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservation;

    private Boolean active;

    private String role;

    public String getFullName() {
        return firstName + " " + lastName;
    }


}
