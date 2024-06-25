package com.quach.shop_giay.model;

import java.util.Objects;

public class Brand {
    private String brandId;
    private String brandName;
    private String brandImage;


    public Brand() {
    }

    public Brand(String brandId, String brandName, String brandImage) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandImage = brandImage;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand)) return false;
        Brand brand = (Brand) o;
        return Objects.equals(getBrandId(), brand.getBrandId()) && Objects.equals(getBrandName(), brand.getBrandName()) && Objects.equals(getBrandImage(), brand.getBrandImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrandId(), getBrandName(), getBrandImage());
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId='" + brandId + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandImage='" + brandImage + '\'' +
                '}';
    }
}
