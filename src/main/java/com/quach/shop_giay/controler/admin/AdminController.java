package com.quach.shop_giay.controler.admin;
import com.quach.shop_giay.database.AccountDAO;
import com.quach.shop_giay.database.EmployeesDAO;
import com.quach.shop_giay.database.OrderDAO;
import com.quach.shop_giay.database.UserDAO;
import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.Employees;
import com.quach.shop_giay.model.Order;
import com.quach.shop_giay.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("url");

        if ("product".equals(url)) {
            req.getRequestDispatcher("/WEB-INF/admin/product.jsp").forward(req, resp);
            return; // Ensure no further processing
        }else if("kho".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/kho.jsp").forward(req, resp);
            return;
        }else if("tonkho".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/tonkho.jsp").forward(req, resp);
            return;
        }else if("donhang".equals(url)){
            showListOrder(req,resp);
            return;
        }else if("chitietdonhang".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/chitietdonhang.jsp").forward(req, resp);
            return;
        }else if("nhanvien".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/nhanvien.jsp").forward(req, resp);
            return;
        }else if("khachhang".equals(url)){
            showUsers(req,resp);
            return;
        }else if("taikhoan".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/taikhoan.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(req, resp);
    }



    private void showListOrder(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        OrderDAO orderDAO=new OrderDAO();
        List<Order> listOrders = orderDAO.getAll();
        System.out.println(listOrders);
        req.setAttribute("listOrders",listOrders);
        req.getRequestDispatcher("/WEB-INF/admin/donhang.jsp").forward(req, resp);
    }

    private void showUsers(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        UserDAO userDAO=new UserDAO();
        List<User> listUsers = userDAO.getAll();
        System.out.println(listUsers);
        req.setAttribute("listUsers",listUsers);
        req.getRequestDispatcher("/WEB-INF/admin/khachhang.jsp").forward(req, resp);

    }

    private  void showTaikhoan(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        AccountDAO accountDAO=new AccountDAO();
        Account account = new Account();;
        List<Account> listacc = accountDAO.getAll();
        System.out.println(listacc);
        req.setAttribute("listacc",listacc);
        req.getRequestDispatcher("/WEB-INF/admin/khachhang.jsp").forward(req, resp);
    }

    private  void showEmployee(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Employees employees = new Employees();
        List<Employees> listemp = employeesDAO.getAll();
        System.out.println(listemp);
        req.setAttribute("listemp",listemp);
        req.getRequestDispatcher("/WEB-INF/admin/nhanvien.jsp").forward(req, resp);
    }

}
