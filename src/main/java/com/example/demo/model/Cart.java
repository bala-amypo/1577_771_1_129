package com.example.E_commerce.model;

import java.sql.Timestamp;

public class Cart {
    private Long id;
    private Long userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    public Cart(){}
    public Cart(Long userId, Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    

    
}
