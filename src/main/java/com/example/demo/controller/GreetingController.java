package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/hi")
    public String sayHi() {
        return "Hi, welcome to the Spring Boot application!";
    }
}
