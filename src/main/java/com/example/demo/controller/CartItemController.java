package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public CartItem addItem(@RequestParam Long cartId,
                            @RequestParam Long productId,
                            @RequestParam Integer quantity) {

        CartItem item = new CartItem();

        Cart cart = new Cart();
        cart.setId(cartId);

        Product product = new Product();
        product.setId(productId);

        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartItemService.addItemToCart(item);
    }

    @GetMapping("/{cartId}")
    public List<CartItem> getItems(@PathVariable Long cartId) {
        return cartItemService.getItemsForCart(cartId);
    }

    @PutMapping("/{id}")
    public CartItem updateItem(@PathVariable Long id,
                               @RequestParam Integer quantity) {
        return cartItemService.updateItem(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable Long id) {
        cartItemService.removeItem(id);
    }
}
