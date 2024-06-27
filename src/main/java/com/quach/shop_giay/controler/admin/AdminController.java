package com.quach.shop_giay.controler.admin;

import com.quach.shop_giay.database.AccountDAO;
import com.quach.shop_giay.database.EmployeesDAO;
import com.quach.shop_giay.database.OrderDAO;
import com.quach.shop_giay.database.UserDAO;
import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.Employees;
import com.quach.shop_giay.model.Order;
import com.quach.shop_giay.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
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
        }else if("deleteEmployee".equals(url)){
            String employeeId = req.getParameter("id_employe");
            Employees employee = new Employees();
            employee.setIdEmploye(employeeId);
            deleteEmployee(req, resp, employee);
            return;
        }else if("deleteAccount".equals(url)){
            String accountId = req.getParameter("accountId");
            Account account = new Account();
            account.setAccountId(accountId);
            deleteAccount(req, resp, account);
        }else if("deleteUser".equals(url)){
            String userId = req.getParameter("id_user");
            User user = new User();
            user.setUserId(userId);
            deleteUser(req, resp, user);
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
        }else if ("createEmployee".equals(action)) {
            createEmployee(req, resp);
        }else if ("editEmployee".equals(action)){
            editEmployee(req, resp);
        }else if("createAccount".equals(action)){
            createAccount(req, resp);
        }else if("editAccount".equals(action)){
            editAccount(req, resp);
        }else if("createUser".equals(action)){
            createUser(req, resp);
        }else if("editUser".equals(action)){
            editUser(req, resp);
        }
    }

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
        resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
    }
    private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId;
        try {
            Random rd = new Random();
            orderId = "DH" + System.currentTimeMillis() + rd.nextInt(1000);
            String userId = req.getParameter("userId");
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setUserId(userId);
            if (userDAO.getId(user) == null) {
                req.getSession().setAttribute("errorMessage", "User ID không hợp lệ. Vui lòng nhập lại.");
            }
            Date orderDate = Date.valueOf(req.getParameter("order_date"));
            double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
            String orderStatus = req.getParameter("orderStatus");
            Order order = new Order(orderId, user, orderDate, totalAmount, orderStatus);
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.insert(order);
            req.getSession().setAttribute("successMessage", "Đã thêm đơn hàng thành công!");
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
        }
    }
    private void editOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        String userId = req.getParameter("userId");
        Date orderDate = Date.valueOf(req.getParameter("orderDate"));
        double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
        String orderStatus = req.getParameter("orderStatus");
        OrderDAO orderDAO = new OrderDAO();
        User user = new User();
        user.setUserId(userId);
        Order order = new Order(orderId, user, orderDate, totalAmount, orderStatus);
        orderDAO.update(order);
        resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
    }
    private void showTaikhoan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        ;
        List<Account> listacc = accountDAO.getAll();
        System.out.println(listacc);
        req.setAttribute("listacc", listacc);
        req.getRequestDispatcher("/WEB-INF/admin/taikhoan.jsp").forward(req, resp);
    }

    private void createAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountId = req.getParameter("accountId");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        Account account = new Account();
        account.setAccountId(accountId);
        account.setUserName(username);
        account.setPassword(password);
        account.setRole(role);
        AccountDAO accountDAO = new AccountDAO();
        int result = accountDAO.insert(account);
        if (result > 0) {
            resp.sendRedirect(req.getContextPath() + "/admin?url=taikhoan");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin?error=insert_failed"); // Thay account bằng đường dẫn tương ứng của bạn
        }
    }
    private void deleteAccount(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        int result = accountDAO.delete(account);
        resp.sendRedirect(req.getContextPath() + "/admin?url=taikhoan");

    }
    private void editAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountId = req.getParameter("accountId");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        // Tạo đối tượng Account mới từ thông tin lấy được
        Account updatedAccount = new Account();
        updatedAccount.setAccountId(accountId);
        updatedAccount.setUserName(username);
        updatedAccount.setPassword(password);
        updatedAccount.setRole(role);

        // Gọi phương thức update trong AccountDAO để cập nhật thông tin tài khoản
        AccountDAO accountDAO = new AccountDAO();
        int updatedRows = accountDAO.update(updatedAccount);

        if (updatedRows > 0) {
            // Cập nhật thành công
            resp.sendRedirect(req.getContextPath() + "/admin?url=taikhoan");
        } else {
            // Cập nhật thất bại, xử lý tại đây (ví dụ: hiển thị thông báo lỗi)
            resp.getWriter().println("Failed to update account.");
        }
    }
    private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> listUsers = userDAO.getAll();
        System.out.println(listUsers);
        req.setAttribute("listUsers", listUsers);
        req.getRequestDispatcher("/WEB-INF/admin/khachhang.jsp").forward(req, resp);
    }
    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random rd = new Random();
        String userId = "KH" + System.currentTimeMillis() + rd.nextInt(1000);
        String accountId = req.getParameter("accountId");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String avatar = req.getParameter("avatar");
        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setEmail(email);
        newUser.setFullName(fullname);
        newUser.setAddress(address);
        newUser.setPhone(phone);
        newUser.setAvatar(avatar);
        Account newAccount = new Account();
        newAccount.setAccountId(accountId);
        newUser.setAccount(newAccount);
        UserDAO userDAO = new UserDAO();
        int insertedRows = userDAO.insert(newUser);
        if (insertedRows > 0) {
            resp.sendRedirect(req.getContextPath() + "/admin?url=khachhang");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
    private  void deleteUser(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        userDAO.delete(user);
        resp.sendRedirect(req.getContextPath() + "/admin?url=khachhang");
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userId = req.getParameter("id_user");
        String accountId = req.getParameter("id_taikhoan");
        String fullName = req.getParameter("fullname");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        User user = new User();
        user.setUserId(userId);
        Account account = new Account();
        account.setAccountId(accountId);
        user.setAccount(account);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);
        UserDAO userDAO = new UserDAO();
        resp.sendRedirect(req.getContextPath() + "/admin?url=khachhang");
        System.out.println(userDAO.update(user));
    }
    private void showEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Employees employees = new Employees();
        List<Employees> listemp = employeesDAO.getAll();
        System.out.println(listemp);
        req.setAttribute("listemp", listemp);
        req.getRequestDispatcher("/WEB-INF/admin/nhanvien.jsp").forward(req, resp);
    }
    private  void deleteEmployee(HttpServletRequest req, HttpServletResponse resp, Employees employees) throws ServletException, IOException {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.delete(employees);
        resp.sendRedirect(req.getContextPath() + "/admin?url=nhanvien");
    }
    private void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random rd = new Random();
        String idEmploye = "NV" + System.currentTimeMillis() + rd.nextInt(1000); // Tạo mã nhân viên ngẫu nhiên
        String idAccount = req.getParameter("accountId");
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        double luong = Double.parseDouble(req.getParameter("salary"));
        Account account = new Account();
        account.setAccountId(idAccount);
        Employees employees = new Employees(idEmploye, account, fullname, address, age, gender, phone, email, luong);
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.insert(employees);
        resp.sendRedirect(req.getContextPath() + "/admin?url=nhanvien");
    }

    private void editEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEmploye = req.getParameter("id_employe");
        String idAccount = req.getParameter("id_taikhoan");
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        double luong = Double.parseDouble(req.getParameter("luong"));
        Account account = new Account();
        account.setAccountId(idAccount);
        Employees employees = new Employees(idEmploye, account, fullname, address, age, gender, phone, email, luong);
        EmployeesDAO employeesDAO = new EmployeesDAO();
        employeesDAO.update(employees);
        resp.sendRedirect(req.getContextPath() + "/admin?url=nhanvien");
        System.out.println(employeesDAO.update(employees));
    }





}
