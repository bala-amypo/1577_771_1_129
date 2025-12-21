package com.example.demo.service.impl;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import com.example.demo.exception.ResourceNotFoundException;

public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    public CartServiceImpl(CartRepository repository) {
        this.repository = repository;
    }

    public Cart createCart(Long userId) {
        repository.findByUserId(userId)
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Cart already exists");
                });
        Cart cart = new Cart();
        cart.setUserId(userId);
        return repository.save(cart);
    }

    public Cart getCartById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public Cart getCartByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public void deactivateCart(Long id) {
        getCartById(id); // logical deactivate only
    }
}
