package com.quach.shop_giay.controler;

import com.quach.shop_giay.database.*;
import com.quach.shop_giay.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("checkLogin");

        if (account == null) {
            req.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(req, resp);
        } else {
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setAccount(account);
            user = userDAO.getIdTk(user);
            System.out.println("User ID: " + user.getUserId());

            String productId = req.getParameter("productId");
            if (productId == null || productId.isEmpty()) {
                resp.sendRedirect("errorPage.jsp");
                return;
            }

            ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
            com.quach.shop_giay.model.ShoppingCart shoppingCart = new com.quach.shop_giay.model.ShoppingCart();
            shoppingCart.setuser(user);
            Product product = new Product();
            product.setProductId(productId);
            shoppingCart.setProductId(product);
            shoppingCart.setQuantity(1);
            System.out.println(shoppingCart);

            int ketqua = shoppingCartDAO.insert(shoppingCart);

            if (ketqua > 0) {
                // Fetch all items in the shopping cart for the user
                ArrayList<com.quach.shop_giay.model.ShoppingCart> shoppingCarts = shoppingCartDAO.getItemUserId(user.getUserId());

                if (shoppingCarts == null || shoppingCarts.isEmpty()) {
                    resp.sendRedirect("errorPage.jsp");
                    return;
                }

                session.setAttribute("shoppingCarts", shoppingCarts);

                // Check if an order already exists for the user
                OrderDAO orderDAO = new OrderDAO();
                Order existingOrder = orderDAO.getOrderByUserId(user.getUserId());

                Order order;
                if (existingOrder == null) {
                    // Create a new order if one doesn't exist
                    order = new Order();
                    order.setOrderId(UUID.randomUUID().toString());
                    order.setUser(user);
                    order.setOrderDate(Date.valueOf(LocalDate.now()));
                    order.setOrderStatus("Chưa thanh toán");
                    double totalAmount = 0.0;
                    for (com.quach.shop_giay.model.ShoppingCart cartItem : shoppingCarts) {
                        totalAmount += cartItem.getProductId().getPrice() * cartItem.getQuantity();
                    }
                    order.setTotalAmount(totalAmount);

                    int orderResult = orderDAO.insert(order);

                    if (orderResult <= 0) {
                        resp.sendRedirect("errorPage.jsp");
                        return;
                    }
                } else {
                    // Update the existing order
                    order = existingOrder;

                    double totalAmount = order.getTotalAmount();
                    for (com.quach.shop_giay.model.ShoppingCart cartItem : shoppingCarts) {
                        totalAmount += cartItem.getProductId().getPrice() * cartItem.getQuantity();
                    }
                    order.setTotalAmount(totalAmount);

                    int orderResult = orderDAO.update(order);

                    if (orderResult <= 0) {
                        resp.sendRedirect("errorPage.jsp");
                        return;
                    }
                }

                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

                for (com.quach.shop_giay.model.ShoppingCart cartItem : shoppingCarts) {
                    OrderDetail existingOrderDetail = orderDetailDAO.getOrderDetailByOrderIdAndProductId(order.getOrderId(), cartItem.getProductId().getProductId());

                    if (existingOrderDetail != null) {
                        // Update quantity if order detail exists
                        existingOrderDetail.setQuantity(cartItem.getQuantity());
                        int orderDetailResult = orderDetailDAO.updateOrderDetailQuantity(existingOrderDetail);

                        if (orderDetailResult <= 0) {
                            resp.sendRedirect("errorPage.jsp");
                            return;
                        }
                    } else {
                        // Insert new order detail if it doesn't exist
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOrderDetailId(UUID.randomUUID().toString());
                        orderDetail.setOrderId(order);
                        orderDetail.setProductId(cartItem.getProductId());
                        orderDetail.setQuantity(cartItem.getQuantity());
                        orderDetail.setUnitPrice(cartItem.getProductId().getPrice());

                        int orderDetailResult = orderDetailDAO.insert(orderDetail);

                        if (orderDetailResult <= 0) {
                            resp.sendRedirect("errorPage.jsp");
                            return;
                        }
                    }
                }

                // Set empty shopping cart attribute
                req.setAttribute("shoppingCarts", new ArrayList<ShoppingCart>());

                // Redirect to shopping cart page
                req.getRequestDispatcher("/WEB-INF/user/shoppingcart.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("errorPage.jsp");
            }
        }
    }
}
