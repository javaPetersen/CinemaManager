package pl.petersen.cinemamanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.entity.User;
import pl.petersen.cinemamanager.service.ReservationService;
import pl.petersen.cinemamanager.service.SeanceService;
import pl.petersen.cinemamanager.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class ReservationController {

    private final ReservationService reservationService;
    private final SeanceService seanceService;
    private final UserService userService;

    public ReservationController(ReservationService reservationService,
                                 SeanceService seanceService,
                                 UserService userService) {
        this.reservationService = reservationService;
        this.seanceService = seanceService;
        this.userService = userService;
    }

    @GetMapping("/reservation/create")
    public String createReservationForm(@RequestParam Long seanceId, Model model) {
        Seance seance = seanceService.findById(seanceId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Reservation reservation = new Reservation();
        reservation.setSeance(seance);


        List<Seat> allSeats = seance.getHall().getSeats().stream()
                .sorted().collect(Collectors.toList());

        List<Seat> activeSeats = seance.getHall().getSeats().stream()
                .sorted()
                .collect(Collectors.toList());


        List<Seat> reservedSeats = reservationService.findReservationsBySeanceId(seanceId).stream()
                .sorted()
                .collect(Collectors.toList());

        activeSeats.removeAll(reservedSeats);

        model.addAttribute("allSeats", allSeats);
        model.addAttribute("activeSeats", activeSeats);
        model.addAttribute("reservedSeats", reservedSeats);
        model.addAttribute("reservation", reservation);
        return "/admin/reservation/create-reservation";
    }

    @PostMapping("/reservation/create")
    public String processingReservation(@Valid Reservation reservation,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    "Musisz wybrać użytkownika i co najmniej jedno miejsce.");
            return "redirect:/admin/reservation/create?seanceId=" + reservation.getSeance().getId();
        }
        reservationService.save(reservation);
        return "redirect:/admin/seances/all";
    }


    @GetMapping("reservation/all")
    public String showReservations() {
        return "/admin/reservation/all-reservations";
    }


    @ModelAttribute("allActiveSeances")
    private List<Seance> allActiveSeances() {
        return seanceService.findAllActiveSeances();
    }

    @ModelAttribute("allReservations")
    private List<Reservation> allReservations() {
        return reservationService.findAll();
    }

    @ModelAttribute("users")
    private List<User> allUsers() {
        return userService.findAll();
    }


}
