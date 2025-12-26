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

    if (applied.isEmpty()) {
        throw new IllegalStateException("No applicable discounts found");
    }

    return applied;
}
