package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart createCart(Long userId) {
        Cart cart=cartRepository.findById(userId).orElse(null);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        Optional<Cart>lis= cartRepository.findById(userId);
        return lis.orElse(null);
    }

    @Override
    public void deactivateCart(Long id) {
        Optional<Cart> optional = cartRepository.findById(id);
        if (optional.isPresent()) {
            Cart cart = optional.get();
            cart.setActive(false);
            cartRepository.save(cart);
        }
    }
}