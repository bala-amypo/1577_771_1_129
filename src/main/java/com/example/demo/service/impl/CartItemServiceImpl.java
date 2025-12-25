package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               CartRepository cartRepository,
                               ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartItem addItemToCart(CartItem item) {

        Cart cart = cartRepository.findById(item.getCart().getId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (!cart.getActive()) {
            throw new IllegalArgumentException("Only active carts allowed");
        }

        Product product = productRepository.findById(item.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        Optional<CartItem> existing =
                cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        if (existing.isPresent()) {
            CartItem ei = existing.get();
            ei.setQuantity(ei.getQuantity() + item.getQuantity());
            return cartItemRepository.save(ei);
        }

        item.setCart(cart);
        item.setProduct(product);
        return cartItemRepository.save(item);
    }
}
