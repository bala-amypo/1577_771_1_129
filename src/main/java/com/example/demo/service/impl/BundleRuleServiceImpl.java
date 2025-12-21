package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐ REQUIRED
public class BundleRuleServiceImpl implements BundleRuleService {

    private final BundleRuleRepository repository;

    public BundleRuleServiceImpl(BundleRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BundleRule createRule(BundleRule rule) {
        return repository.save(rule);
    }

    @Override
    public BundleRule updateRule(Long id, BundleRule rule) {
        BundleRule existing = getRuleById(id);
        existing.setRuleName(rule.getRuleName());
        existing.setRequiredProductIds(rule.getRequiredProductIds());
        existing.setDiscountPercentage(rule.getDiscountPercentage());
        return repository.save(existing);
    }

    @Override
    public BundleRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public void deactivateRule(Long id) {
        BundleRule rule = getRuleById(id);
        rule.setActive(false);
        repository.save(rule);
    }
}
