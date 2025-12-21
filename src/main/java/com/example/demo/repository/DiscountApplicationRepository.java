package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DiscountApplication;

import java.util.List;

public interface DiscountApplicationRepository
        extends JpaRepository<DiscountApplication, Long> {

    List<DiscountApplication> findByCartId(Long cartId);
}
