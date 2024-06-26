package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.Order;
import com.quach.shop_giay.model.User;

import javax.lang.model.type.PrimitiveType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class OrderDAO implements DAOInterface<Order> {
    @Override
    public ArrayList<Order> getAll() {
        ArrayList<Order> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FORM orders";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String orderId = rs.getString("order_id");
                String userId = rs.getString("user_id");
                Date orderDate = rs.getDate("order_date");
                double totalAmount = rs.getDouble("total_amount");
                String orderStatus = rs.getString("order_status");
                User user = new UserDAO().getId(new User(userId,new Account(), "", "", "", "", "",""));
                Order order = new Order(orderId, user, orderDate, totalAmount, orderStatus);
                ketqua.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public Order getId(Order order) {
        Order ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM orders WHERE order_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, order.getOrderId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String orderId = rs.getString("order_id");
                String userId = rs.getString("user_id");
                Date orderDate = rs.getDate("order_date");
                double totalAmount = rs.getDouble("total_amount");
                String orderStatus = rs.getString("order_status");
                User user = new UserDAO().getId(new User(userId, new Account(), "", "", "", "", "",""));
                ketqua = new Order(orderId, user, orderDate, totalAmount, orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int insert(Order order) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO orders (order_id,user_id,order_date,total_amount,order_status) VALUES(?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, order.getOrderId());
            String userId = order.getUser().getUserId();
            st.setString(2, userId);
            st.setDate(3, order.getOrderDate());
            st.setDouble(4, order.getTotalAmount());
            st.setString(5, order.getOrderStatus());
            ketqua = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }


    @Override
    public int insertAll(ArrayList<Order> arr) {
        int ketqua = 0;
        for (Order order : arr) {
            ketqua += this.insert(order);
        }
        return ketqua;
    }

    @Override
    public int delete(Order order) {
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<Order> arr) {
        return 0;
    }

    @Override
    public int update(Order order) {
        return 0;
    }
}