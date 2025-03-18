package com.finalproject.assignment3.controller;

import com.finalproject.assignment3.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String redirectUrl = authService.login(request.get("username"), request.get("password"));
        return Map.of("redirect_url", redirectUrl);
    }
}
