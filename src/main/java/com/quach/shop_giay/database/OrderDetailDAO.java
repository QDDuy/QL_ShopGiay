package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Order;
import com.quach.shop_giay.model.OrderDetail;
import com.quach.shop_giay.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDetailDAO implements DAOInterface<OrderDetail> {
    @Override
    public ArrayList<OrderDetail> getAll() {
        ArrayList<OrderDetail> ketqua=new ArrayList<>();
        try {
            Connection conn=JDBCUtil.getConnection();
            String sql=("select *form orderdetails");
            PreparedStatement st= conn.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                String orderDetailId=rs.getString("order_detail_id");
                String orderId=rs.getString("order_id");
                String productId=rs.getString("product_id");
                int quantity=rs.getInt("quantity");
                double uniprice=rs.getDouble("unitprice");
                Order order=new Order();
                order.setOrderId(orderId);
                Product product=new Product();
                product.setProductId(productId);
                OrderDetail orderDetail=new OrderDetail(orderDetailId,order,product,quantity,uniprice);
                ketqua.add(orderDetail);
            }
            JDBCUtil.closeConnection(conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ketqua;
    }

    public static void main(String[] args) {
        OrderDetailDAO orderDetailDAO=new OrderDetailDAO();
        System.out.println(orderDetailDAO.getAll());
    }

    @Override
    public OrderDetail getId(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public int insert(OrderDetail orderDetail) {
        return 0;
    }

    @Override
    public int insertAll(ArrayList<OrderDetail> arr) {
        return 0;
    }

    @Override
    public int delete(OrderDetail orderDetail) {
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<OrderDetail> arr) {
        return 0;
    }

    @Override
    public int update(OrderDetail orderDetail) {
        return 0;
    }
}
