package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        if (!cart.getActive()) {
            return Collections.emptyList();
        }

        // Clear old discounts
        discountApplicationRepository.deleteByCartId(cartId);

        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        Set<Long> productIds = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : items) {
            productIds.add(item.getProduct().getId());
            total = total.add(
                    item.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );
        }

        List<DiscountApplication> applied = new ArrayList<>();

        for (BundleRule rule : bundleRuleRepository.findByActiveTrue()) {

            boolean eligible = true;
            for (String pid : rule.getRequiredProductIds().split(",")) {
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

        return applied;
    }

    // ✅ FIX 1: Auto-evaluate if not found
    @Override
    public DiscountApplication getApplicationById(Long id) {

        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    // ✅ FIX 2: Auto-evaluate if empty
    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {

        List<DiscountApplication> existing =
                discountApplicationRepository.findByCartId(cartId);

        if (!existing.isEmpty()) {
            return existing;
        }

        // Auto-evaluate if no discounts exist
        return evaluateDiscounts(cartId);
    }
}
