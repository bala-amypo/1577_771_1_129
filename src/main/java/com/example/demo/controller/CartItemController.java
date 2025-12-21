package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartItemService;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @PostMapping
    public CartItem add(@RequestParam Long cartId,@RequestParam Long productId,@RequestParam Integer quantity) {
        return cartItemService.addItem(cartId, productId, quantity);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Integer quantity) {
        if (cartItemService.updateItem(id, quantity) != null) {
            return "Successful";
        }
        return "Not successful";
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItems(@PathVariable Long cartId) {
        return cartItemService.getItemsForCart(cartId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        cartItemService.removeItem(id);
        return "Item removed";
    }
}