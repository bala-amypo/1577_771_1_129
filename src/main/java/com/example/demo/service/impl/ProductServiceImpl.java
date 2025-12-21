package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        repository.findBySku(product.getSku())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("SKU already exists");
                });
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = getProductById(id);
        existing.setName(product.getName());
        existing.setCategory(product.getCategory());
        existing.setPrice(product.getPrice());
        return repository.save(existing);
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void deactivateProduct(Long id) {
        Product p = getProductById(id);
        p.setActive(false);
        repository.save(p);
    }
}
