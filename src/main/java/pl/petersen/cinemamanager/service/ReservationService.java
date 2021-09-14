package pl.petersen.cinemamanager.service;

import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<Seat> findReservationsBySeanceId(Long reservationId) {
        return reservationRepository.findReservationsBySeanceId(reservationId);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
