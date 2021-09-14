package pl.petersen.cinemamanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import pl.petersen.cinemamanager.entity.*;
import pl.petersen.cinemamanager.service.ReservationService;
import pl.petersen.cinemamanager.service.SeanceService;
import pl.petersen.cinemamanager.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.http.HttpRequest;
import java.util.*;
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

    @GetMapping("/seances/reservation")
    public String reservations(Long reservationId, Model model,
                               HttpServletRequest request) {
        Seance seance = seanceService.findById(reservationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Seat> reservations = reservationService.findReservationsBySeanceId(reservationId);

        seance.getHall().setSeats(seance.getHall().getSeats().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)));
        List<Seat> availableSeats = new ArrayList<>(seance.getHall().getSeats());
        availableSeats.removeAll(reservations);
        System.out.println(request.getUserPrincipal().getName());

        Reservation reservation = new Reservation();
        reservation.setSeance(seance);
        model.addAttribute("reservation", reservation);
        model.addAttribute("reservations", reservations);
        model.addAttribute("seances", seance);
        model.addAttribute("available", availableSeats);
        return "/admin/seance/seance-reservation";
    }


    @GetMapping("reservation/all")
    public String showReservations() {
        return "/admin/reservation/all-reservations";
    }


    @ModelAttribute("allReservations")
    private List<Reservation> allReservations() {
        return reservationService.findAll();
    }

    @ModelAttribute("users")
    private List<User> allUsers() {
        return userService.findAll();
    }

    @PostMapping("/seances/reservation")
    public String processingReservation(@Valid Reservation reservation,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/admin/seances/reservation?reservationId="+reservation.getSeance().getId();
        }
        reservationService.save(reservation);
        return "redirect:/admin/seances/all";
    }


}
