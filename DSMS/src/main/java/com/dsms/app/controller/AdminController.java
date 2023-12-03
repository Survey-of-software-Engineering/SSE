package com.dsms.app.controller;

import com.dsms.app.constants.CouponStatus;
import com.dsms.app.entity.Category;
import com.dsms.app.entity.CouponCode;
import com.dsms.app.entity.Department;
import com.dsms.app.models.CreateCategory;
import com.dsms.app.models.CreateCoupon;
import com.dsms.app.models.CreateItem;
import com.dsms.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String adminLogin(Model model) {
        model.addAttribute("departments", adminService.getDepartments().size());
        model.addAttribute("categories", adminService.getCategories().size());
        model.addAttribute("items", adminService.getItems().size());
        model.addAttribute("orders", adminService.getOrders().size());
        model.addAttribute("coupons", adminService.getCoupons().size());
        return "admin/dashboard";
    }

    @GetMapping("/departments/add/")
    public String addDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "admin/add_department";
    }

    @PostMapping("/add_department/")
    public String add_department(Department department) {
        System.out.println(department);
        adminService.addDepartment(department);
        return "redirect:/admin/departments/";
    }

    @GetMapping("/departments/")
    public String departments(Model model) {
        model.addAttribute("departments", adminService.getDepartments());
        return "admin/departments";
    }


    @GetMapping("/categories/add/")
    public String addCategory(Model model) {
        CreateCategory category = new CreateCategory();
        category.setCategory(new Category());
        model.addAttribute("category", category);
        model.addAttribute("departments", adminService.getDepartments());
        return "admin/add_category";
    }

    @GetMapping("/categories/")
    public String categories(Model model) {
        model.addAttribute("categories", adminService.getCategories());
        return "admin/categories";
    }

    @PostMapping("/add_category/")
    public String add_category(CreateCategory createCategory) {
        adminService.addCategory(createCategory);
        return "redirect:/admin/categories/";
    }

    @GetMapping("/items/add/")
    public String addItem(Model model) {
        model.addAttribute("departments", adminService.getDepartments());
        model.addAttribute("item", new CreateItem());
        return "admin/add_item";
    }

    @GetMapping("/items/")
    public String items(Model model) {
        model.addAttribute("items", adminService.getItems());
        return "admin/items";
    }

    @PostMapping("/add_item/")
    public String add_item(CreateItem createItem) {
        adminService.addItem(createItem);
        return "redirect:/admin/items/";
    }

    @GetMapping("/coupons/add/")
    public String addCoupon(Model model) {
        CreateCoupon coupon = new CreateCoupon();
        coupon.setCouponCode(new CouponCode());
        model.addAttribute("coupon", coupon);
        return "admin/add_coupon";
    }

    @PostMapping("/add_coupon/")
    public String add_promocode(CreateCoupon couponCode) {
        CouponCode coupon = couponCode.getCouponCode();
        LocalDate localDate = LocalDate.parse(couponCode.getValidity().split("T")[0]);
        Instant instant = localDate.atStartOfDay(ZoneOffset.UTC).toInstant();
        coupon.setExpiryDate(instant);
        coupon.setCreatedTime(Instant.now());
        coupon.setUpdatedTime(Instant.now());
        coupon.setStatus(CouponStatus.ACTIVE);
        adminService.createCouponCode(coupon);
        return "redirect:/admin/coupons/";
    }

    @GetMapping("/coupons/")
    public String coupons(Model model) {
        model.addAttribute("coupons", adminService.getCoupons());
        return "admin/coupons";
    }

}
