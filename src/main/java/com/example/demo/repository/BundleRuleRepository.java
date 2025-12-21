package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.BundleRule;
import java.util.List;

public interface BundleRuleRepository extends JpaRepository<BundleRule, Long> {
    List<BundleRule> findByActiveTrue();
}
