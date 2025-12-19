package com.example.demo.model;
import java.util.*;
import com.example.demo.model.

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BundleRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique=true)
    private String ruleName;
    private String requiredProductIds;
    private Double discountPercentage;
    private Boolean active=true;
    @OneToMany(mappedby="bundleRule")
    public List<DiscountApplication> lis1=new ArrayList<>();
    public BundleRule(){}
    public BundleRule(String ruleName, String requiredProductIds, Double discountPercentage, Boolean active) {
        this.ruleName = ruleName;
        this.requiredProductIds = requiredProductIds;
        this.discountPercentage = discountPercentage;
        this.active = active;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public void setRequiredProductIds(String requiredProductIds) {
        this.requiredProductIds = requiredProductIds;
    }
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Long getId() {
        return id;
    }
    public String getRuleName() {
        return ruleName;
    }
    public String getRequiredProductIds() {
        return requiredProductIds;
    }
    public Double getDiscountPercentage() {
        return discountPercentage;
    }
    public Boolean getActive() {
        return active;
    }
    

    
}
