package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.repository.SeanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final ReservationService reservationService;

    @Autowired
    public SeanceService(SeanceRepository seanceRepository, ReservationService reservationService) {
        this.seanceRepository = seanceRepository;
        this.reservationService = reservationService;
    }


    public Optional<Seance> findById(Long seanceId) {
        return seanceRepository.findById(seanceId);
    }

    public List<Seance> findAllActiveSeances() {
        return seanceRepository.findAllActiveSeancesOrderByDateAsc();
    }

    public void save(Seance seance) {
        seanceRepository.save(seance);
    }

    public boolean isHallAvailable(Seance seance) {
        List<Seance> seances = seanceRepository.findAll();
        seances.removeIf(s -> s.getId().equals(seance.getId()));
        return seances.stream()
                .filter(s -> s.getDate().equals(seance.getDate()) && s.getHall().equals(seance.getHall()))
                .allMatch(s -> seance.getTime().isAfter(s.getTime().plusMinutes(s.getMovie().getLength()))
                        || seance.getTime().plusMinutes(seance.getMovie().getLength()).isBefore(s.getTime()));
    }

    public long countByHallId(Long hallId) {
        return seanceRepository.countByHallId(hallId);
    }

    public long countByTicketTypesId(Long ticketTypeId) {
        return seanceRepository.countByTicketTypesId(ticketTypeId);
    }

    public long countByMovieId(Long id) {
        return seanceRepository.countByMovieId(id);
    }

    public Boolean deleteById(Long deleteId) {
        long count = reservationService.countBySeanceId(deleteId);
        if (count == 0) {
            seanceRepository.deleteById(deleteId);
            return true;
        }
        return false;
    }
}
