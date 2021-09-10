package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.petersen.cinemamanager.entity.Hall;
import pl.petersen.cinemamanager.entity.Movie;
import pl.petersen.cinemamanager.entity.Seance;
import pl.petersen.cinemamanager.entity.TicketType;
import pl.petersen.cinemamanager.service.HallService;
import pl.petersen.cinemamanager.service.MovieService;
import pl.petersen.cinemamanager.service.SeanceService;
import pl.petersen.cinemamanager.service.TicketTypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/seances")
public class SeanceController {

    private final SeanceService seanceService;
    private final MovieService movieService;
    private final TicketTypeService ticketTypeService;
    private final HallService hallService;

    @Autowired
    public SeanceController(SeanceService seanceService, MovieService movieService, TicketTypeService ticketTypeService, HallService hallService) {
        this.seanceService = seanceService;
        this.movieService = movieService;
        this.ticketTypeService = ticketTypeService;
        this.hallService = hallService;
    }

    @GetMapping("/add")
    public String addSeanceForm(Model model, Long seanceId) {
    Seance seance;
    if (seanceId != null) {
        seance = seanceService.findById(seanceId).orElse(new Seance());
    } else {
        seance = new Seance();
    }
    model.addAttribute("seance", seance);
    return "/admin/seance/add-seance-form";
    }

    @PostMapping("/add")
    public String processAddingForm(@Valid Seance seance,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/seance/add-seance-form";
        }
        seanceService.save(seance);
        return "redirect:/admin/seances/all";
    }

    @GetMapping("/all")
    public String showSeances() {
        return "/admin/seance/all-seance";
    }






    @ModelAttribute("seances")
    public List<Seance> allSeances() {
        return seanceService.findAllActiveSeances();
    }

    @ModelAttribute("ticketTypes")
    public List<TicketType> allTicketTypes() {
        return ticketTypeService.findAll();
    }

    @ModelAttribute("movies")
    public List<Movie> allMovies() {
        return movieService.findAllOrderByNewest();
    }

    @ModelAttribute("halls")
    public List<Hall> allHalls() {
        return hallService.findAll();
    }

}
