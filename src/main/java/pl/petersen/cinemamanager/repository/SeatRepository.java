package pl.petersen.cinemamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.petersen.cinemamanager.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

}
