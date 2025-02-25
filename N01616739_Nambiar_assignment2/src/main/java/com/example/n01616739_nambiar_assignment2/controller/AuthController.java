package com.example.n01616739_nambiar_assignment2.controller;

import com.example.n01616739_nambiar_assignment2.model.Student;
import com.example.n01616739_nambiar_assignment2.model.User;
import com.example.n01616739_nambiar_assignment2.repository.UserRepository;
import com.example.n01616739_nambiar_assignment2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository StudentRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login?logout";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            if (user.get().getRole().equals("STUDENT")) {
                Optional<Student> studentOpt = StudentRepository.findByEmail(user.get().getUsername());

                if (studentOpt.isPresent()) {
                    Long studentId = studentOpt.get().getId();
                    System.out.println("Redirecting student with ID: " + studentId); // Debugging Log
                    return "redirect:/student/dashboard?id=" + studentId;
                } else {
                    System.out.println("Student record not found for user: " + user.get().getUsername());
                    model.addAttribute("error", "Student record not found in the database.");
                    return "login";
                }
            } else {
                return "redirect:/admin/dashboard";
            }
        }

        model.addAttribute("error", "Invalid Credentials");
        return "login";
    }



}