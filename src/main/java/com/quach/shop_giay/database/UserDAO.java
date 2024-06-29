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
                String accountId=rs.getString("id_taikhoan");
                String email = rs.getString("email");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String avatar = rs.getString("avatar");
                Account account=new Account();
                account.setAccountId(accountId);
                ketqua = new User(userId, account, email, fullName, address, phone, avatar);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public User getIdTk(User user) {
        User ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM users WHERE id_taikhoan=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getAccount().getAccountId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("user_id");
                String accountId=rs.getString("id_taikhoan");
                String email = rs.getString("email");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String avatar = rs.getString("avatar");
                Account account=new Account();
                account.setAccountId(accountId);
                ketqua = new User(userId, account, email, fullName, address, phone, avatar);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }


    @Override
    public int insert(User user) {
        int ketqua = 0; // Initialize result to 0 (indicating failure) by default
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO users (user_id, email, fullname, address, phone, avatar, id_taikhoan) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getUserId());
            st.setString(2, user.getEmail());
            st.setString(3, user.getFullName());
            st.setString(4, user.getAddress());
            st.setString(5, user.getPhone());
            st.setString(6, user.getAvatar());
            st.setString(7, user.getAccount().getAccountId()); // Assuming user.getAccountID() returns the account ID
            ketqua = st.executeUpdate(); // Execute the insert statement
            // If ketqua > 0, it means insertion was successful
            System.out.println("Inserted " + ketqua + " row(s) into users table.");
            JDBCUtil.closeConnection(conn); // Close connection after use
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua; // Return the result of the insertion operation
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
            String sql = "DELETE FROM users WHERE user_id=?";
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
            String sql = "UPDATE users SET id_taikhoan=? ,email=?,fullname=?, address=?, phone=?, avatar=? WHERE user_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,user.getAccount().getAccountId());
            st.setString(2, user.getEmail());
            st.setString(3, user.getFullName());
            st.setString(4, user.getAddress());
            st.setString(5, user.getPhone());
            st.setString(6, user.getAvatar());
            st.setString(7, user.getUserId());
            ketqua = st.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }
    public static void main(String[] args) {
        UserDAO userDAO=new UserDAO();
        User user=new User();
        System.out.println(userDAO.delete(user));
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
