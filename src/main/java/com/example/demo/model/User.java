package com.example.demo.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    // 🔹 RESPONSE ONLY — Swagger will NOT ask input
    @Transient
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String token;

    public User() {}

    // getters & setters (same as before)
}
