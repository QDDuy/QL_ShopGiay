package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.Employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesDAO  implements  DAOInterface<Employees>{
    @Override
    public int deleteAll(ArrayList<Employees> arr) {
        return 0;
    }

    @Override
    public int update(Employees employees) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE employee SET id_taikhoan = ?, fullname = ?, address = ?, age = ?, gender = ?, phone = ?, email = ?, luong = ? WHERE id_employe = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employees.getTaikhoan().getAccountId());
            ps.setString(2, employees.getFullname());
            ps.setString(3, employees.getAddress());
            ps.setInt(4, employees.getAge());
            ps.setString(5, employees.getGender());
            ps.setString(6, employees.getPhone());
            ps.setString(7, employees.getEmail());
            ps.setDouble(8, employees.getLuong());
            ps.setString(9, employees.getIdEmploye());
            // In ra câu lệnh SQL và các tham số
            System.out.println("Executing SQL: " + sql);
            System.out.println("Parameters: [" + employees.getTaikhoan().getAccountId() + ", " + employees.getFullname() + ", " +
                    employees.getAddress() + ", " + employees.getAge() + ", " + employees.getGender() + ", " +
                    employees.getPhone() + ", " + employees.getEmail() + ", " + employees.getLuong() + ", " +
                    employees.getIdEmploye() + "]");

            ketqua = ps.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int delete(Employees employees) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM employee WHERE id_employe = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, employees.getIdEmploye());
            ketqua = ps.executeUpdate();

            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ketqua;
    }

    @Override
    public int insertAll(ArrayList<Employees> arr) {
        int ketqua = 0;
        for (Employees employee : arr) {
            ketqua += this.insert(employee);
        }
        return ketqua;
    }

    @Override
    public int insert(Employees employees) {
        int ketqua = 0; // Initialize result to 0 (indicating failure) by default
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO employee (id_employe, id_taikhoan, fullname, address, age, gender, phone, email, luong) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, employees.getIdEmploye());
            ps.setString(2, employees.getTaikhoan().getAccountId());
            ps.setString(3, employees.getFullname());
            ps.setString(4, employees.getAddress());
            ps.setInt(5, employees.getAge());
            ps.setString(6, employees.getGender());
            ps.setString(7, employees.getPhone());
            ps.setString(8, employees.getEmail());
            ps.setDouble(9, employees.getLuong());

            ketqua = ps.executeUpdate(); // Execute the insert statement

            // If ketqua > 0, it means insertion was successful
            System.out.println("Inserted " + ketqua + " row(s) into employee table.");

        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for debugging SQL exceptions
            // Handle specific SQL exceptions here (e.g., constraint violations)
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for unexpected exceptions
        } finally {
            // Close PreparedStatement and Connection in finally block to ensure they're always closed

            JDBCUtil.closeConnection(conn);
        }

        return ketqua; // Return the result of the insertion operation
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
        Account acc = new Account();
        acc.setAccountId("TK3");
        Employees emp = new Employees("E4", acc, "Anh Duong dccc2", "123 Street", 25, "male", "0123456789", "anhduong@example.com", 1000.0);
        try {
            // Call the insert method and capture the result
            int result = dao.insert(emp);
            System.out.println("Number of rows inserted: " + result);
        } catch (Exception e) {
            System.err.println("Exception occurred:");
            e.printStackTrace();
        }
    }




}
