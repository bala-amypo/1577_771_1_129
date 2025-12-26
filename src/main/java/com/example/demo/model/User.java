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

    /**
     * Token is auto-generated and returned in response only.
     * Swagger will NOT ask this as input.
     */
    @Transient
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String token;

    // ================= CONSTRUCTORS =================

    // No-args constructor (required by JPA)
    public User() {
    }

    // All-args constructor (without token)
    public User(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Password (input only)
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    // Role (optional)
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    // ================= TOKEN =================

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
