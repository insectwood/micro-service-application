package com.example.userservice.controller;

import com.example.userservice.vo.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    private Environment env;

    @Autowired
    private Greeting greeting;

    @GetMapping("/test_check")
    public String status() {
        return "Test is Working in User service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

}
