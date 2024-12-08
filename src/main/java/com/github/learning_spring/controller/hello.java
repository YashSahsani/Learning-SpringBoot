package com.github.learning_spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class hello {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String hello() {
        return message;
    }
}
