package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // -------- REGISTER --------
    @Override
    public User register(User user) {

        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        User savedUser = userRepository.save(user);

        // ✅ AUTO TOKEN
        String token = jwtUtil.generateToken(savedUser.getId(), savedUser.getEmail());
        savedUser.setToken(token);

        return savedUser;
    }

    // -------- LOGIN --------
    @Override
    public User login(User user) {

        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

        if (!existingUser.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // ✅ AUTO TOKEN
        String token = jwtUtil.generateToken(existingUser.getId(), existingUser.getEmail());
        existingUser.setToken(token);

        return existingUser;
    }
}
