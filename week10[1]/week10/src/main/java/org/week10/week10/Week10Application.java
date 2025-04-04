package org.week10.week10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "hello")
public class Week10Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Week10Application.class, args);
    }
    @RequestMapping(value = "{firstname}/{lastname}", method = RequestMethod.GET)
    public String hello(@PathVariable("firstname") String firstname,
                        @PathVariable("lastname") String lastname) {
        return String.format("{\"message\":\"Hello %s %s\"}", firstname,lastname);

    }
}
