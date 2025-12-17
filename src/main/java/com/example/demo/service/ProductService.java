package com.example.E_commerce.service;

import java.util.List;

import com.example.E_commerce.model.Product;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id,Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void deactiveProduct(Long id);
}
