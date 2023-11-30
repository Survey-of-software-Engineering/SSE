package com.dsms.app.controller;

import com.dsms.app.constants.Country;
import com.dsms.app.constants.State;
import com.dsms.app.constants.UserType;
import com.dsms.app.entity.Address;
import com.dsms.app.entity.CreditCard;
import com.dsms.app.entity.User;
import com.dsms.app.models.RegisterUser;
import com.dsms.app.repository.UserRepository;
import com.dsms.app.service.AdminService;
import com.dsms.app.service.AppService;
import com.dsms.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthService authService;

    @GetMapping("/login/")
    public String login(Model model) {
        model.addAttribute("type", UserType.valueOf("USER"));
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("register", new RegisterUser());
        model.addAttribute("states", State.getStates());
        model.addAttribute("countries", Country.getCountries());
        return "register";
    }
    @PostMapping("/register_acct")
    public String registerUser(RegisterUser user) {

        User new_user = user.getUser();
        String pass = passwordEncoder.encode(new_user.getUserPassword());
        new_user.setUserPassword(pass);
        Set<String> roles = new HashSet<>();
        roles.add("USER"); // Assuming all registered users have the "USER" role
        new_user.setRoles(roles);
        authService.registerUser(user);
        return "redirect:/login/";
    }

    @GetMapping("/logout/")
    public String logoutUser() {
        return "redirect:/login/";
    }
}
