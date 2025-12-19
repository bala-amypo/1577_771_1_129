package com.example.demo.model;
import java.util.*;
import com.example.demo.model.CartItem;
import com.example.demo.model.BundleRule;
import java.sql.Timestamp;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean active=true;
    @OneToMany(mappedby="cart")
    public List<CartItem> lis1=new ArrayList<>();
    @OneToMany(mappedby="cart")
    public List<DiscountApplication>lis2=new ArrayList<>();
    public Cart(){}
    public Cart(Long userId, Timestamp createdAt, Timestamp updatedAt,Boolean active) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active=active;
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
    
    public void setActive(Boolean active) {
        this.active = active;
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
    public Boolean getActive() {
        return active;
    }

    

    
}
