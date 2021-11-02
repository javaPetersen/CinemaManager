package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.petersen.cinemamanager.entity.Reservation;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.User;
import pl.petersen.cinemamanager.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserPanelController {

    private final UserService userService;

    @Autowired
    public UserPanelController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String userPanel() {
        return "user/dashboard";
    }


    @GetMapping("/reservations")
    public String userReservations(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("reservations", user.getReservation());
        return "user/reservation/reservations";
    }

    @PostMapping("/reservation/delete")
    public String deleteReservation(Long deleteId) {
        userService.deleteReservation(deleteId);
        return "redirect:/user/reservations";
    }

    @GetMapping("/seances/all")
    public String allActiveSeances(Model model) {
        model.addAttribute("seances", userService.getActiveSeances());
        return "/user/seance/all-seances";
    }

    @GetMapping("/reservation/create")
    public String createReservationForm(@RequestParam(required = false) Long seanceId, Model model, Principal principal) {
        if (seanceId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User user = userService.findByEmail(principal.getName());
        Seance seance = userService.findSeanceById(seanceId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Reservation reservation = new Reservation();
        reservation.setSeance(seance);
        reservation.setUser(user);

        model.addAttribute("allSeats", userService.sortSeats(reservation.getSeance()));
        model.addAttribute("activeSeats", userService.findAllActiveSeatsBySeanceId(seanceId));
        model.addAttribute("reservedSeats", userService.findAllReservedSeatsBySeanceId(seanceId));
        model.addAttribute("reservation", reservation);
        return "user/reservation/create-reservation";
    }


    @PostMapping("/reservation/create")
    public String processingReservation(@Valid Reservation reservation,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes) {
        if (userService.checkIfAlreadyReserved(reservation)) {
            redirectAttributes.addFlashAttribute("error",
                    "To miejsce jest już zajęte.");
            return "redirect:/user/reservation/create?seanceId=" + reservation.getSeance().getId();

        }
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error",
                    "Musisz wybrać użytkownika i co najmniej jedno miejsce.");
            return "redirect:/user/reservation/create?seanceId=" + reservation.getSeance().getId();
        }
        userService.saveReservation(reservation);
        return "redirect:/user";
    }




}
