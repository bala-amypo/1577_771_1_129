package com.example.E_commerce.model;

public class CartItem {
    private Long id;
    private Cart cart;
    private Product product;
    private int quantity;
    public CartItem(){}
    public CartItem(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }
    public Cart getCart() {
        return cart;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }


    
}
