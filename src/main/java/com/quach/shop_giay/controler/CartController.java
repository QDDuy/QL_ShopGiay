//package com.quach.shop_giay.controler;
//
//import com.quach.shop_giay.database.ProductDAO;
//import com.quach.shop_giay.database.ShoppingCartDAO;
//import com.quach.shop_giay.database.UserDAO;
//import com.quach.shop_giay.model.Product;
//import com.quach.shop_giay.model.ShoppingCart;
//import com.quach.shop_giay.model.User;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.util.Random;
//import java.util.UUID;
//
//@WebServlet("/cart")
//public class CartController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("checkLogin");
//
//        if (user == null) {
//            req.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(req, resp);
//        } else {
//            String productId = req.getParameter("productId");
//
//            ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
//
//            ShoppingCart shoppingCart = new ShoppingCart();
//            shoppingCart.setuser(new User(user.getUserId(), "", "", "", "", "", "", "",""));
//            shoppingCart.setProductId(new Product(productId, "", "", 0, null, null, ""));
//            shoppingCart.setQuantity(1);
//            int ketqua = shoppingCartDAO.insert(shoppingCart);
//            if(ketqua>0){
//                resp.sendRedirect("shoppingcart");
//            }
//        }
//
//
//
//    }
//
//}
