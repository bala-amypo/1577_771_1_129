package com.example.demo.model;

import java.math.BigDecimal;
import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
@Entity
public class DiscountApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @ManyToOne
    // @JoinColumn(name="cart_id")
    private Cart cart;
    // @ManyToOne
    // @JoinColumn(name="bundle_rule_id")
    // private BundleRule bundleRule;
    private BigDecimal discountAmount;
    private Timestamp appliedAt;
    public DiscountApplication(){}
    public DiscountApplication(Cart cart, BundleRule bundleRule, BigDecimal discountAmount, Timestamp appliedAt) {
        this.cart = cart;
        this.bundleRule = bundleRule;
        this.discountAmount = discountAmount;
        this.appliedAt = appliedAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public void setBundleRule(BundleRule bundleRule) {
        this.bundleRule = bundleRule;
    }
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    public void setAppliedAt(Timestamp appliedAt) {
        this.appliedAt = appliedAt;
    }
    public Long getId() {
        return id;
    }
    public Cart getCart() {
        return cart;
    }
    public BundleRule getBundleRule() {
        return bundleRule;
    }
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    public Timestamp getAppliedAt() {
        return appliedAt;
    }
    
    
}
