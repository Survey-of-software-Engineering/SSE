package com.dsms.app.controller;

import com.dsms.app.constants.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class AppController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("type", UserType.GUEST);
        return "index";
    }
}
