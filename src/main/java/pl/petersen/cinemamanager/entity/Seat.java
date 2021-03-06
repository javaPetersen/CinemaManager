package pl.petersen.cinemamanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seats")
public class Seat implements Comparable<Seat> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "CHAR(1)", name = "row_of_seat")
    private Character row;

    private Integer number;

    @ManyToMany(mappedBy = "seats")
    private List<Hall> halls = new ArrayList<>();

    @ManyToMany(mappedBy = "seats")
    private List<Reservation> reservation;


    public Seat(Character row, Integer number) {
        this.row = row;
        this.number = number;
    }

    public String getFullName() {
        return row + "-" + number;
    }


    @Override
    public int compareTo(Seat o) {
        int result = this.getRow().compareTo(o.getRow());
        if (result == 0) {
            result = this.getNumber().compareTo(o.getNumber());
        }
        return result;
    }
}
