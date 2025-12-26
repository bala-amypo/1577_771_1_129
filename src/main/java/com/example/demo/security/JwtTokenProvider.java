package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // Dummy token validation (NO real JWT)
    public boolean validateToken(String token) {
        return token != null && !token.isEmpty();
    }

    // Optional helpers (safe for future use)
    public String getEmail(String token) {
        return "test@example.com";
    }

    public String getRole(String token) {
        return "USER";
    }
}
