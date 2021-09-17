package pl.petersen.cinemamanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ticket_types")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Column(nullable = false, precision = 7, scale = 2)
    @Digits(integer = 9, fraction = 2)
    @DecimalMin("0.00")
    private BigDecimal price;

    @ManyToMany(mappedBy = "ticketTypes")
    private Set<Seance> seances = new HashSet<>();


    public String getNameAndPrice() {
        return String.format("%s : %.2f pln", name, price);
    }
}
