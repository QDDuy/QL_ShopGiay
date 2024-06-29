package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Brand;

import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BrandDAO implements DAOInterface<Brand> {
    @Override
    public ArrayList<Brand> getAll() {
        ArrayList<Brand> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM brands";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String brandId = rs.getString("brand_id");
                String brandName = rs.getString("brand_name");
                String brandImage = rs.getString("image");
                Brand brand = new Brand(brandId, brandName, brandImage);
                ketqua.add(brand);
            }
            JDBCUtil.closeConnection(conn);
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
            String sql = "INSERT INTO brands (brand_id, brand_name,image) VALUES (?,?,?)";
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
        int dem = 0;
        for (Brand brand : arr) {
            dem += this.insert(brand);
        }
        return dem;
    }

    @Override
    public int delete(Brand brand) {
        int ketqua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FORM brands WHERE brand_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, brand.getBrandId());
            ketqua = st.executeUpdate();

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
            String sql = "UPDATE brands set brand_name=? ,image=? WHERE brand_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, brand.getBrandName());
            st.setString(2,brand.getBrandImage());
            st.setString(3, brand.getBrandId());

            ketqua = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public Map<String, String> ddd() {
        Map<String, String> ketqua = new HashMap<>();
        try (Connection conn = JDBCUtil.getConnection(); // Sử dụng try-with-resources để tự động đóng kết nối
             PreparedStatement st = conn.prepareStatement("SELECT brand_id, brand_name FROM brands");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String brandId = rs.getString("brand_id");
                String brandName = rs.getString("brand_name");

                // Thêm trực tiếp vào HashMap
                ketqua.put(brandId, brandName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi phù hợp (ví dụ: ném một ngoại lệ tùy chỉnh hoặc trả về một HashMap rỗng)
        }
        return ketqua;
    }

}
