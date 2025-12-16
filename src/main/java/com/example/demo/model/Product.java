package com.example.demo.model;

import java.math.BigDecimal;
import java.security.Timestamp;

public class Product {
    private Long id;
    private String sku;
    private String name;
    private String category;
    private BigDecimal price;
    private Boolean active;
    private Timestamp createdAt;
    public Product(){}
    public Product(String sku,String name,String category,BigDecimal price,Boolean active,Timestamp createdAt){
        this.sku=sku;
        this.name=name;
    }
}
