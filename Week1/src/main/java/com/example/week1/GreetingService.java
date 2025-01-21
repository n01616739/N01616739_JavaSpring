package com.example.week1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class GreetingService {
    public String getGreeting() {
        return "Hello, Spring Beans!";
    }

    @PostConstruct
    public void init() {
        System.out.println("GreetingService initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("GreetingService destroyed.");
    }
}
