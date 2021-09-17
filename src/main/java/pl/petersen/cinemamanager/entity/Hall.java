package pl.petersen.cinemamanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hall_schema")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 90)
    private String name;

    @NotNull
    @Min(1)
    @Max(26)
    @Column(name = "hall_rows")
    private Integer rows;

    @NotNull
    @Min(1)
    private Integer numOfSeatsPerRow;

    @ManyToMany
    @JoinTable(name = "seats_halls")
    private Set<Seat> seats = new HashSet<>();

}
