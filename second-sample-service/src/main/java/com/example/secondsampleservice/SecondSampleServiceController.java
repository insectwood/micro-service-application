package com.example.secondsampleservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-sample-service")
@Slf4j
public class SecondSampleServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to second sample service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "second service";
    }

    @GetMapping("/check")
    public String check() {
        return "second service check check";
    }
}
