package com.dsms.app.controller;

import com.dsms.app.constants.UserType;
import com.dsms.app.service.AdminService;
import com.dsms.app.service.AppService;
import com.dsms.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/")
public class AppController {

    @Autowired
    AdminService adminService;

    @Autowired
    AppService appService;

    @Autowired
    AuthService authService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("departments", appService.getDepartmentsResponse());
        return "user/index";
    }

    @GetMapping("/item_detail/{id}/")
    public String home(Model model, @PathVariable("id") String itemId) {
        model.addAttribute("item", adminService.getItemById(itemId));
        return "user/item_detail";
    }

    @GetMapping("/profile/view/")
    public String profile(Model model) {

        model.addAttribute("user", authService.getCurrentUser());
        return "user/view_profile";
    }
}
