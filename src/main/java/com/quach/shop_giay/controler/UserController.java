//package com.quach.shop_giay.controler;
//
//import com.quach.shop_giay.database.UserDAO;
//import com.quach.shop_giay.model.User;
//import com.quach.shop_giay.util.Encryption;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Random;
//
//@WebServlet("/user")
//public class UserController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String act = req.getParameter("act");
//        if (act.equals("logout")) {
//            logout(req, resp);
//        }
//
//    }
//
//    private void login(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String userName = request.getParameter("userName");
//            String password = request.getParameter("password");
//            password = Encryption.toSHA1(password);  // Đảm bảo phương thức này đúng
//            User user = new User();
//            user.setUserName(userName);
//            user.setPassword(password);
//            UserDAO userDAO = new UserDAO();
//            User checkLogin = userDAO.selectUserPass(user);
//            System.out.println("Username: " + userName);
//            System.out.println("Hashed Password: " + password);
//            System.out.println("Login Check: " + (checkLogin != null ? "Success" : "Failure"));
//
//            if (checkLogin != null) {
//                HttpSession session = request.getSession();
//                session.setAttribute("checkLogin", checkLogin);
//                response.sendRedirect("home");
//            } else {
//                request.setAttribute("msgError", "Your username and password are incorrect");
//                // Chuyển tiếp yêu cầu để giữ lại thông báo lỗi
//                request.getRequestDispatcher("login").forward(request, response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void register(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String act = request.getParameter("act");
//            String userName = request.getParameter("userName");
//            String password = request.getParameter("password");
//            String passwordConfirm = request.getParameter("passwordConfirm");
//            String fullName = request.getParameter("fullName");
//            String email = request.getParameter("email");
//            String address = request.getParameter("address");
//            String phone = request.getParameter("phone");
//            String role = request.getParameter("role");
//            String avatar=request.getParameter("avatar");
//            request.setAttribute("userName", userName);
//            request.setAttribute("password", password);
//            request.setAttribute("passwordConfirm", passwordConfirm);
//            request.setAttribute("fullName", fullName);
//            request.setAttribute("address", address);
//            request.setAttribute("phone", phone);
//            request.setAttribute("avatar", avatar);
//            String url = "";
//            String msgError = "";
//            UserDAO userDAO = new UserDAO();
//            if (userDAO.checkUserName(userName)) {
//                msgError += "Tên người dùng đã tồn tại";
//            }
//            if (!password.equals(passwordConfirm)) {
//                msgError += "Mật khẩu không khớp";
//            } else {
//                password = Encryption.toSHA1(password);
//            }
//
//            if (msgError.length() > 0) {
//                url = "/WEB-INF/user/register.jsp";
//            } else {
//                Random rd = new Random();
//                String userId = "KH" + System.currentTimeMillis() + rd.nextInt(1000);
//                User user = new User(userId, userName, password, email, fullName, address, phone, role, avatar);
//                userDAO.insert(user);
//                url = "/WEB-INF/user/login.jsp";
//
//            }
//            request.setAttribute("msgError", msgError);
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
//            rd.forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.removeAttribute("check  Login");
//            session.invalidate();
//        }
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//        response.sendRedirect(url + "/login");
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String act = req.getParameter("act");
//        if (act != null) {
//            if (act.equals("login")) {
//                login(req, resp);
//            }
//            if (act.equals("register")) {
//                register(req, resp);
//            }
//            if (act.equals("logout")) {
//                logout(req, resp);
//            }
//
//            // Thêm các điều kiện khác cho act nếu cần
//        } else {
//            // Xử lý khi act là null
//            resp.getWriter().write("Hành động không được xác định.");
//            System.out.println("act parameter is null");
//        }
//    }
//}
