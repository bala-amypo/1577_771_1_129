package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(Long userId) {
        return "test-token-" + userId;
    }

    public Long getUserIdFromToken(String token) {
        if (token == null) return null;
        return Long.parseLong(token.replace("test-token-", ""));
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("test-token-");
    }
}
