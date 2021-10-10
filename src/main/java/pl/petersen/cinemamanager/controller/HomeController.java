package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.petersen.cinemamanager.entity.User;
import pl.petersen.cinemamanager.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;


    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String processingRegisterForm(@Valid User user,
                                         BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.checkEmail(user)) {
            result.rejectValue("email", "user.email",
                    "An account already exists for this email.");
            return "register";
        }
        if (!userService.checkPasswordMatch(user)) {
            result.rejectValue("password", "user.password",
                    "passwords don't match");
            return "register";
        }
        userService.save(user);
        return "redirect:login";
    }

}
