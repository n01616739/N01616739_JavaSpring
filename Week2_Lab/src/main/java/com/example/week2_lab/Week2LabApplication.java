package com.example.week2_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "controllers")  // Ensure controllers are scanne
@SpringBootApplication
public class Week2LabApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week2LabApplication.class, args);
    }

}
