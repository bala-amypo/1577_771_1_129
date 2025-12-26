package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    // token validity: 1 hour
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    // In-memory token store (for demo / assignment)
    private final Map<String, TokenData> tokenStore = new HashMap<>();

    // ✅ GENERATE TOKEN
    public String generateToken(Long userId, String email) {

        String token = UUID.randomUUID().toString();

        TokenData data = new TokenData(
                userId,
                email,
                System.currentTimeMillis() + EXPIRATION_TIME
        );

        tokenStore.put(token, data);
        return token;
    }

    // ✅ VALIDATE TOKEN
    public boolean validateToken(String token) {
        TokenData data = tokenStore.get(token);

        if (data == null) {
            return false;
        }
        return System.currentTimeMillis() < data.expiryTime;
    }

    // ✅ GET USER ID FROM TOKEN
    public Long getUserId(String token) {
        return tokenStore.get(token).userId;
    }

    // ✅ GET EMAIL FROM TOKEN
    public String getEmail(String token) {
        return tokenStore.get(token).email;
    }

    // ===== Helper inner class =====
    private static class TokenData {
        Long userId;
        String email;
        long expiryTime;

        TokenData(Long userId, String email, long expiryTime) {
            this.userId = userId;
            this.email = email;
            this.expiryTime = expiryTime;
        }
    }
}
