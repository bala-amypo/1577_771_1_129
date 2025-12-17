package com.example.E_commerce.repository;

import org.springframework.stereotype.Repository;

import com.example.E_commerce.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    boolean existBySku(String sku);
}
