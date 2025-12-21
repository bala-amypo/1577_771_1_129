package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DiscountService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void evaluateDiscounts(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            return;
        }

        Set<Long> productIdsInCart = new HashSet<>();
        BigDecimal cartTotal = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            productIdsInCart.add(item.getProduct().getId());
            BigDecimal itemTotal = item.getProduct()
                    .getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            cartTotal = cartTotal.add(itemTotal);
        }

        List<BundleRule> activeRules = bundleRuleRepository.findByActiveTrue();

        for (BundleRule rule : activeRules) {

            String[] requiredIds = rule.getRequiredProductIds().split(",");
            boolean eligible = true;

            for (String idStr : requiredIds) {
                Long pid = Long.parseLong(idStr.trim());
                if (!productIdsInCart.contains(pid)) {
                    eligible = false;
                    break;
                }
            }

            if (eligible) {
                BigDecimal discount = cartTotal
                        .multiply(BigDecimal.valueOf(rule.getDiscountPercentage()))
                        .divide(BigDecimal.valueOf(100));

                DiscountApplication application = new DiscountApplication();
                application.setCart(cart);
                application.setBundleRule(rule);
                application.setDiscountAmount(discount);

                discountApplicationRepository.save(application);
            }
        }
    }

    @Override
    public DiscountApplication getApplicationById(Long id) {
        return discountApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    @Override
    public List<DiscountApplication> getApplicationsForCart(Long cartId) {
        return discountApplicationRepository.findByCartId(cartId);
    }
}
