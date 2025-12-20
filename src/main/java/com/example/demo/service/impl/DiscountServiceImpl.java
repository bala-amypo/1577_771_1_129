// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.demo.model.DiscountApplication;
// import com.example.demo.repository.DiscountApplicationRepository;
// import com.example.demo.service.DiscountService;

// @Service
// public class DiscountServiceImpl implements DiscountService {

//     @Autowired
//     DiscountApplicationRepository discountRepository;

//     @Override
//     public void evaluateDiscounts(Long cartId) {
//     }

//     @Override
//     public DiscountApplication getApplicationById(Long id) {
//         return discountRepository.findById(id).orElse(null);
//     }

//     @Override
//     public List<DiscountApplication> getApplicationsForCart(Long cartId) {
//         return discountRepository.findByCartId(cartId);
//     }
// }