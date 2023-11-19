package com.dsms.app.controller;

import com.dsms.app.constants.UserType;
import com.dsms.app.entity.User;
import com.dsms.app.repository.UserRepository;
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

    @GetMapping("/login/")
    public String login(Model model) {
        model.addAttribute("type", UserType.valueOf("USER"));
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register_acct")
    public String registerUser(User user) {

        String pass = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(pass);
        Set<String> roles = new HashSet<>();
        roles.add("USER"); // Assuming all registered users have the "USER" role
        user.setRoles(roles);
        userRepository.save(user);

        return "redirect:/login/";
    }

    @GetMapping("/logout/")
    public String logoutUser() {
        return "redirect:/login/";
    }
}
