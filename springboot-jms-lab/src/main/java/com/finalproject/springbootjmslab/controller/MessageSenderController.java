package com.finalproject.springbootjmslab.controller;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/lab")
public class MessageSenderController {
    private final JmsTemplate jmsTemplate;

    public MessageSenderController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        jmsTemplate.convertAndSend("lab-queue", message);
        return "Message sent: " + message;
    }


//    @PostMapping("/send")
//    public String sendMessage(@RequestBody Map<String, String> payload) {
//        String message = payload.get("message");
//        jmsTemplate.convertAndSend("lab-queue", message);
//        return " Message sent: " + message;
//    }
}
