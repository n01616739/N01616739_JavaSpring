package com.example.week1;

import org.springframework.beans.factory.annotation.Autowired;

public class GreetingPrinter {

    private final GreetingService greetingService;

    @Autowired
    public GreetingPrinter(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void printGreeting() {
        System.out.println(greetingService.getGreeting());
    }
}
