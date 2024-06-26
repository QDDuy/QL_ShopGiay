package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.Employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeesDAO  implements  DAOInterface<Employees>{
    @Override
    public int deleteAll(ArrayList<Employees> arr) {
        return 0;
    }

    @Override
    public int update(Employees employees) {
        return 0;
    }

    @Override
    public int delete(Employees employees) {
        return 0;
    }

    @Override
    public int insertAll(ArrayList<Employees> arr) {
        return 0;
    }

    @Override
    public int insert(Employees employees) {
        return 0;
    }

    @Override
    public Employees getId(Employees employees) {
        Employees result = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM employee WHERE id_employe = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employees.getIdEmploye()); // Assuming Employees class has getIdEmploye method
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String idEmploye = rs.getString("id_employe");
                String idAccount = rs.getString("id_taikhoan");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                double luong = rs.getDouble("luong");

                Account account = new Account();
                account.setAccountId(idAccount);
                result = new Employees(idEmploye, account, fullname, address, age, gender, phone, email, luong);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ArrayList<Employees> getAll() {
        ArrayList<Employees> emp = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String idEmploye = rs.getString("id_employe");
                String idAccount = rs.getString("id_taikhoan");
                String fullname = rs.getString("fullname");
                String address = rs.getString("address");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                double luong = rs.getDouble("luong");
                Account account = new Account();
                account.setAccountId(idAccount);
                Employees employees = new Employees(idEmploye, account, fullname, address, age, gender, phone, email, luong);
                emp.add(employees);
            }
            JDBCUtil.closeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }


    public static void main(String[] args) {
        EmployeesDAO dao = new EmployeesDAO();
        Employees emp = new Employees();
        emp.setIdEmploye("E1");
        System.out.println(emp);
    }
}
