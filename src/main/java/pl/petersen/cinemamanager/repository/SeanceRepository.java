package pl.petersen.cinemamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.petersen.cinemamanager.entity.Seance;

import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    @Query(value = "select s from Seance s where s.date >= current_date order by s.date, s.time")
    List<Seance> findAllActiveSeancesOrderByDateAsc();

    long countByHallId(Long hallId);

    long countByTicketTypesId(Long ticketTypeId);

    long countByMovieId(Long id);
}
