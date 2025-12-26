package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    private static final long EXPIRY_TIME = 60 * 60 * 1000; // 1 hour

    private final Map<String, TokenData> tokenStore = new HashMap<>();

    // ✅ AUTO-GENERATE TOKEN
    public String generateToken(Long userId, String email) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token,
                new TokenData(userId, email,
                        System.currentTimeMillis() + EXPIRY_TIME));
        return token;
    }

    public boolean validateToken(String token) {
        TokenData data = tokenStore.get(token);
        return data != null && System.currentTimeMillis() < data.expiry;
    }

    public Long getUserId(String token) {
        return tokenStore.get(token).userId;
    }

    public String getEmail(String token) {
        return tokenStore.get(token).email;
    }

    // ----- Inner class -----
    private static class TokenData {
        Long userId;
        String email;
        long expiry;

        TokenData(Long userId, String email, long expiry) {
            this.userId = userId;
            this.email = email;
            this.expiry = expiry;
        }
    }
}
