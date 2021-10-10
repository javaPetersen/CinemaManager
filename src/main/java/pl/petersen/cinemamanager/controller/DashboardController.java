package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.petersen.cinemamanager.service.ReservationService;
import pl.petersen.cinemamanager.service.SeanceService;
import pl.petersen.cinemamanager.service.UserService;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private final SeanceService seanceService;
    private final ReservationService reservationService;
    private final UserService userService;

    @Autowired
    public DashboardController(SeanceService seanceService, ReservationService reservationService, UserService userService) {
        this.seanceService = seanceService;
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping("")
    public String showDashboard() {
        return "/admin/dashboard";
    }

    @ModelAttribute("userCount")
    public long userCount() {
        return userService.countAllUsers();
    }

    @ModelAttribute("seanceCount")
    public long seanceCount() {
        return seanceService.countAllSeances();
    }

    @ModelAttribute("reservationsCount")
    public long reservationsCount() {
        return reservationService.countAllReservations();
    }

}
