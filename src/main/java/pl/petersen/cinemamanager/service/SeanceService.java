package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.repository.SeanceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Seance> findAllInactiveSeances() {
        return seanceRepository.findAllInactiveSeances();
    }

    public List<Seat> findAllReservedSeatsBySeanceId(Long seanceId) {
        return reservationService.findReservationsBySeanceId(seanceId).stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Seat> findAllAvailableSeatsBySeanceId(Long seanceId) {
        List<Seat> allSeatsBySeanceId = findAllSeatsBySeanceId(seanceId);
        allSeatsBySeanceId.removeAll(findAllReservedSeatsBySeanceId(seanceId));
        return allSeatsBySeanceId;
    }

    private List<Seat> findAllSeatsBySeanceId(Long seanceId) {
        Optional<Seance> optionalSeance = findById(seanceId);
        if (optionalSeance.isPresent()) {
            return sortSeats(optionalSeance.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Seat> sortSeats(Seance seance) {
        return seance.getHall().getSeats().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public long countAllSeances() {
        return seanceRepository.count();
    }
}
