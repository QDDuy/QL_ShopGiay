package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO  implements DAOInterface<Account>{
    @Override
    public ArrayList<Account> getAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from taikhoan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String accountID = rs.getString("id_taikhoan");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                Account account = new Account(accountID, username, password, role);
                accounts.add(account);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account getId(Account account) {
        Account account1 = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from taikhoan where id_taikhoan = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getAccountId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String accountID = rs.getString("id_taikhoan");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
               account1 = new Account(accountID, username, password, role);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account1;
    }

    @Override
    public int insert(Account account) {
        int ketqua = 0; // Khởi tạo kết quả mặc định là 0 (thất bại)
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO taikhoan (id_taikhoan, username, password, role) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, account.getAccountId());
            ps.setString(2, account.getUserName());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getRole());
            ketqua = ps.executeUpdate();
            System.out.println("Inserted " + ketqua + " row(s) into taikhoan table.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return ketqua;
    }


    @Override
    public int insertAll(ArrayList<Account> arr) {
        int ketqua = 0;
        for (Account account : arr) {
            ketqua = this.insert(account);
        }
        return 0;
    }

    @Override
    public int delete(Account account) {
        int ketqua = 0; // Khởi tạo kết quả mặc định là 0 (thất bại)
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM taikhoan WHERE id_taikhoan = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, account.getAccountId());
            ketqua = ps.executeUpdate(); // Thực thi lệnh delete
            if (ketqua > 0) {
                System.out.println("Deleted " + ketqua + " row(s) from taikhoan table for account ID: " + account.getAccountId());
            } else {
                System.out.println("No rows deleted for account ID: " + account.getAccountId());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra stack trace để debug các exception liên quan đến SQL
        } catch (Exception e) {
            e.printStackTrace(); // In ra stack trace cho các exception bất ngờ khác
        } finally {
            // Đóng PreparedStatement và Connection trong finally block để chắc chắn luôn được đóng
            JDBCUtil.closeConnection(conn);
        }
        return ketqua; // Trả về kết quả của hoạt động delete
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();

        // Khởi tạo đối tượng Account và thiết lập accountId
        Account acc = new Account();
        acc.setAccountId("TK6"); // Thiết lập accountId cần xóa
        int deletedRows = dao.delete(acc);
        // Kiểm tra kết quả
        if (deletedRows > 0) {
            System.out.println("Deleted account with ID " + acc.getAccountId() + " successfully.");
        } else {
            System.out.println("Failed to delete account with ID " + acc.getAccountId() + ".");
        }
    }


    @Override
    public int deleteAll(ArrayList<Account> arr) {
        return 0;
    }

    @Override
    public int update(Account account) {
        int ketqua = 0; // Khởi tạo kết quả mặc định là 0 (thất bại)
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE taikhoan SET username = ?, password = ?, role = ? WHERE id_taikhoan = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getRole());
            ps.setString(4, account.getAccountId());

            ketqua = ps.executeUpdate(); // Thực thi lệnh update

            // In ra số dòng đã cập nhật nếu thành công
            System.out.println("Updated " + ketqua + " row(s) in taikhoan table.");

        } catch (SQLException e) {
            e.printStackTrace(); // In ra stack trace để debug các exception liên quan đến SQL
            // Xử lý các SQLException cụ thể tại đây (ví dụ như ràng buộc khoá ngoại)
        } catch (Exception e) {
            e.printStackTrace(); // In ra stack trace cho các exception bất ngờ khác
        } finally {
            // Đóng PreparedStatement và Connection trong finally block để chắc chắn luôn được đóng
            JDBCUtil.closeConnection(conn);
        }
        return ketqua; // Trả về kết quả của hoạt động update
    }

    public Account selectUserPass(Account account){
        Account ketqua=null;
        try {
            Connection conn=JDBCUtil.getConnection();
            String sql="Select *from taikhoan where username=? and password=?";
            PreparedStatement st= conn.prepareStatement(sql);
            st.setString(1,account.getUserName());
            st.setString(2,account.getPassword());
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                String accountID = rs.getString("id_taikhoan");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                ketqua = new Account(accountID, username, password, role);
            }
            JDBCUtil.closeConnection(conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ketqua;
    }
    public boolean checkUserName(String username) {
        boolean exists = false;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE username = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                exists = true;
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }


}

