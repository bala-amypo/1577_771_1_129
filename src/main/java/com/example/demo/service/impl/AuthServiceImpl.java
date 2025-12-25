package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;

    public AuthServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User register(User user) {

        repository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        return repository.save(user);
    }

    @Override
    public User login(User user) {

        User existing = repository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (!existing.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return existing;
    }
}
