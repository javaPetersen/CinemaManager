package pl.petersen.cinemamanager.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 200)
    private String title;

    @Size(max = 200)
    private String originalTitle;

    @NotEmpty
    @Size(max = 1000)
    private String description;

    @NotNull
    @Min(1)
    @Max(300)
    private Long length;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String genre;

    @NotEmpty
    @Size(min = 2, max = 40)
    private String country;

    @NotEmpty
    @Size(min = 3, max = 80)
    private String director;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @NotEmpty
    private String poster;

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDateTime.now().withNano(0);
        this.updatedOn = this.createdOn;
    }

    @PreUpdate
    public void setUpdatedOn() {
        this.updatedOn = LocalDateTime.now().withNano(0);
    }


    public String getConvertedLength() {
        if (this.length > 59) {
            long hours = this.length / 60;
            long minutes = this.length % 60;
            return hours + "h. " + minutes + "min.";
        } else {
            return this.length + "min.";
        }
    }
}
