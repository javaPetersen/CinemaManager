package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Hall;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.repository.HallRepository;
import pl.petersen.cinemamanager.repository.SeatRepository;

import java.util.*;

@Service
public class HallService {

    private final HallRepository hallRepository;
    private final SeatRepository seatRepository;
    private final SeanceService seanceService;

    @Autowired
    public HallService(HallRepository hallRepository, SeatRepository seatRepository, SeanceService seanceService) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
        this.seanceService = seanceService;
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
