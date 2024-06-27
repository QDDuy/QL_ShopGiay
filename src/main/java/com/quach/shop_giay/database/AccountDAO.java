package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        Account acc = new Account();
        acc.setAccountId("TK1");
        System.out.println(dao.getId(acc));
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
        return 0;
    }

    @Override
    public int insertAll(ArrayList<Account> arr) {
        return 0;
    }

    @Override
    public int delete(Account account) {
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<Account> arr) {
        return 0;
    }

    @Override
    public int update(Account account) {
        return 0;
    }
}
