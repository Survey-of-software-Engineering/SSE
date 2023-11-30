package com.dsms.app.controller;

import com.dsms.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/")
public class AppRestController {

    @Autowired
    AppService appService;

    @PostMapping("/cart/add/")
    public ResponseEntity addItemToCart() {
        return ResponseEntity.ok(new Object());
    }
}
