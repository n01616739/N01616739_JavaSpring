package com.example.week1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Week1Application {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            GreetingService greetingService1 = context.getBean(GreetingService.class);
            GreetingService greetingService2 = context.getBean(GreetingService.class);

            System.out.println("Are beans the same instance? " + (greetingService1 == greetingService2));

            GreetingPrinter greetingPrinter = context.getBean(GreetingPrinter.class);
            greetingPrinter.printGreeting();
        }
    }
}
