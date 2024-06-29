package com.quach.shop_giay.model;

public class Warehouse {
    private String warehouse_id;
    private String warehouse_name;
    private String warehouse_address;

    public Warehouse(){

    }
    public Warehouse(String warehouse_id, String warehouse_name, String warehouse_address) {
        this.warehouse_id = warehouse_id;
        this.warehouse_name = warehouse_name;
        this.warehouse_address = warehouse_address;
    }
    public String getWarehouse_id() {
        return warehouse_id;
    }
    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }
    public String getWarehouse_name() {
        return warehouse_name;
    }
    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }
    public String getWarehouse_address() {
        return warehouse_address;
    }
    public void setWarehouse_address(String warehouse_address) {
        this.warehouse_address = warehouse_address;
    }

}
