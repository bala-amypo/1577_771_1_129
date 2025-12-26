package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------- REGISTER ----------------
    @Override
    public User register(User user) {

        // Check if email exists
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);

        // Generate JWT token (role should be set in user)
        String token = jwtUtil.generateToken(
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getId()
        );
        savedUser.setToken(token);

        return savedUser;
    }

    // ---------------- LOGIN ----------------
    @Override
    public User login(User user) {

        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

        // Validate password using PasswordEncoder
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(
                existingUser.getEmail(),
                existingUser.getRole(),
                existingUser.getId()
        );
        existingUser.setToken(token);

        return existingUser;
    }
}
