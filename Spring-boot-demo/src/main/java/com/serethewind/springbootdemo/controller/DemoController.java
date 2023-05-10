package com.serethewind.springbootdemo.controller;

import com.serethewind.springbootdemo.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/greeting")
public class DemoController {


    @GetMapping
    public String greeting() {
        return "Hello guys";
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest user) {
        String userName = "iacademy";
        String password = "123456789";
        String response;
        if (user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equalsIgnoreCase(password)) {
            response = "You are successfully welcome";
            return ResponseEntity.ok(response);
        } else {
            response = "Login failed";
            return ResponseEntity.badRequest().body(response);
        }
    }
}
