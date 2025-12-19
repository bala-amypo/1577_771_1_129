// package com.example.demo.service.impl;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.demo.model.Cart;
// import com.example.demo.model.CartItem;
// import com.example.demo.model.Product;
// import com.example.demo.repository.CartItemRepository;
// import com.example.demo.repository.CartRepository;
// import com.example.demo.repository.ProductRepository;
// import com.example.demo.service.CartItemService;

// @Service
// public class CartItemServiceImpl implements CartItemService {

//     @Autowired
//     CartItemRepository cartItemRepository;

//     @Autowired
//     CartRepository cartRepository;

//     @Autowired
//     ProductRepository productRepository;

//     @Override
//     public CartItem addItem(Long cartId, Long productId, Integer quantity) {

//         Cart cart = cartRepository.findById(cartId).orElse(null);
//         Product product = productRepository.findById(productId).orElse(null);

//         if (cart != null && product != null) {
//             CartItem item = new CartItem();
//             item.setCart(cart);
//             item.setProduct(product);
//             item.setQuantity(quantity);
//             return cartItemRepository.save(item);
//         }
//         return null;
//     }

//     @Override
//     public CartItem updateItem(Long id, Integer quantity) {
//         Optional<CartItem> optional = cartItemRepository.findById(id);

//         if (optional.isPresent()) {
//             CartItem item = optional.get();
//             item.setQuantity(quantity);
//             return cartItemRepository.save(item);
//         }
//         return null;
//     }

//     @Override
//     public List<CartItem> getItemsForCart(Long cartId) {
//         return cartItemRepository.findByCartId(cartId);
//     }

//     @Override
//     public void removeItem(Long id) {
//         cartItemRepository.deleteById(id);
//     }
// }