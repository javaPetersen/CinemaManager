package pl.petersen.cinemamanager.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "seats")
public class Seat implements Comparable<Seat>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(26)
    @Column(columnDefinition = "CHAR(1)", name = "row_of_seat")
    private Character row;

    @NotNull
    @Min(1)
    private Integer number;

    @ManyToMany(mappedBy = "seats")
    private List<Hall> halls;

    public Seat(Character row, Integer number) {
        this.row = row;
        this.number = number;
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
