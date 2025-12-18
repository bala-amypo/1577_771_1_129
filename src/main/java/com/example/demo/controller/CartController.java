package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    CartService cartService;
    @PostMapping("/{userId}")
    public Cart create(@Valid @RequestBody Long userId){ 
        return cartService.createCart(userId);
    }
 
    @GetMapping("/{id}")
    public Cart get(@PathVariable Long id){
        return cartService.getCartById(id);
    }
    @GetMapping("/user/{userId}")
    public Cart getbyid(@PathVariable Long userId){
        return cartService.getCartByUserId(userId);
    }
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> put(@PathVariable Long id){
         cartService.deactivateCart(id);
         return ResponseEntity.ok("Product deactivated");
    }


}


