package pl.petersen.cinemamanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.petersen.cinemamanager.entity.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

}
