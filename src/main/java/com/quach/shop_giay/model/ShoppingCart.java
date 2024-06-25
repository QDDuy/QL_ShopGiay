package com.quach.shop_giay.model;

import java.util.Objects;

public class ShoppingCart {
    private String cartId;
    private User user;
    private Product productId;
    private int quantity;

    public ShoppingCart() {
    }

    public ShoppingCart(String cartId, User user, Product productId, int quantity) {
        this.cartId = cartId;
        this.user = user;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public User getuser() {
        return user;
    }

    public void setuser(User user) {
        this.user = user;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCart)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return getQuantity() == that.getQuantity() && Objects.equals(getCartId(), that.getCartId()) && Objects.equals(getuser(), that.getuser()) && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId(), getuser(), getProductId(), getQuantity());
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId='" + cartId + '\'' +
                ", user=" + user +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
