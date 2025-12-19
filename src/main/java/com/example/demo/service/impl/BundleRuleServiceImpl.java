package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BundleRule;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.service.BundleRuleService;

@Service
public class BundleRuleServiceImpl implements BundleRuleService {

    @Autowired
    BundleRuleRepository bundleRuleRepository;

    @Override
    public BundleRule createRule(BundleRule rule) {
        return bundleRuleRepository.save(rule);
    }

    @Override
    public BundleRule updateRule(Long id, BundleRule rule) {
        Optional<BundleRule> optional = bundleRuleRepository.findById(id);

        if (optional.isPresent()) {
            BundleRule old = optional.get();
            old.setRuleName(rule.getRuleName());
            old.setRequiredProductIds(rule.getRequiredProductIds());
            old.setDiscountPercentage(rule.getDiscountPercentage());
            old.setActive(rule.getActive());
            return bundleRuleRepository.save(old);
        }
        return null;
    }

    @Override
    public BundleRule getRuleById(Long id) {
        return bundleRuleRepository.findById(id).orElse(null);
    }

    @Override
    public List<BundleRule> getActiveRules() {
        return bundleRuleRepository.findByActive(true);
    }

    @Override
    public BundleRule deactivateRule(Long id) {
        Optional<BundleRule> optional = bundleRuleRepository.findById(id);
        if (optional.isPresent()) {
            BundleRule rule = optional.get();
            rule.setActive(false);
            return bundleRuleRepository.save(rule);
        }
    }
}