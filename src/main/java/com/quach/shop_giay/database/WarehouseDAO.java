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
        return 0;
    }
    @Override
    public int insertAll(ArrayList<Warehouse> arr){
        return 0;
    }
    @Override
    public int delete(Warehouse warehouse){
        return 0;
    }
    @Override
    public int deleteAll(ArrayList<Warehouse> arr){
        return 0;
    }
    @Override
    public int update(Warehouse warehouse){
        return 0;
    }
}
