// package com.example.demo.service.impl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.AuthService;

// @Service
// public class AuthServiceImpl implements AuthService {

//     @Autowired
//     UserRepository userRepository;

//     @Override
//     public AuthResponse register(RegisterRequest request) {
//         User user = new User();
//         user.setEmail(request.getEmail());
//         user.setPassword(request.getPassword());
//         user.setRole("USER");
//         userRepository.save(user);

//         return new AuthResponse("User registered successfully");
//     }

//     @Override
//     public AuthResponse login(AuthRequest request) {
//         return new AuthResponse("Login successful");
//     }
// }