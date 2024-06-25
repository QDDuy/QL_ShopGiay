package com.quach.shop_giay.model;

import java.util.Objects;

public class Product {
    private String productId;
    private String productName;
    private String desciption;
    private double price;
    private Category categoryId;
    private Brand brandId;
    private String image;

    public Product() {
    }

    public Product(String productId, String productName, String desciption, double price, Category categoryId, Brand brandId, String image) {
        this.productId = productId;
        this.productName = productName;
        this.desciption = desciption;
        this.price = price;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.image = image;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(getPrice(), product.getPrice()) == 0 && Objects.equals(getProductId(), product.getProductId()) && Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getDesciption(), product.getDesciption()) && Objects.equals(getCategoryId(), product.getCategoryId()) && Objects.equals(getBrandId(), product.getBrandId()) && Objects.equals(getImage(), product.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getProductName(), getDesciption(), getPrice(), getCategoryId(), getBrandId(), getImage());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", desciption='" + desciption + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", image='" + image + '\'' +
                '}';
    }
}
