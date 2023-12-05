package com.dsms.app.controller;

import com.dsms.app.constants.Country;
import com.dsms.app.constants.State;
import com.dsms.app.constants.UserType;
import com.dsms.app.entity.Address;
import com.dsms.app.entity.CreditCard;
import com.dsms.app.entity.Order;
import com.dsms.app.entity.User;
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

import java.util.List;

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
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("user", authService.getCurrentUser());
        model.addAttribute("item", adminService.getItemById(itemId));
        return "user/item_detail";
    }

    @GetMapping("/profile/view/")
    public String profile(Model model) {
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("user", authService.getCurrentUser());
        return "user/view_profile";
    }

    @GetMapping("/cart/")
    public String cart(Model model) {
        model.addAttribute("user", authService.getCurrentUser());
        model.addAttribute("checkout", new Checkout());
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        return "user/shopping_cart";
    }

    @PostMapping("/checkout/")
    public String checkout(Model model, @ModelAttribute("checkout") Checkout checkout) {

        System.out.print("Coupon Code : " + checkout.getCouponCode());
        model.addAttribute("checkout", new Checkout(checkout.getPickupType(), checkout.getCouponCode(), checkout.getTotal()));
        model.addAttribute("placeOrder", new PlaceOrder("", new User(), new Address(), new CreditCard(), "", new Checkout()));
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("currentUser", authService.getCurrentUser());
        model.addAttribute("states", State.getStates());
        model.addAttribute("countries", Country.getCountries());
        return "user/checkout";
    }

    @PostMapping("/placeOrder/")
    public String placeOrder(Model model, @ModelAttribute("placeOrder") PlaceOrder placeOrder) {

        Order order = appService.placeOrder(placeOrder);
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("user", authService.getCurrentUser());
        model.addAttribute("orderId", order.getId());
        return "user/thankyou";
    }

    @GetMapping("/myOrders/")
    public String myOrders(Model model) {
        List<Order> orders = appService.getOrders(authService.getCurrentUser());
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("user", authService.getCurrentUser());
        model.addAttribute("orders", orders);
        return "user/myorders";
    }

    @GetMapping("/order_detail/{orderId}/")
    public String myOrders(Model model, @PathVariable("orderId") String order_id) {
        Order order = appService.getOrderByOrderId(order_id);
        model.addAttribute("cartItems", appService.getCartItems(authService.getCurrentUser()));
        model.addAttribute("user", authService.getCurrentUser());
        model.addAttribute("order", order);
        return "user/order_detail";
    }
}