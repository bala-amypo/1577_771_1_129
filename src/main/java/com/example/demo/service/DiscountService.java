package com.example.E_commerce.service;

import java.util.List;

import com.example.E_commerce.model.DiscountApplication;

public interface DiscountService {
    void evaluateDiscounts(Long cartId);
    DiscountApplication getApplicationById(Long id);
    List<DiscountApplication> getApplicationsForCart(Long cartId);
    
}
