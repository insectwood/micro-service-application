package com.example.firstsampleservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-sample-service")
@Slf4j
public class FirstSampleServiceController {

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
    public String check() {
        return "first service check check";
    }
}
