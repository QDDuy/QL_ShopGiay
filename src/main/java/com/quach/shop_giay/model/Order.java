package com.quach.shop_giay.model;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private String orderId;
    private User user;
    private Date orderDate;
    private double totalAmount;
    private String orderStatus;

    public Order() {
    }

    public Order(String orderId, User user, Date orderDate, double totalAmount, String orderStatus) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderId(), order.getOrderId()) && Objects.equals(getUser(), order.getUser()) && Objects.equals(getOrderDate(), order.getOrderDate()) && Objects.equals(getTotalAmount(), order.getTotalAmount()) && Objects.equals(getOrderStatus(), order.getOrderStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getUser(), getOrderDate(), getTotalAmount(), getOrderStatus());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", user=" + user +
                ", orderDate=" + orderDate +
                ", totalAmount='" + totalAmount + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
