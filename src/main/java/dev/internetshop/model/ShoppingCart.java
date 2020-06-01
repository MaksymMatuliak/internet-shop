package dev.internetshop.model;

import java.util.List;

public class ShoppingCart {
    private Long userId;
    private List<Product> products;
    private Long id;

    public ShoppingCart() {
        this.userId = null;
        this.products = null;
    }

    public ShoppingCart(Long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bucket{" + "userId=" + userId + ", products=" + products + ", id=" + id + '}';
    }
}
