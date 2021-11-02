package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.entity.User;
import pl.petersen.cinemamanager.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReservationService reservationService;
    private final SeanceService seanceService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ReservationService reservationService, SeanceService seanceService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.reservationService = reservationService;
        this.seanceService = seanceService;
    }


    public void save(User user) {
        user.setActive(true);
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Boolean checkPasswordMatch(User user) {
        return user.getPassword().equals(user.getPasswordMatcher());
    }

    public boolean checkEmail(User user) {
        Optional<User> potentialUser = userRepository.findByEmail(user.getEmail());
        return potentialUser.isPresent();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long countAllUsers() {
        return userRepository.count();
    }

    public User findByEmail(String email) {
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isEmpty()) throw new NullPointerException();
        return optUser.get();
    }

    public void deleteReservation(Long deleteId) {
        reservationService.deleteById(deleteId);
    }

    public Optional<Seance> findSeanceById(Long id) {
        return seanceService.findById(id);
    }

    public List<Seance> getActiveSeances() {
        return seanceService.findAllActiveSeances();
    }

    public List<Seat> sortSeats(Seance seance) {
        return seanceService.sortSeats(seance);
    }

    public List<Seat> findAllActiveSeatsBySeanceId(Long id) {
        return seanceService.findAllAvailableSeatsBySeanceId(id);
    }

    public List<Seat> findAllReservedSeatsBySeanceId(Long id) {
        return seanceService.findAllReservedSeatsBySeanceId(id);
    }

    public Boolean checkIfAlreadyReserved(Reservation reservation) {
        return reservationService.checkIfAlreadyReserved(reservation);
    }

    public void saveReservation(Reservation reservation) {
        reservationService.save(reservation);
    }

}
