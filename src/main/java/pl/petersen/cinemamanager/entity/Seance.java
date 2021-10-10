package pl.petersen.cinemamanager.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seances")
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Movie movie;

    @NotNull
    @OneToOne
    private Hall hall;

    @NotEmpty
    @ManyToMany
    private Set<TicketType> ticketTypes = new HashSet<>();

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @OneToMany(mappedBy = "seance")
    private List<Reservation> reservations;

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.of(date, time);
    }


}
