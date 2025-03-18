package com.finalproject.assignment3.service;

import com.finalproject.assignment3.entity.User;
import com.finalproject.assignment3.repository.UserRepository;
import com.finalproject.assignment3.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = JwtUtil.generateToken(user.getUsername(), user.getRole().name());

                // Redirect based on role
                if (user.getRole().name().equals("ADMIN")) {
                    return "/admin/dashboard?token=" + token;
                } else {
                    return "/student/dashboard?token=" + token;
                }
            }
        }
        throw new RuntimeException("Invalid username or password");
    }
}