package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.petersen.cinemamanager.entity.TicketType;
import pl.petersen.cinemamanager.exception.MovieNotFoundException;
import pl.petersen.cinemamanager.service.TicketTypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/ticket-types")
public class TicketTypeController {


    private final TicketTypeService ticketTypeService;

    @Autowired
    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @GetMapping("/add")
    public String addTicketTypeForm(Model model, Long ticketTypeId) {
        TicketType ticketType;
        if (ticketTypeId != null) {
            ticketType = ticketTypeService.findById(ticketTypeId).orElseThrow(MovieNotFoundException::new);
        } else {
            ticketType = new TicketType();
        }
        model.addAttribute("ticketType", ticketType);
        return "/admin/ticket/add-ticket-type";
    }

    @PostMapping("/add")
    public String processingTicketType(@Valid TicketType ticketType,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/ticket/add-ticket-type";
        }
        ticketTypeService.save(ticketType);
        return "redirect:/admin/ticket-types/all";
    }

    @GetMapping("/all")
    public String showAllTicketTypes() {
        return "/admin/ticket/all-ticket-types";
    }

    @ModelAttribute("ticketTypes")
    public List<TicketType> allTicketTypes() {
        return ticketTypeService.findAll();
    }

    @PostMapping("/delete")
    public String deleteTicketType(Long deleteId) {
        ticketTypeService.deleteById(deleteId);
        return "redirect:/admin/ticket-types/all";
    }


}
