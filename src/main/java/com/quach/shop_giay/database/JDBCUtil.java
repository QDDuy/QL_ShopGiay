package com.quach.shop_giay.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection c = null;
        try {
            // Register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Define the connection URL, user and password
            String url = "jdbc:mysql://localhost:3306/shop_giay";
            String user = "root";
            String pass = "Admin123@";
            // Establish the connection
            c = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed. Check output console");
            e.printStackTrace();
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close connection");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = JDBCUtil.getConnection();
        if (conn != null) {
            System.out.println("Connection established successfully.");
            JDBCUtil.closeConnection(conn);
        } else {
            System.out.println("Failed to establish connection.");
        }
    }

}
