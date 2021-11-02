package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.User;
import pl.petersen.cinemamanager.service.ReservationService;
import pl.petersen.cinemamanager.service.SeanceService;
import pl.petersen.cinemamanager.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ReservationController {

    private final ReservationService reservationService;
    private final SeanceService seanceService;
    private final UserService userService;

    @Autowired
    public ReservationController(ReservationService reservationService,
                                 SeanceService seanceService,
                                 UserService userService) {
        this.reservationService = reservationService;
        this.seanceService = seanceService;
        this.userService = userService;
    }

    @GetMapping("/reservation/create")
    public String createReservationForm(@RequestParam(required = false) Long seanceId, Model model) {
        if (seanceId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Seance seance = seanceService.findById(seanceId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Reservation reservation = new Reservation();
        reservation.setSeance(seance);

        model.addAttribute("allSeats", seanceService.sortSeats(reservation.getSeance()));
        model.addAttribute("activeSeats", seanceService.findAllAvailableSeatsBySeanceId(seanceId));
        model.addAttribute("reservedSeats", seanceService.findAllReservedSeatsBySeanceId(seanceId));
        model.addAttribute("reservation", reservation);
        return "admin/reservation/create-reservation";
    }


    @PostMapping("/reservation/create")
    public String processingReservation(@Valid Reservation reservation,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes) {
        if (reservationService.checkIfAlreadyReserved(reservation)) {
            redirectAttributes.addFlashAttribute("error",
                    "To miejsce jest już zajęte.");
            return "redirect:/admin/reservation/create?seanceId=" + reservation.getSeance().getId();

        }
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    "Musisz wybrać użytkownika i co najmniej jedno miejsce.");
            return "redirect:/admin/reservation/create?seanceId=" + reservation.getSeance().getId();
        }
        reservationService.save(reservation);
        return "redirect:/admin/seances/all";
    }

    @PostMapping("/reservation/delete")
    public String deleteReservation(Long deleteId) {
        reservationService.deleteById(deleteId);
        return "redirect:/admin/reservation/all";
    }

    @GetMapping("reservation/all")
    public String showReservations() {
        return "/admin/reservation/all-reservations";
    }


    @ModelAttribute("allReservations")
    private List<Reservation> allReservations() {
        return reservationService.findAllOrderByDate();
    }

    @ModelAttribute("users")
    private List<User> allUsers() {
        return userService.findAll();
    }



}
