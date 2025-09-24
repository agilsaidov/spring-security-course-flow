package com.projects.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @GetMapping("/notifications")
    public String notifications() {
        return "Hello World";
    }
}
