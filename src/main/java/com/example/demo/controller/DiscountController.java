// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.model.DiscountApplication;
// import com.example.demo.service.DiscountService;

// @RestController
// @RequestMapping("/api/discounts")
// public class DiscountController {

//     @Autowired
//     DiscountService discountService;

//     @PostMapping("/evaluate/{cartId}")
//     public String evaluate(@PathVariable Long cartId) {
//         discountService.evaluateDiscounts(cartId);
//         return "Discount evaluated";
//     }

//     @GetMapping("/{id}")
//     public DiscountApplication getById(@PathVariable Long id) {
//         return discountService.getApplicationById(id);
//     }

//     @GetMapping("/cart/{cartId}")
//     public List<DiscountApplication> getByCart(@PathVariable Long cartId) {
//         return discountService.getApplicationsForCart(cartId);
//     }
// }