package com.quach.shop_giay.controler;

import com.quach.shop_giay.database.BrandDAO;
import com.quach.shop_giay.database.CategoryDAO;
import com.quach.shop_giay.database.ProductDAO;
import com.quach.shop_giay.database.ShoppingCartDAO;
import com.quach.shop_giay.model.*;
import com.quach.shop_giay.model.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {"/", "/home"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        BrandDAO brandDAO = new BrandDAO();
        ArrayList<Brand> listB = brandDAO.getAll();
        session.setAttribute("listB", listB);
        CategoryDAO categoryDAO=new CategoryDAO();
        ArrayList<Category> listC=categoryDAO.getAll();
        session.setAttribute("listC",listC);
        ProductDAO productDAO = new ProductDAO();
        List<Product> listP = productDAO.getAll();
        session.setAttribute("listP", listP);
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
        User user = (User) session.getAttribute("checkLogin");
        if (user == null) {

        } else {
            int total = shoppingCartDAO.getTotalCart(user.getUserId());
            session.setAttribute("total", total);
            ArrayList<ShoppingCart> shoppingCarts = shoppingCartDAO.getItemUserId(user.getUserId());
            session.setAttribute("shoppingCarts", shoppingCarts);
        }


        req.getRequestDispatcher("/WEB-INF/user/myweb.jsp").forward(req, resp);
    }



}
