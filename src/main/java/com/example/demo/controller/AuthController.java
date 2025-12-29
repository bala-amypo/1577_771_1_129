package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User savedUser = service.register(user);
        return ResponseEntity.ok(savedUser);
    }

    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        
        User loggedInUser = service.login(user);
        return ResponseEntity.ok(loggedInUser);
    }
}
