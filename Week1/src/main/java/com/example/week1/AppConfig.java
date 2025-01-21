package com.example.week1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton") // Change to "prototype" for prototype scope
    public GreetingService greetingService() {
        return new GreetingService();
    }

    @Bean
    public GreetingPrinter greetingPrinter() {
        return new GreetingPrinter(greetingService());
    }

}
