package com.trackit.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
public class Health {
    @GetMapping()
    String health() {
        return "OK";
    }
}
