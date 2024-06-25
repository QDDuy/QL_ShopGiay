package com.quach.shop_giay.controler;

import com.quach.shop_giay.database.ProductDAO;
import com.quach.shop_giay.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/shop-detail")
public class ShopDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId=req.getParameter("id");
        ProductDAO productDAO=new ProductDAO();
        Product product=productDAO.getProductId(productId);
        req.setAttribute("product",product);
req.getRequestDispatcher("/WEB-INF/user/shop-detail.jsp").forward(req,resp);
    }
}
