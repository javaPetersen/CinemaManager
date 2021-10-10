package pl.petersen.cinemamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petersen.cinemamanager.entity.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
}
