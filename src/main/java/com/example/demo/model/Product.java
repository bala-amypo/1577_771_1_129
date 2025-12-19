// package com.example.demo.model;

// import java.math.BigDecimal;
// import java.sql.Timestamp;
// import java.util.*;
// import com.example.demo.model.CartItem;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.validation.constraints.Positive;
// @Entity
// public class Product {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     @Column(nullable = false ,unique = true)
//     private String sku;
//     private String name;
//     private String category;
//     @Positive(message="Price must be greater than o")
//     private BigDecimal price;
//     private Boolean active=true;
//     private Timestamp createdAt;
//     @OneToMany(mappedBy="product")
//     public List<CartItem> lis1=new ArrayList<>();
//     public Product(){}
//     public Product(String sku, String name, String category, BigDecimal price, Boolean active, Timestamp createdAt) {
//         this.sku = sku;
//         this.name = name;
//         this.category = category;
//         this.price = price;
//         this.active = active;
//         this.createdAt = createdAt;
//     }
//     public void setId(Long id) {
//         this.id = id;
//     }
//     public void setSku(String sku) {
//         this.sku = sku;
//     }
//     public void setName(String name) {
//         this.name = name;
//     }
//     public void setCategory(String category) {
//         this.category = category;
//     }
//     public void setPrice(BigDecimal price) {
//         this.price = price;
//     }
//     public void setActive(Boolean active) {
//         this.active = active;
//     }
//     public void setCreatedAt(Timestamp createdAt) {
//         this.createdAt = createdAt;
//     }
//     public Long getId() {
//         return id;
//     }
//     public String getSku() {
//         return sku;
//     }
//     public String getName() {
//         return name;
//     }
//     public String getCategory() {
//         return category;
//     }
//     public BigDecimal getPrice() {
//         return price;
//     }
//     public Boolean getActive() {
//         return active;
//     }
//     public Timestamp getCreatedAt() {
//         return createdAt;
//     }
    
    
    
// }
