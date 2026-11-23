package com.example.springboot_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping()
    public String home(){
        System.out.println("called");
        return "Welcome to our would";
    }

}
