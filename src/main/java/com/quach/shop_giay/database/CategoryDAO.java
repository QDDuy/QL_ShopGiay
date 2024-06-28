package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDAO implements DAOInterface<Category> {
    @Override
    public ArrayList<Category> getAll() {
        ArrayList<Category> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM categorys";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String categoryId = rs.getString("category_id");
                String categoryName = rs.getString("category_name");
                Category category = new Category(categoryId, categoryName);
                ketqua.add(category);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }


    @Override
    public Category getId(Category category) {
        Category ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM categorys WHERE category_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, category.getCategoryId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String categoryId = rs.getString("category_id");
                String categoryName = rs.getString("category_name");
                ketqua = new Category(categoryId, categoryName);
                break;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int insert(Category category) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO categorys (category_id,category_name) VALUES(?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, category.getCategoryId());
            st.setString(2, category.getCategoryName());
            ketqua = st.executeUpdate();

            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int insertAll(ArrayList<Category> arr) {
        int ketqua = 0;
        for (Category category : arr) {
            ketqua += this.insert(category);
        }
        return ketqua;
    }

    @Override
    public int delete(Category category) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "DELETE from categorys WHERE category_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, category.getCategoryId());

            ketqua = st.executeUpdate();
            return ketqua;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        category.setCategoryId("A1719546864509296");
        categoryDAO.delete(category);

    }
    @Override
    public int deleteAll(ArrayList<Category> arr) {
        int ketqua = 0;
        for (Category category : arr) {
            ketqua += this.delete(category);
        }
        return ketqua;
    }

    @Override
    public int update(Category category) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE categorys SET category_name=? WHERE category_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, category.getCategoryName());
            st.setString(2, category.getCategoryId());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
}
