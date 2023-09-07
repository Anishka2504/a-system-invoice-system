package com.example.greetingservice.controller;

import com.example.greetingservice.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.greetingservice.util.Constant.GREETING;

@RestController
public class GreetingController {

//    private static final String template = "Welcome, %s!";

    @RequestMapping("/greeting")
    public Greeting greet(@RequestParam(value = "name",defaultValue = "User") String name) {
        return new Greeting(GREETING.concat(name).concat("!"));
    }
}
