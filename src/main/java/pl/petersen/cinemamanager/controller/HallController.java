package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import pl.petersen.cinemamanager.entity.Hall;
import pl.petersen.cinemamanager.entity.Seat;
import pl.petersen.cinemamanager.service.HallService;

import javax.validation.Valid;
import java.util.LinkedHashSet;
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
    public String addNewHall(@RequestParam(required = false) Long hallId, Model model) {
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
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes) {
        if (hallService.doesHallContainAnyReservations(hall)) {
            redirectAttributes.addFlashAttribute("error",
                    "nie można zmniejszyć liczby miejsc, ponieważ w tej sali są aktywne rezerwacje.");
            return "redirect:/admin/hall/add?hallId=" + hall.getId();
        }
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
    public String deleteHall(Long deleteId, RedirectAttributes redirectAttributes) {
        if (!hallService.deleteById(deleteId)) {
            redirectAttributes.addFlashAttribute("error", "Nie można usunąć, ponieważ sala jest obecnie używana");
        }
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
