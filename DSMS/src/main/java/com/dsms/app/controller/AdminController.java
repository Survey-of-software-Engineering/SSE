package com.dsms.app.controller;

import com.dsms.app.constants.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @GetMapping("/")
    public String adminLogin(Model model) {
        return "admin/dashboard";
    }

    @GetMapping("/departments/add/")
    public String addDepartment(Model model) {
        return "admin/add_department";
    }

    @GetMapping("/categories/add/")
    public String addCategory(Model model) {
        return "admin/add_category";
    }

    @GetMapping("/items/add/")
    public String addItem(Model model) {
        return "admin/add_item";
    }

    @GetMapping("/departments/view/")
    public String view_departments(Model model) {
        return "admin/view_departments";
    }
}
