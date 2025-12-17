package com.example.E_commerce.model;

public class BundleRule {
    private Long id;
    private String ruleName;
    private String requiredProductIds;
    private Double discountPercentage;
    private Boolean active;
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
