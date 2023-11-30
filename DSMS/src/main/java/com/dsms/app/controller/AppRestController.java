package com.dsms.app.controller;

import com.dsms.app.entity.CartItem;
import com.dsms.app.models.AddItemToCart;
import com.dsms.app.models.RemoveItem;
import com.dsms.app.models.UpdateItemQuantity;
import com.dsms.app.models.ValidateCoupon;
import com.dsms.app.service.AppService;
import com.dsms.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/")
public class AppRestController {

    @Autowired
    AppService appService;

    @Autowired
    AuthService authService;

    @PostMapping("/cart/add/")
    public ResponseEntity addItemToCart(@RequestBody AddItemToCart cartItem) {
        CartItem cartItem1 = appService.addItemToShoppingCart(cartItem, authService.getCurrentUser());
        return ResponseEntity.ok(cartItem1);
    }

    @PostMapping("/cart/remove/")
    public ResponseEntity removeItemFromCart(@RequestBody RemoveItem removeItem) {

        return ResponseEntity.ok(appService.removeItemFromCart(authService.getCurrentUser(), removeItem.getItemId()));
    }

    @PostMapping("/cart/updateItemQuantity/")
    public ResponseEntity updateItemQuantity(@RequestBody UpdateItemQuantity updateItemQuantity) {

        return ResponseEntity.ok(appService.updateItemQuantity(updateItemQuantity, authService.getCurrentUser()));
    }

    @PostMapping("/cart/vaidate_coupon/")
    public ResponseEntity validateCoupon(@RequestBody ValidateCoupon coupon) {
        return ResponseEntity.ok(appService.validateCoupon(coupon));
    }
}
