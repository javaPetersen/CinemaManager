package pl.petersen.cinemamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.petersen.cinemamanager.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m order by m.createdOn desc")
    List<Movie> findAllOrderCreatedOn();

}
