package com.finalproject.assignment3.controller;
import com.finalproject.assignment3.entity.Role;
import com.finalproject.assignment3.entity.User;
import com.finalproject.assignment3.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/auth")
public class RegistrationController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        Role role = Role.valueOf(request.get("role").toUpperCase());

        if (userRepository.findByUsername(username).isPresent()) {
            return Map.of("message", "User already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);

        return Map.of("message", "User registered successfully");
    }
}