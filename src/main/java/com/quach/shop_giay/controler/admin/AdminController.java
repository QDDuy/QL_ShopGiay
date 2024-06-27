package com.quach.shop_giay.controler.admin;

import com.quach.shop_giay.database.*;
import com.quach.shop_giay.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Random;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");

        if ("product".equals(url)) {
            req.getRequestDispatcher("/WEB-INF/admin/product.jsp").forward(req, resp);
            return; // Ensure no further processing
        } else if ("kho".equals(url)) {
            req.getRequestDispatcher("/WEB-INF/admin/kho.jsp").forward(req, resp);
            return;
        } else if ("tonkho".equals(url)) {
            req.getRequestDispatcher("/WEB-INF/admin/tonkho.jsp").forward(req, resp);
            return;
        } else if ("donhang".equals(url)) {
            showListOrder(req, resp);
            return;
        } else if ("chitietdonhang".equals(url)) {
            req.getRequestDispatcher("/WEB-INF/admin/chitietdonhang.jsp").forward(req, resp);
            return;
        } else if ("nhanvien".equals(url)) {
            showEmployee(req, resp);
            return;

        } else if ("khachhang".equals(url)) {
            showUser(req, resp);
            return;
        } else if ("taikhoan".equals(url)) {
            showTaikhoan(req, resp);
            return;
        } else if ("danhmuc".equals(url)) {
            req.getRequestDispatcher("/WEB-INF/admin/danhmuc.jsp").forward(req, resp);
            return;
        } else if ("brand".equals(url)) {
            req.getRequestDispatcher("/WEB-INF/admin/brand.jsp").forward(req, resp);
            return;
        } else if ("deleteOrder".equals(url)) {
            String orderId = req.getParameter("orderId");
            Order order = new Order();
            order.setOrderId(orderId);
            deleteOrder(req, resp, order);
            return;
        }

        req.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createOrder".equals(action)) {
            createOrder(req, resp);
        } else if ("editOrder".equals(action)) {
            editOrder(req, resp);

        }
    }

//  start  Đơn hàng
    private void showListOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> listOrders = orderDAO.getAll();
        System.out.println(listOrders);
        req.setAttribute("listOrders", listOrders);
        req.getRequestDispatcher("/WEB-INF/admin/donhang.jsp").forward(req, resp);
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp, Order order) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.delete(order);
        req.getSession().setAttribute("successMessage", "Đã xoá đơn hàng thành công!");

        resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
    }

    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId;
        try {
            Random rd = new Random();
            orderId = "DH" + System.currentTimeMillis() + rd.nextInt(1000);
            String userId = req.getParameter("userId");
            System.out.println(userId);
            // Kiểm tra userId có hợp lệ không (ví dụ: tồn tại trong cơ sở dữ liệu)
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setUserId(userId);

            if (userDAO.getId(user) == null) {
                req.getSession().setAttribute("errorMessage", "User ID không hợp lệ. Vui lòng nhập lại.");
            }else {
                Date orderDate = Date.valueOf(req.getParameter("order_date"));
                double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
                String orderStatus = req.getParameter("orderStatus");

                // Tạo đối tượng Order và thêm vào cơ sở dữ liệu
                Order order = new Order(orderId, user, orderDate, totalAmount, orderStatus);
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.insert(order);

                // Nếu thành công, đặt thông báo thành công vào session
                req.getSession().setAttribute("successMessage", "Đã thêm đơn hàng thành công!");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            // Chuyển hướng người dùng về trang quản lý đơn hàng
            resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
        }
    }


    private void editOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String orderId = req.getParameter("orderId");
            String userId = req.getParameter("userId");
            Date orderDate = Date.valueOf(req.getParameter("orderDate"));
            double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
            String orderStatus = req.getParameter("orderStatus");

            // Kiểm tra userId có hợp lệ không
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setUserId(userId);

            if (userDAO.getId(user).equals(null)) {
                req.getSession().setAttribute("errorMessage", "User ID không hợp lệ. Vui lòng nhập lại.");
            } else {
                Order order = new Order(orderId,user, orderDate, totalAmount, orderStatus);
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.update(order);

                // Nếu thành công, đặt thông báo thành công vào session
                req.getSession().setAttribute("successMessage", "Đã update đơn hàng thành công!");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi update đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            // Chuyển hướng người dùng về trang quản lý đơn hàng
            resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
        }
    }


//    --------------------------------------------------END DON HANG---------------------------------------------------


//--------------------------------------------------START CHI TIET DON HANG-----------------------------------------



    //-----------------------------------------------END CHI TIET DON HANG------------------------------------------
   private void showChitietDonHang(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

   }

    private void showTaikhoan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        ;
        List<Account> listacc = accountDAO.getAll();
        System.out.println(listacc);
        req.setAttribute("listacc", listacc);
        req.getRequestDispatcher("/WEB-INF/admin/khachhang.jsp").forward(req, resp);
    }


    private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> listUsers = userDAO.getAll();
        System.out.println(listUsers);
        req.setAttribute("listUsers", listUsers);
        req.getRequestDispatcher("/WEB-INF/admin/khachhang.jsp").forward(req, resp);
    }


    private void showEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Employees employees = new Employees();
        List<Employees> listemp = employeesDAO.getAll();
        System.out.println(listemp);
        req.setAttribute("listemp", listemp);
        req.getRequestDispatcher("/WEB-INF/admin/nhanvien.jsp").forward(req, resp);
    }



}