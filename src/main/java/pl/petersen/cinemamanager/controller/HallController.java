package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.petersen.cinemamanager.entity.Hall;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.service.HallService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/hall")
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/add")
    public String addNewHall(Model model, Long hallId) {
        Hall hall;
        if (hallId != null) {
            hall = hallService.findById(hallId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            hall = new Hall();
        }
        model.addAttribute("hall", hall);
        return "/admin/hall/add-hall-form";
    }

    @PostMapping("/add")
    public String processingHallForm(@Valid Hall hall,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/hall/add-hall-form";
        }
        hallService.save(hall);
        return "redirect:/admin/hall/all";
    }

    @GetMapping("/all")
    public String showAllHalls() {
        return "admin/hall/all-halls";
    }

    @ModelAttribute("halls")
    public List<Hall> allHalls() {
        return hallService.findAll();
    }

    @PostMapping("/delete")
    public String deleteHall(Long deleteId) {
        hallService.deleteById(deleteId);
        return "redirect:/admin/hall/all";
    }

    @GetMapping("/details")
    public String hallDetails(@RequestParam Long detailsId, Model model) {
        Hall hall = hallService.findById(detailsId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Seat> seats = hall.getSeats().stream()
                .sorted()
                .collect(Collectors.toList());
        model.addAttribute("hallDetails", hall);
        model.addAttribute("seats", seats);
        return "/admin/hall/hall-details";
    }


}
