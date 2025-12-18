package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BundleRule;
import com.example.demo.service.BundleRuleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bundle-rules")
public class BundleRuleController {
    @Autowired
    BundleRuleService bundleRuleService;
    @PostMapping
    public BundleRule create(@Valid @RequestBody BundleRule rule){ 
        return bundleRuleService.createRule(rule);
 
    }
    @PutMapping("/{id}")
    public String putbyid(Long id,@Valid @RequestBody BundleRule rule){
        if(bundleRuleService.updateRule(id,rule) != null){
            return "SuccessFul";
        }
        return "Not sucessfull";
    }
    @GetMapping("/{id}")
    public BundleRule getbyid(@PathVariable Long id){
        return bundleRuleService.getRuleById(id);
    }
    @GetMapping("/active")
    public List<BundleRule> getll(@PathVariable Long id){
        return bundleRuleService.getActiveRules(id);
    }
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> put(@PathVariable Long id){
         bundleRuleService.deactivateRule(id);
         return ResponseEntity.ok("Product deactivated");
    }


}


