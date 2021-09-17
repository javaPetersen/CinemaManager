package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.petersen.cinemamanager.entity.Hall;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.repository.HallRepository;
import pl.petersen.cinemamanager.repository.SeatRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HallService {

    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;
    private final SeanceService seanceService;
    private final ReservationService reservationService;

    @Autowired
    public HallService(HallRepository hallRepository, SeatRepository seatRepository, SeanceService seanceService, ReservationService reservationService) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.seanceService = seanceService;
        this.reservationService = reservationService;
    }


    public Boolean doesHallContainAnyReservations(Hall hall) {
        if (hall.getId() == null) {
            return false;
        }
        Optional<Hall> oldHall = hallRepository.findById(hall.getId());
        List<Reservation> allReservations = reservationService.findAll();
        if (oldHall.isPresent()) {
            if (allReservations.stream()
                    .anyMatch(r -> r.getSeance().getHall().equals(oldHall.get()))) {
                return hall.getNumOfSeatsPerRow() < oldHall.get().getNumOfSeatsPerRow()
                        || hall.getRows() < oldHall.get().getRows();
            }
        }
        return false;
    }

    public Optional<Hall> findById(Long hallId) {
        return hallRepository.findById(hallId);
    }

    public void save(Hall hall) {
        Integer rows = hall.getRows();
        Integer numOfSeatsPerRow = hall.getNumOfSeatsPerRow();
        Set<Seat> seats = generateSeats(rows, numOfSeatsPerRow);
        hall.setSeats(seats);
        hallRepository.save(hall);
    }


    private List<Seat> getSeats() {
        return seatRepository.findAll();
    }

    private final char[] LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private Set<Seat> generateSeats(int row, int numOfSeats) {
        Set<Seat> seats = new HashSet<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < numOfSeats; j++) {
                seats.add(checkSeatExistence(LETTERS[i], (j + 1)));
            }
        }
        return seats;
    }


    private Seat checkSeatExistence(Character letter, Integer num) {
        if (seatRepository.count() > 0) {
            List<Seat> existingSeats = getSeats();
            for (Seat existingSeat : existingSeats) {
                if (existingSeat.getRow().equals(letter)
                        && existingSeat.getNumber().equals(num)) {
                    return existingSeat;
                }
            }
        }
        Seat seat = new Seat(letter, num);
        seatRepository.save(seat);
        return seat;
    }

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    public Boolean deleteById(Long deleteId) {
        long count = seanceService.countByHallId(deleteId);
        if (count == 0) {
            hallRepository.deleteById(deleteId);
            return true;
        }
        return false;
    }
}
