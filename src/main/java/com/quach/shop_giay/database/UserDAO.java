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
            e.printStackTrace(); // Print the stack trace for debugging SQL exceptions
            // Handle specific SQL exceptions here (e.g., constraint violations)
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for unexpected exceptions
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

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO(); // Khởi tạo DAO của User

        // Tạo một đối tượng User mới để chèn vào cơ sở dữ liệu
        User newUser = new User();
        newUser.setUserId("U001");
        newUser.setEmail("test@example.com");
        newUser.setFullName("Test User");
        newUser.setAddress("123 Test Street");
        newUser.setPhone("1234567890");
        newUser.setAvatar("avatar.jpg");
        Account newAccount = new Account();
        newAccount.setAccountId("TK1"); // Thay thế bằng tài khoản ID thực tế
        newUser.setAccount(newAccount); // Đặt đối tượng Account cho User
        int insertedRows = userDAO.insert(newUser);
        if (insertedRows > 0) {
            System.out.println("Insert operation successful!");
        } else {
            System.out.println("Insert operation failed!");
        }
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
