package com.quach.shop_giay.controler;

import com.quach.shop_giay.database.UserDAO;
import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/changeInforUser")
public class changInforUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account= (Account) req.getSession().getAttribute("checkLogin");
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setAccount(account);
        User user1=userDAO.getIdTk(user);
        System.out.println(user1);
        req.setAttribute("userLoging",user1);
        req.getRequestDispatcher("/WEB-INF/user/changeInforUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the logged-in account from the session
        Account account = (Account) req.getSession().getAttribute("checkLogin");

        // Check if the account is null (user not logged in), redirect to login page
        if (account == null) {
            resp.sendRedirect(req.getContextPath()+"/login");
            return;
        }

        // Retrieve user details from the request parameters
        String userId = req.getParameter("userId");
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        // Create a new User object with the provided details
        User user = new User(userId, account, email, fullname, address, phone, "");

        // Create an instance of UserDAO and update the user information
        UserDAO userDAO = new UserDAO();
        int updateResult = userDAO.update(user);

        // Set a message based on the result of the update operation
        if (updateResult >0) {
            req.setAttribute("msgSuccess", "User information updated successfully.");
        } else {
            req.setAttribute("msgError", "Failed to update user information. Please try again.");
        }

        // Forward the request back to the user information page
        req.getRequestDispatcher("/WEB-INF/user/changeInforUser.jsp").forward(req, resp);
    }

}
