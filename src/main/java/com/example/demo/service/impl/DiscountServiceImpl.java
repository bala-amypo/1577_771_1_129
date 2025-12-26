package com.example.demo.service.impl;

import com.example.demo.model.BundleRule;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.DiscountApplication;
import com.example.demo.repository.BundleRuleRepository;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.DiscountApplicationRepository;
import com.example.demo.service.DiscountService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final BundleRuleRepository bundleRuleRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final DiscountApplicationRepository discountApplicationRepository;

    public DiscountServiceImpl(BundleRuleRepository bundleRuleRepository,
                               CartRepository cartRepository,
                               CartItemRepository cartItemRepository,
                               DiscountApplicationRepository discountApplicationRepository) {
        this.bundleRuleRepository = bundleRuleRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.discountApplicationRepository = discountApplicationRepository;
    }

    @Override
    public List<DiscountApplication> evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        if (!Boolean.TRUE.equals(cart.getActive())) {
            throw new IllegalStateException("Cart is inactive");
        }

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        if (items.isEmpty()) {
            throw new IllegalStateException("Cart has no items");
        }

        // Remove old discounts
        discountApplicationRepository.deleteByCartId(cartId);

        Set<Long> productIds = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : items) {
            productIds.add(item.getProduct().getId());
            total = total.add(
                    item.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );
        }

        List<BundleRule> rules = bundleRuleRepository.findByActiveTrue();
        if (rules.isEmpty()) {
            throw new IllegalStateException("No active bundle rules");
        }

        List<DiscountApplication> applied = new ArrayList<>();

        for (BundleRule rule : rules) {

            boolean eligible = true;
            String[] requiredIds = rule.getRequiredProductIds().split(",");

            for (String pid : requiredIds) {
                if (!productIds.contains(Long.parseLong(pid.trim()))) {
                    eligible = false;
                    break;
                }
            }

            if (eligible) {
                DiscountApplication app = new DiscountApplication();
                app.setCart(cart);
                app.setBundleRule(rule);
                app.setDiscountAmount(
                        total.multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                                .divide(BigDecimal.valueOf(100))
                );
                app.setAppliedAt(LocalDateTime.now());

                applied.add(discountApplicationRepository.save(app));
            }
        }

        if (applied.isEmpty()) {
            throw new IllegalStateException("No applicable discounts found");
        }

        return applied;
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Discount application not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
