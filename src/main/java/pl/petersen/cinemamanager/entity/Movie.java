package pl.petersen.cinemamanager.entity;


import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@ToString
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 200)
    private String title;

    @Size(min = 1, max = 200)
    private String originalTitle;

    @Size(max = 1000)
    private String description;

    @NotNull
    @Min(1)
    @Max(300)
    private Long length;

    @Size(max = 30)
    private String genre;

    @Size(max = 50)
    private String country;

    @Size(max = 80)
    private String director;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] poster;

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDateTime.now().withNano(0);
        this.updatedOn = this.createdOn;
    }

    @PreUpdate
    public void setUpdatedOn() {
        this.updatedOn = LocalDateTime.now().withNano(0);
    }


}
