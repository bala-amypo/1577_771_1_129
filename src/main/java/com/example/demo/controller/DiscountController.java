package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/evaluate/{cartId}")
    public ResponseEntity<String> evaluate(@PathVariable Long cartId) {
        discountService.evaluateDiscounts(cartId);
        return ResponseEntity.ok("Discounts evaluated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountApplication> getById(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.getApplicationById(id));
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<DiscountApplication>> getByCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(discountService.getApplicationsForCart(cartId));
    }
}
