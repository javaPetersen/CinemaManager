package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public Boolean checkIfAlreadyReserved(Reservation reservation) {
        List<Seat> reservationsBySeanceId = findReservationsBySeanceId(reservation.getSeance().getId());
        return reservationsBySeanceId.stream()
                .anyMatch(r -> reservation.getSeats().contains(r));
    }

    public List<Seat> findReservationsBySeanceId(Long seanceId) {
        return reservationRepository.findReservationsBySeanceId(seanceId);
    }

    public List<Reservation> findAllOrderByDate() {
        return reservationRepository.findAllOrderByDate();
    }

    public long countBySeanceId(Long seanceId) {
        return reservationRepository.countBySeanceId(seanceId);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void deleteById(Long deleteId) {
        reservationRepository.deleteById(deleteId);
    }

    public long countAllReservations() {
        return reservationRepository.count();
    }
}
