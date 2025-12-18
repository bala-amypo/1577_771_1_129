package com.example.E_commerce.service;

import java.util.List;

import com.example.E_commerce.model.BundleRule;

public interface BundleRuleService {
    BundleRule createRule(BundleRule rule);
    BundleRule updateRule(Long id,BundleRule rule);
    BundleRule getRuleById(Long id);
    List<BundleRule> getActiveRules();
    void deactivateRule(Long id);
}
