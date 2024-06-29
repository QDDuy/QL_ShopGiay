package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Order;
import com.quach.shop_giay.model.OrderDetail;
import com.quach.shop_giay.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAO implements DAOInterface<OrderDetail> {

    public OrderDetail getName_by_ID(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() {
        ArrayList<OrderDetail> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = ("select *from orderdetails");
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String orderDetailId = rs.getString("order_detail_id");
                String orderId = rs.getString("order_id");
                String productId = rs.getString("product_id");
                int quantity = rs.getInt("quantity");
                double uniprice = rs.getDouble("unitprice");
                Order order = new Order();
                order.setOrderId(orderId);
                Product product = new Product();
                product.setProductId(productId);
                OrderDetail orderDetail = new OrderDetail(orderDetailId, order, product, quantity, uniprice);
                ketqua.add(orderDetail);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }


    @Override
    public OrderDetail getId(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public int insert(OrderDetail orderDetail) {
        int ketqua = 0;
        try {
            String sql = "INSERT INTO orderdetails (order_detail_id, order_id, product_id, quantity, unitprice) VALUES (?, ?, ?, ?, ?)";
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, orderDetail.getOrderDetailId());
            st.setString(2, orderDetail.getOrderId().getOrderId());
            st.setString(3, orderDetail.getProductId().getProductId());
            st.setInt(4, orderDetail.getQuantity());
            st.setDouble(5, orderDetail.getUnitPrice());

            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ketqua;
    }



    @Override
    public int insertAll(ArrayList<OrderDetail> arr) {
        return 0;
    }

    @Override
    public int delete(OrderDetail orderDetail) {
        int ketqua=0;
        try {
            Connection conn=JDBCUtil.getConnection();
            String sql="Delete from orderdetails where order_detail_id=?";
            PreparedStatement st=conn.prepareStatement(sql);
            st.setString(1,orderDetail.getOrderDetailId());
            ketqua= st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ketqua;
    }

    public static void main(String[] args) {
        OrderDetailDAO orderDetailDAO=new OrderDetailDAO();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderDetailId("ODT1719536179753599");
        System.out.println(orderDetailDAO.delete(orderDetail));
    }
    @Override
    public int deleteAll(ArrayList<OrderDetail> arr) {
        return 0;
    }

    @Override
    public int update(OrderDetail orderDetail) {
        int ketqua=0;
        try {
            Connection conn=JDBCUtil.getConnection();
            String sql=("Update orderdetails set order_id=? ,product_id=?, quantity=?,unitprice=? where order_detail_id=?");
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,orderDetail.getOrderId().getOrderId());
            st.setString(2,orderDetail.getProductId().getProductId());
            st.setInt(3,orderDetail.getQuantity());
            st.setDouble(4, orderDetail.getUnitPrice());
            st.setDouble(4, orderDetail.getUnitPrice());
            st.setString(5, orderDetail.getOrderDetailId());
            ketqua=st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ketqua;
    }

    public OrderDetail getOrderDetailByOrderIdAndProductId(String orderId, String productId) {
        OrderDetail orderDetail = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM orderdetails WHERE order_id = ? AND product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, orderId);
            st.setString(2, productId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String orderDetailId = rs.getString("order_detail_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("unitprice");
                Order order = new OrderDAO().getId(new Order(orderId, null, null, 0.0, ""));
                Product product =new Product();
                product.setProductId(productId);
                orderDetail = new OrderDetail(orderDetailId, order, product, quantity, price);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetail;
    }
    public int updateOrderDetailQuantity(OrderDetail orderDetail) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE orderdetails SET quantity = ? WHERE order_detail_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderDetail.getQuantity());
            st.setString(2, orderDetail.getOrderDetailId());
            result = st.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
