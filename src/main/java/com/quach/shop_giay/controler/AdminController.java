package com.quach.shop_giay.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
            req.getRequestDispatcher("/WEB-INF/admin/donhang.jsp").forward(req, resp);
            return;
        }else if("chitietdonhang".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/chitietdonhang.jsp").forward(req, resp);
            return;
        }else if("nhanvien".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/nhanvien.jsp").forward(req, resp);
            return;
        }else if("khachhang".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/khachhang.jsp").forward(req, resp);
            return;
        }else if("taikhoan".equals(url)){
            req.getRequestDispatcher("/WEB-INF/admin/taikhoan.jsp").forward(req, resp);
            return;
        }

        req.getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(req, resp);
    }
}
