package com.finalproject.assignment3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/dashboard")
    public String studentDashboard() {
        return "Welcome to Student Dashboard!";
    }
}
