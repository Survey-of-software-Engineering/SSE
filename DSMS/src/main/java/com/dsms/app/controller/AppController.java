package com.dsms.app.controller;

import com.dsms.app.constants.UserType;
import com.dsms.app.models.Checkout;
import com.dsms.app.models.PlaceOrder;
import com.dsms.app.service.AdminService;
import com.dsms.app.service.AppService;
import com.dsms.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("cartItemIds", appService.getCartItemsIds(authService.getCurrentUser()));
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("departments", appService.getDepartmentsResponse());
        model.addAttribute("user", authService.getCurrentUser());
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

    @GetMapping("/cart/")
    public String cart(Model model) {
        model.addAttribute("checkout", new Checkout());
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        return "user/shopping_cart";
    }

    @PostMapping("/cart_checkout/")
    public String cart_checkout(Checkout checkout, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("coupon", checkout.getCouponCode());
        redirectAttributes.addAttribute("pickup", checkout.getPickupType());
        return "redirect:/app/checkout/";
    }

    @GetMapping("/checkout/")
    public String checkout(@ModelAttribute("coupon") String coupon, @ModelAttribute("pickup") String pickup, Model model) {

        model.addAttribute("placeOrder", new PlaceOrder());
        model.addAttribute("coupon", coupon);
        model.addAttribute("pickup", pickup);
        model.addAttribute("coupon", coupon);
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("user", authService.getCurrentUser());
        return "user/checkout";
    }
}
