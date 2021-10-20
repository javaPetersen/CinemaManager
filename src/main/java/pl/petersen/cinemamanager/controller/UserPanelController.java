package pl.petersen.cinemamanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserPanelController {




    @GetMapping("/hello")
    public String testHello() {
        return "user/dashboard";
    }
}
