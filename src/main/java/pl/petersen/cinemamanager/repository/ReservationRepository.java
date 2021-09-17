package pl.petersen.cinemamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.Seat;

import java.util.List;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("select r.seats from Reservation r where r.seance.id=:id")
    List<Seat> findReservationsBySeanceId(Long id);


    long countBySeanceId(Long seanceId);
}
