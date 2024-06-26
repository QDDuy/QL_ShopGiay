package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements DAOInterface<User> {
    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM users";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("user_id");
                String email = rs.getString("email");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String avatar = rs.getString("avatar");
                String accountId = rs.getString("id_taikhoan");
                Account account = new Account();
                account.setAccountId(accountId);
                User user = new User(userId, account, email, fullName, address, phone,avatar);
                ketqua.add(user);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    public static void main(String[] args) {

        UserDAO userDAO=new UserDAO();
        System.out.println(userDAO.getAll());
    }

    @Override
    public User getId(User user) {
        User ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM users WHERE user_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getUserId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("user_id");
                String accountId=rs.getString("account_id");
                String email = rs.getString("email");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String avatar = rs.getString("avatar");
                Account account=new Account();
                account.setAccountId(accountId);
                ketqua = new User(userId, account, email, fullName, address, phone, avatar);
                break;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }



    @Override
    public int insert(User user) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO users (user_id,account_id,email,fullname,address,phone,role,avatar) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getUserId());
            st.setString(4, user.getEmail());
            st.setString(5, user.getFullName());
            st.setString(6, user.getAddress());
            st.setString(7, user.getPhone());
            st.setString(8, user.getAvatar());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int insertAll(ArrayList<User> arr) {
        int ketqua = 0;
        for (User user : arr) {
            ketqua += this.insert(user);
        }
        return ketqua;
    }

    @Override
    public int delete(User user) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "DELETE FORM users WHERE user_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getUserId());

            ketqua = st.executeUpdate();

            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int deleteAll(ArrayList<User> arr) {
        int ketqua = 0;
        for (User user : arr) {
            ketqua += this.delete(user);
        }
        return ketqua;
    }

    @Override
    public int update(User user) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE users SET account_id=? ,email=?,fullname=?, address=?, phone=?,  WHERE user_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,user.getAccount().getAccountId());
            st.setString(2, user.getEmail());
            st.setString(3, user.getFullName());
            st.setString(4, user.getAddress());
            st.setString(5, user.getPhone());
            st.setString(6, user.getUserId());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }



    public boolean checkUserName(String userName) {
        boolean ketqua = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM users WHERE user_name=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            ketqua = rs.next(); // Trả về true nếu có bản ghi, ngược lại trả về false
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public int updateAvatar(User user) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE users SET avatar=?  WHERE user_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getAvatar());
            st.setString(2, user.getUserId());
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ketqua;
    }


}
