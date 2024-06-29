package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Inventory;
import com.quach.shop_giay.model.Product;
import com.quach.shop_giay.model.Warehouse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WarehouseDAO implements DAOInterface<Warehouse>{
    @Override
    public ArrayList<Warehouse> getAll(){
        ArrayList<Warehouse> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM warehouse";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String warehouseId = rs.getString("id_kho");
                String warehouseNname = rs.getString("name_kho");
                String warehouseAddress = rs.getString("address_kho");

                Warehouse warehouse = new Warehouse(warehouseId,warehouseNname,warehouseAddress);
                System.out.println(warehouse);
                ketqua.add(warehouse);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    @Override
    public Warehouse getId(Warehouse warehouse){
        return null;
    }
    @Override
    public int insert(Warehouse warehouse){
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO warehouse (id_kho, name_kho, address_kho) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, warehouse.getWarehouse_id());
            st.setString(2, warehouse.getWarehouse_name());
            st.setString(3, warehouse.getWarehouse_address());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    @Override
    public int insertAll(ArrayList<Warehouse> arr){
        return 0;
    }
    @Override
    public int delete(Warehouse warehouse){
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM warehouse WHERE id_kho=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, warehouse.getWarehouse_id());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    @Override
    public int deleteAll(ArrayList<Warehouse> arr){
        return 0;
    }
    @Override
    public int update(Warehouse warehouse){
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE warehouse SET name_kho=?, address_kho=? WHERE id_kho=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, warehouse.getWarehouse_name());
            st.setString(2, warehouse.getWarehouse_address());
            st.setString(3, warehouse.getWarehouse_id());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
}
