package com.dsms.app.controller;

import com.dsms.app.constants.UserType;
import com.dsms.app.service.AdminService;
import com.dsms.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
