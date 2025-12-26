package com.example.demo.controller;

import com.example.demo.model.DiscountApplication;
import com.example.demo.service.DiscountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    // ✅ Force evaluation before returning
    @GetMapping("/cart/{cartId}")
    public List<DiscountApplication> getByCart(@PathVariable Long cartId) {
        discountService.evaluateDiscounts(cartId);
        return discountService.getApplicationsForCart(cartId);
    }

    // ✅ Works after evaluation
    @GetMapping("/{id}")
    public DiscountApplication getById(@PathVariable Long id) {
        return discountService.getApplicationById(id);
    }

    // Optional manual trigger (keep it)
    @PostMapping("/evaluate/{cartId}")
    public void evaluate(@PathVariable Long cartId) {
        discountService.evaluateDiscounts(cartId);
    }
}
