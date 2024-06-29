package com.quach.shop_giay.model;

import java.sql.Date;

public class Inventory {
    private Integer inventoryId;
    private Product product_id;
    private Warehouse warehouse_id;
    private Integer quantity;
    private Date ngay_nhap;
//    private Date ngay_het_han;

    public Inventory() {

    }
    public Inventory(Integer inventoryId,Product product_id, Warehouse warehouse_id, Integer quantity, Date ngay_nhap) {
        this.inventoryId = inventoryId;
        this.product_id = product_id;
        this.warehouse_id = warehouse_id;
        this.quantity = quantity;
        this.ngay_nhap = ngay_nhap;
    }
    public Integer getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Product getProduct_id() {
        return product_id;
    }
    public String getProduct_id_str() {
        return product_id.getProductId();
    }
    public void setProduct_id(Product product_id) {
        this.product_id = product_id;
    }
    public Warehouse getWarehouse_id() {
        return warehouse_id;
    }
    public String getWarehouse_id_str() {
        return warehouse_id.getWarehouse_id();
    }
    public void setWarehouse_id(Warehouse warehouse_id) {
        this.warehouse_id = warehouse_id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Date getNgay_nhap() {
        return ngay_nhap;
    }
    public void setNgay_nhap(Date ngay_nhap) {
        this.ngay_nhap = ngay_nhap;
    }

}
