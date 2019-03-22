package pl.mkpk.newhorizon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mkpk.newhorizon.model.User;
import pl.mkpk.newhorizon.repository.RoleRepository;
import pl.mkpk.newhorizon.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class LoginController {
    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    // obsługa żądzania przekierowania na stronę formularza
    @GetMapping("/register")
    public String registerUser(Model model) {
        User user = new User();
        // dodajemy atrybut dla obiektu klasy Model ("nazwa stosowanana w html", nazwa bjektu Java)
        model.addAttribute("user", user);
        return "register";  // przekierowanie na widok formularza rejestracji (html)
    }

    // obsługa żądzania przekazania obiektu z formularz metodą post
    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        // zapis do DB przez serwis użytkownika
        user.setActive(1);
        user.setRoles(Arrays.asList(roleRepository.findByName("USER")));
        userService.registerUser(user);
        return "redirect:login";
    }
}
