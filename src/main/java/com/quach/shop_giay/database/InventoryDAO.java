package com.quach.shop_giay.database;

import com.quach.shop_giay.model.*;

import java.sql.*;
import java.util.ArrayList;

public class InventoryDAO implements DAOInterface<Inventory>{
    @Override
    public ArrayList<Inventory> getAll(){
        ArrayList<Inventory> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM inventory";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer inventoryId = rs.getInt("inventory_id");
                String productId = rs.getString("product_id");
                String warehouseId = rs.getString("kho_id");
                Integer quantity = rs.getInt("quantity");
                Date ngayNhap = rs.getDate("ngay_nhap");
                Product product = new Product();
                product.setProductId(productId);
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouse_id(warehouseId);
                Inventory inventory = new Inventory(inventoryId ,product, warehouse, quantity, ngayNhap);
                System.out.println(inventory);
                ketqua.add(inventory);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    @Override
    public Inventory getId(Inventory inventory){
        Inventory ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM inventory WHERE inventory_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, inventory.getInventoryId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Integer inventoryId = rs.getInt("inventory_id");
                String userId = rs.getString("product_id");
                String warehouseId = rs.getString("kho_id");
                Integer quantity = rs.getInt("quantity");
                Date ngay_nhap = rs.getDate("ngay_nhap");
                Product product = new Product();
                product.setProductId(userId);
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouse_id(warehouseId);

                ketqua = new Inventory(inventoryId, product, warehouse, quantity, ngay_nhap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    @Override
    public int insert(Inventory inventory){
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO inventory ( product_id ,kho_id , quantity, ngay_nhap, ngay_het_han) VALUES(?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, inventory.getProduct_id_str());
            st.setString(2, inventory.getWarehouse_id_str());
            st.setInt(3, inventory.getQuantity());
            st.setDate(4, inventory.getNgay_nhap());
            st.setDate(5, inventory.getNgay_nhap());
            ketqua = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    @Override
    public int insertAll(ArrayList<Inventory> arr){
        return 0;
    }
    @Override
    public int delete(Inventory inventory){
        int ketqua=0;
        try {
            Connection conn=JDBCUtil.getConnection();
            String sql="Delete from inventory where inventory_id=?";
            PreparedStatement st=conn.prepareStatement(sql);
            st.setInt(1,inventory.getInventoryId());
            ketqua= st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ketqua;
    }
    @Override
    public int deleteAll(ArrayList<Inventory> arr){
        return 0;
    }
    @Override
    public int update(Inventory inventory){
        int ketqua=0;
        try {
            Connection conn=JDBCUtil.getConnection();
            String sql=("Update inventory set product_id=?, kho_id=?, quantity=?, ngay_nhap=?, ngay_het_han=? where inventory_id=?");
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,inventory.getProduct_id_str());
            st.setString(2,inventory.getWarehouse_id_str());
            st.setInt(3,inventory.getQuantity());
            st.setDate(4, inventory.getNgay_nhap());
            st.setDate(5, inventory.getNgay_nhap());
            st.setInt(6, inventory.getInventoryId());
            ketqua=st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ketqua;
    }
    public int getQuantityByProductId(String productId) {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection(); // Assuming JDBCUtil is a utility class for database connection
            String sql = "SELECT quantity FROM inventory WHERE product_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (logging or throwing as needed)
        } finally {
            JDBCUtil.closeConnection(conn);
        }

        return quantity;
    }
    public void updateQuantity(String productId, int newQuantity) {
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE inventory SET quantity = ? WHERE product_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, productId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Số lượng tồn kho đã được cập nhật.");
            } else {
                System.out.println("Không có sản phẩm trong kho để cập nhật.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
