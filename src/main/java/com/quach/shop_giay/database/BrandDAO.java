package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BrandDAO implements DAOInterface<Brand> {
    @Override
    public ArrayList<Brand> getAll() {
        ArrayList<Brand> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            System.out.println("Connection established: " + (conn != null));
            String sql = "SELECT * FROM brands";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String brandId = rs.getString("brand_id");
                String brandName = rs.getString("brand_name");
                String brandImage = rs.getString("image");
                Brand brand = new Brand(brandId, brandName, brandImage);
                ketqua.add(brand);
                System.out.println("Added brand: " + brandId + ", " + brandName + ", " + brandImage);
            }
            JDBCUtil.closeConnection(conn);
            System.out.println("Connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }



    @Override
    public Brand getId(Brand brand) {
        Brand ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM brands WHERE brand_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, brand.getBrandId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String brandId = rs.getString("brand_id");
                String brandName = rs.getString("brand_name");
                String brandImage = rs.getString("image");

                ketqua = new Brand(brandId, brandName, brandImage);
                break;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int insert(Brand brand) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO brands (brand_id, brand_name, image) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, brand.getBrandId());
            st.setString(2, brand.getBrandName());
            st.setString(3, brand.getBrandImage());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int insertAll(ArrayList<Brand> arr) {
        int ketqua = 0;
        for (Brand brand : arr) {
            ketqua += this.insert(brand);
        }
        return ketqua;
    }

    @Override
    public int delete(Brand brand) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM brands WHERE brand_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, brand.getBrandId());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int deleteAll(ArrayList<Brand> arr) {
        int ketqua = 0;
        for (Brand brand : arr) {
            ketqua += this.delete(brand);
        }
        return ketqua;
    }

    @Override
    public int update(Brand brand) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE brands SET brand_name=?, image=? WHERE brand_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, brand.getBrandName());
            st.setString(2, brand.getBrandImage());
            st.setString(3, brand.getBrandId());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public static void main(String[] args) {
        BrandDAO dao = new BrandDAO();
        Brand brand = new Brand("bra1","àdsfs","àdfasf");
        dao.insert(brand);
    }
}
