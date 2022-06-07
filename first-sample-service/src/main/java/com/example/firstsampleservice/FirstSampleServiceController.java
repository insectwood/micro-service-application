package com.example.firstsampleservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-sample-service")
@Slf4j
public class FirstSampleServiceController {
    Environment env;
    @Autowired
    public FirstSampleServiceController(Environment env) {
        this.env = env;
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to fisrt sample service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "first service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        return String.format("Hello, this is a check from first sample service on Port %s", env.getProperty("local.server.port"));
    }
}
