package com.quach.shop_giay.controler;

import com.quach.shop_giay.database.BrandDAO;
import com.quach.shop_giay.database.ProductDAO;
import com.quach.shop_giay.model.Brand;
import com.quach.shop_giay.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class ShopController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId=req.getParameter("id");
        ProductDAO productDAO=new ProductDAO();
        if(categoryId!=null){
            ArrayList<Product> listCP=productDAO.getCategoryProducts(categoryId);
            req.setAttribute("listCP",listCP);
        }else {
            ArrayList<Product> listCP=productDAO.getAll();
            req.setAttribute("listCP",listCP);
        }

        List<Product> productNew=productDAO.getNewProducts();
        req.setAttribute("listPN",productNew);

        String query = req.getParameter("search");

        if (query == null || query.isEmpty()) {
            req.getRequestDispatcher("/WEB-INF/user/shop.jsp").forward(req, resp);

            return;
        }

        List<Product> searchResults = productDAO.searchProducts(query);

        req.setAttribute("searchResults", searchResults);

        req.getRequestDispatcher("/WEB-INF/user/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
