package com.dsms.app.controller;

import com.dsms.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthRestController {

    @Autowired
    private AuthService authService;

    @PostMapping("/guest_user/")
    public ResponseEntity createGuestAccount() {
        return ResponseEntity.ok(authService.newGuestUser());
    }
}
