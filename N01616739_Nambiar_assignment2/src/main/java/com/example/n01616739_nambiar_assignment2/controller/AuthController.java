package com.example.n01616739_nambiar_assignment2.controller;

import com.example.n01616739_nambiar_assignment2.model.Student;
import com.example.n01616739_nambiar_assignment2.model.User;
import com.example.n01616739_nambiar_assignment2.repository.UserRepository;
import com.example.n01616739_nambiar_assignment2.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                logger.info("User '{}' logged in successfully with role: {}", username, user.get().getRole());

                if (user.get().getRole().equals("STUDENT")) {
                    Optional<Student> studentOpt = studentRepository.findByEmail(user.get().getUsername());

                    if (studentOpt.isPresent()) {
                        Long studentId = studentOpt.get().getId();
                        return "redirect:/student/dashboard?id=" + studentId;
                    } else {
                        logger.warn("Student record not found for user '{}'", username);
                        model.addAttribute("error", "Student record not found.");
                        return "login";
                    }
                } else {
                    return "redirect:/admin/dashboard";
                }
            } else {
                logger.warn("Failed login attempt for user '{}'", username);
            }
        } else {
            logger.warn("Login failed - user '{}' not found", username);
        }

        model.addAttribute("error", "Invalid Credentials");
        return "login";
    }
}
