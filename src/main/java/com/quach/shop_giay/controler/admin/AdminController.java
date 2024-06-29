package com.quach.shop_giay.controler.admin;

import com.quach.shop_giay.database.*;
import com.quach.shop_giay.model.*;

import com.quach.shop_giay.util.Encryption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Random;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Retrieve existing session without creating a new one
        if (session == null || session.getAttribute("checkLogin") == null) {
            // If session or account attribute doesn't exist, redirect to login or show an error
            resp.sendRedirect(req.getContextPath() + "/login"); // Adjust your login page URL as needed
            return;
        }

        Account account = (Account) session.getAttribute("checkLogin");
        if (account.getRole().equals("user")) {
            resp.sendRedirect(req.getContextPath() + "/login"); // Adjust your login page URL as needed
            return;
        }

        String url = req.getParameter("url");
        if ("product".equals(url)) {
            showProduct(req, resp);
        } else if ("kho".equals(url)) {
            showWarehouse(req, resp);
        } else if ("tonkho".equals(url)) {
            showInventory(req, resp);
        } else if ("donhang".equals(url)) {
            showListOrder(req, resp);
        } else if ("chitietdonhang".equals(url)) {
            showChitietDonHang(req, resp);
        } else if ("nhanvien".equals(url)) {
            showEmployee(req, resp);
        } else if ("khachhang".equals(url)) {
            showUser(req, resp);
        } else if ("taikhoan".equals(url)) {
            showTaikhoan(req, resp);
        } else if ("danhmuc".equals(url)) {
            showDanhmuc(req, resp);
        } else if ("brand".equals(url)) {
            showBrand(req, resp);
        } else if ("deleteOrder".equals(url)) {
            String orderId = req.getParameter("orderId");
            Order order = new Order();
            order.setOrderId(orderId);
            deleteOrder(req, resp, order);
        } else if ("deleteCategory".equals(url)) {
            String categoryId = req.getParameter("categoryId");
            Category category = new Category();
            category.setCategoryId(categoryId);
            deleteCategory(req, resp, category);
        } else if ("deleteEmployee".equals(url)) {
            String employeeId = req.getParameter("id_employe");
            Employees employee = new Employees();
            employee.setIdEmploye(employeeId);
            deleteEmployee(req, resp, employee);
        } else if ("deleteAccount".equals(url)) {
            String accountId = req.getParameter("accountId");
            Account account1 = new Account();
            account1.setAccountId(accountId);
            deleteAccount(req, resp, account1);
        } else if ("deleteOrderDetail".equals(url)) {
            String orderDetailId = req.getParameter("orderDetailId");
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailId(orderDetailId);
            deleteOrderDetail(req, resp, orderDetail);
        } else if ("deleteInventory".equals(url)) {
            Integer inventoryId = Integer.parseInt(req.getParameter("inventoryId"));
            Inventory inventory = new Inventory();
            inventory.setInventoryId(inventoryId);
            deleteInventory(req, resp, inventory);
        } else if ("deleteProduct".equals(url)) {
            String productId = req.getParameter("productId");
            Product product = new Product();
            product.setProductId(productId);
            deleteProduct(req, resp, product);
        } else if ("deleteBrand".equals(url)) {
            String brandId = req.getParameter("brandId");
            Brand brand = new Brand();
            brand.setBrandId(brandId);
            deleteBrand(req, resp, brand);
            return;
        } else if ("deleteWarehouse".equals(url)) {
            String warehouseId = req.getParameter("warehouseId");
            Warehouse warehouse = new Warehouse();
            warehouse.setWarehouse_id(warehouseId);
            deleteWarehouse(req, resp, warehouse);
        } else  if("deleteUser".equals(url)) {
            String userId = req.getParameter("id_user");
            User user = new User();
            user.setUserId(userId);
            deleteUser(req, resp, user);
        } else {
            // Handle unknown URL or default behavior
            req.getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("createOrder".equals(action)) {
            createOrder(req, resp);
        } else if ("editOrder".equals(action)) {
            editOrder(req, resp);
        } else if ("categorycreate".equals(action)) {
            category(req, resp);
        } else if ("editCategory".equals(action)) {
            editCategory(req, resp);

        } else if ("createEmployee".equals(action)) {
            createEmployee(req, resp);
        } else if ("editEmployee".equals(action)) {
            editEmployee(req, resp);
        } else if ("createAccount".equals(action)) {
            createAccount(req, resp);
        } else if ("editAccount".equals(action)) {
            editAccount(req, resp);
        } else if ("createUser".equals(action)) {
            createUser(req, resp);
        } else if ("createOrderDetail".equals(action)) {
            createOrderDetails(req, resp);
        } else if ("orderDetailId".equals(action)) {
            updateOrderDetail(req, resp);

        } else if ("createBrand".equals(action)) {
            createBrand(req, resp);
        } else if ("editBrand".equals(action)) {
            editBrand(req, resp);
        } else if ("editUser".equals(action)) {
            editUser(req, resp);
        } else if ("editProduct".equals(action)) {
            editProduct(req, resp);
        } else if ("product_create".equals(action)) {
            createProduct(req, resp);
        } else if ("editInventory".equals((action))) {
            editInventory(req, resp);
        } else if ("inventory_create".equals((action))) {
            createInventory(req, resp);
        } else if("editWarehouse".equals((action))){
            editWarehouse(req, resp);
        } else if("createWarehouse".equals((action))){
            createWarehouse(req, resp);
        }


    }
    // -----------------------------------------------------------------------------------------------------------------
    // =============================================Warehouse===========================================================
    private void showWarehouse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        List<Warehouse> listWarehouse = warehouseDAO.getAll();
        System.out.println(listWarehouse);
        req.setAttribute("listWarehouse", listWarehouse);

        req.getRequestDispatcher("/WEB-INF/admin/kho.jsp").forward(req, resp);
    }


    private void createWarehouse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các thông tin từ form
        Random rd = new Random();
        String warehouseId = "br" + System.currentTimeMillis() + rd.nextInt(1000);
        String warehouseName = req.getParameter("warehouseName");
        String warehouseAddress = req.getParameter("warehouseAddress");

        // Tạo đối tượng Brand
        Warehouse warehouse = new Warehouse(warehouseId, warehouseName, warehouseAddress);

        // Sử dụng DAO để thêm brand mới vào cơ sở dữ liệu
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.insert(warehouse);

        // Chuyển hướng đến trang quản lý thương hiệu (hoặc trang bạn muốn)
        resp.sendRedirect(req.getContextPath() + "/admin?url=kho");
    }

    private void editWarehouse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các thông tin từ form

        String warehouseId = req.getParameter("warehouse_id");
        String warehouseName = req.getParameter("warehouse_name");
        String warehouseAddress = req.getParameter("warehouse_address");

        // Tạo đối tượng Brand
        Warehouse warehouse = new Warehouse(warehouseId, warehouseName, warehouseAddress);

        // Sử dụng DAO để cập nhật brand trong cơ sở dữ liệu
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.update(warehouse);

        // Chuyển hướng đến trang quản lý thương hiệu (hoặc trang bạn muốn)
        resp.sendRedirect(req.getContextPath() + "/admin?url=kho");
    }

    private void deleteWarehouse(HttpServletRequest req, HttpServletResponse resp, Warehouse warehouse) throws ServletException, IOException {
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        warehouseDAO.delete(warehouse);
        req.getSession().setAttribute("successMessage", "Da xoa thanh cong");
        resp.sendRedirect(req.getContextPath() + "/admin?url=kho");
    }




    //==========================================end_warehouse===========================================================

    // -----------------------------------------Inventory---------------------------------------------------------------
    private void showInventory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InventoryDAO inventoryDAO = new InventoryDAO();
        List<Inventory> listInventory = inventoryDAO.getAll();
        System.out.println(listInventory);
        req.setAttribute("listInventory", listInventory);

        // Lấy danh sách Category và gửi cho JSP
        ProductDAO productDAO = new ProductDAO();
        List<Product> listProduct = productDAO.getAll(); // Lấy danh sách tất cả
        req.setAttribute("listProducts", listProduct);

        WarehouseDAO warehouseDAO = new WarehouseDAO();
        List<Warehouse> listWarehouse = warehouseDAO.getAll(); // Lấy danh sách tất cả
        req.setAttribute("listWarehouse", listWarehouse);
        req.getRequestDispatcher("/WEB-INF/admin/tonkho.jsp").forward(req, resp);
    }

    private void editInventory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các thông tin từ form
        Integer inventoryId = Integer.parseInt(req.getParameter("inventory_id"));
        String productId = req.getParameter("id_product");
        String warehouseId = req.getParameter("id_warehouse");
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        Date ngay_nhap = Date.valueOf(req.getParameter("import_at"));


        Product product = new Product();
        Warehouse warehouse = new Warehouse();

        product.setProductId(productId);
        warehouse.setWarehouse_id(warehouseId);


        Inventory inventory = new Inventory(inventoryId, product, warehouse, quantity, ngay_nhap);

        InventoryDAO inventorytDAO = new InventoryDAO();
        inventorytDAO.update(inventory);

        // Chuyển hướng đến trang quản lý thương hiệu (hoặc trang bạn muốn)
        resp.sendRedirect(req.getContextPath() + "/admin?url=tonkho");

    }


    private void createInventory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

//            Integer inventoryId = Integer.parseInt(req.getParameter("inventory_id"));
            String productId = req.getParameter("id_product");
            String warehouseId = req.getParameter("id_warehouse");
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            Date ngay_nhap = Date.valueOf(req.getParameter("import_at"));


            Product product = new Product();
            Warehouse warehouse = new Warehouse();

            product.setProductId(productId);
            warehouse.setWarehouse_id(warehouseId);

            Inventory inventory = new Inventory();
            inventory.setProduct_id(product);
            inventory.setWarehouse_id(warehouse);
            inventory.setQuantity(quantity);
            inventory.setNgay_nhap(ngay_nhap);
            InventoryDAO inventorytDAO = new InventoryDAO();
            int a = inventorytDAO.insert(inventory);
            if (a > 0) {
                req.getSession().setAttribute("successMessage", "Đã thêm sản phẩm thành công!");
            }
            System.out.println(product);
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=tonkho");
        }

    }


    private void deleteInventory(HttpServletRequest req, HttpServletResponse resp, Inventory inventory) throws ServletException, IOException {
        try {
            InventoryDAO inventoryDAO = new InventoryDAO();
            inventoryDAO.delete(inventory);
            req.getSession().setAttribute("successMessage", "Đã xóa thành công!");
        }catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xóa. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=tonkho");
        }
        }


    // -----------------------------------------------------------------------------------------------------------------
    // --------------------------------------hiển thị product-----------------------------------------------------------
    private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> listProducts = productDAO.getAll();
        System.out.println(listProducts);
        req.setAttribute("listProduct", listProducts);

        // Lấy danh sách Category và gửi cho JSP
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> listCategories = categoryDAO.getAll(); // Lấy danh sách tất cả
        req.setAttribute("listCategories", listCategories);

        BrandDAO brandDAO = new BrandDAO();
        List<Brand> listBrands = brandDAO.getAll(); // Lấy danh sách tất cả
        req.setAttribute("listBrands", listBrands);
        req.getRequestDispatcher("/WEB-INF/admin/product.jsp").forward(req, resp);
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các thông tin từ form
        String productId = req.getParameter("product_id");
        String productName = req.getParameter("product_name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        String categoryId = req.getParameter("id_category");
        String brandId = req.getParameter("id_brand");
        String image = req.getParameter("image");
        String color = req.getParameter("color");
        Double size = Double.parseDouble(req.getParameter("size"));


        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        BrandDAO brandDAO = new BrandDAO();
        Brand brand = new Brand();

        category.setCategoryId(categoryId);


        brand.setBrandId(brandId);


        Product product = new Product(productId, productName, description, price, category, brand, image, color, size);

        ProductDAO productDAO = new ProductDAO();
        productDAO.update(product);

        // Chuyển hướng đến trang quản lý thương hiệu (hoặc trang bạn muốn)
        resp.sendRedirect(req.getContextPath() + "/admin?url=product");

    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            Random rs = new Random();
            String productId = "TK" + System.currentTimeMillis() + rs.nextInt(10);
            String productName = req.getParameter("product_name");
            String description = req.getParameter("description");
            Double price = Double.parseDouble(req.getParameter("price"));
            String categoryId = req.getParameter("id_category");
            String brandId = req.getParameter("id_brand");
            String image = req.getParameter("image");
            String color = req.getParameter("color");
            Double size = Double.parseDouble(req.getParameter("size"));


            Category category = new Category();
            Brand brand = new Brand();

            category.setCategoryId(categoryId);
            brand.setBrandId(brandId);

            Product product = new Product(productId, productName, description, price, category, brand, image, color, size);

            ProductDAO productDAO = new ProductDAO();
            int a = productDAO.insert(product);
            if (a > 0) {
                req.getSession().setAttribute("successMessage", "Đã thêm sản phẩm thành công!");
            }
            System.out.println(product);

        }catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm sản phẩm. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=product");
        }

    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp, Product product) throws ServletException, IOException {
        try {


            ProductDAO productDAO = new ProductDAO();
            productDAO.delete(product);
            req.getSession().setAttribute("successMessage", "Đã xóa sản phẩm thành công!");
        }catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xóa. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=product");
        }

    }
    // ---------------------------------------end product --------------------------------------------------------------


    //  start  Đơn hàng
    private void showListOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> listOrders = orderDAO.getAll();
        req.setAttribute("listOrders", listOrders);
        req.getRequestDispatcher("/WEB-INF/admin/donhang.jsp").forward(req, resp);
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp, Order order) throws ServletException, IOException {
        try {
            OrderDAO orderDAO = new OrderDAO();
            int ketqua = orderDAO.delete(order);
            if (ketqua > 0) {
                req.getSession().setAttribute("successMessage", "Đã xoá chi tiết đơn hàng thành công!");
            } else {
                req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xoá chi tiết đơn hàng. Vui lòng thử lại sau.");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xoá chi tiết đơn hàng. Vui lòng thử lại sau.");
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=chitietdonhang");
        }
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
            } else {
                Date orderDate = Date.valueOf(req.getParameter("order_date"));
                double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
                String orderStatus = req.getParameter("orderStatus");

                // Tạo đối tượng Order và thêm vào cơ sở dữ liệu
                Order order = new Order(orderId, user, orderDate, totalAmount, orderStatus);
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.insert(order);

                // Nếu thành công, đặt thông báo thành công vào session
                req.getSession().setAttribute("successMessage", "Đã thêm đơn hàng thành công!");
            }

        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
        }
    }

    private void editOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String orderId = req.getParameter("orderId");
            String userId = req.getParameter("userId");
            Date orderDate = Date.valueOf(req.getParameter("orderDate"));
            double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
            String orderStatus = req.getParameter("orderStatus");

            // Kiểm tra userId có hợp lệ không
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setUserId(userId);

            if (userDAO.getId(user).equals(null)) {
                req.getSession().setAttribute("errorMessage", "User ID không hợp lệ. Vui lòng nhập lại.");
            } else {
                Order order = new Order(orderId, user, orderDate, totalAmount, orderStatus);
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.update(order);

                // Nếu thành công, đặt thông báo thành công vào session
                req.getSession().setAttribute("successMessage", "Đã update đơn hàng thành công!");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi update đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            // Chuyển hướng người dùng về trang quản lý đơn hàng
            resp.sendRedirect(req.getContextPath() + "/admin?url=donhang");
        }

    }


//    --------------------------------------------------END DON HANG---------------------------------------------------


    //--------------------------------------------------START CHI TIET DON HANG-----------------------------------------


    private void showChitietDonHang(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        List<OrderDetail> listOrderDetail = orderDetailDAO.getAll();
        req.setAttribute("listOrderDetail", listOrderDetail);
        req.getRequestDispatcher("/WEB-INF/admin/chitietdonhang.jsp").forward(req, resp);
    }

    private void createOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Random rd = new Random();
            String orderDetailId = "ODT" + System.currentTimeMillis() + rd.nextInt(1000);
            String orderId = req.getParameter("orderId");
            String productId = req.getParameter("productId");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double unitPrice = Double.parseDouble(req.getParameter("unitprice"));

            OrderDAO orderDAO = new OrderDAO();
            Order order = new Order();
            order.setOrderId(orderId);

            ProductDAO productDAO = new ProductDAO();
            Product product = new Product();
            product.setProductId(productId);

            // Check if order and product exist
            if (orderDAO.getId(order) == null) {
                req.getSession().setAttribute("errorMessage", "Mã đơn hàng không hợp lệ. Vui lòng nhập lại.");
                resp.sendRedirect(req.getContextPath() + "/admin?url=chitietdonhang");
                return;
            }

            if (productDAO.getId(product) == null) {
                req.getSession().setAttribute("errorMessage", "Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");
                resp.sendRedirect(req.getContextPath() + "/admin?url=chitietdonhang");
                return;
            }

            InventoryDAO inventoryDAO = new InventoryDAO();
            int currentQuantity = inventoryDAO.getQuantityByProductId(productId);

            // Check if there is enough quantity in inventory
            if (currentQuantity < quantity) {
                req.getSession().setAttribute("errorMessage", "Sản phẩm trong kho không đủ để thêm vào đơn hàng.");
                resp.sendRedirect(req.getContextPath() + "/admin?url=chitietdonhang");
                return;
            }

            // Proceed with adding order detail
            OrderDetail orderDetail = new OrderDetail(orderDetailId, order, product, quantity, unitPrice);
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            orderDetailDAO.insert(orderDetail);
            req.getSession().setAttribute("successMessage", "Đã thêm chi tiết đơn hàng thành công!");

        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm chi tiết đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            if (!resp.isCommitted()) {
                resp.sendRedirect(req.getContextPath() + "/admin?url=chitietdonhang");
            }
        }
    }


    private void updateOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String orderDetailId = req.getParameter("orderDetailId");
            String orderId = req.getParameter("orderId");
            String productId = req.getParameter("productId");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double unitPrice = Double.parseDouble(req.getParameter("unitprice"));

            OrderDAO orderDAO = new OrderDAO();
            Order order = new Order();
            order.setOrderId(orderId);
            Product product = new Product();
            product.setProductId(productId);
            ProductDAO productDAO = new ProductDAO();

            if (orderDAO.getId(order) == null) {
                req.getSession().setAttribute("errorMessage", "Mã đơn hàng không hợp lệ. Vui lòng nhập lại.");

            } else if (productDAO.getId(product) == null) {
                req.getSession().setAttribute("errorMessage", "Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");

            } else {
                OrderDetail orderDetail = new OrderDetail(orderDetailId, order, product, quantity, unitPrice);
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                orderDetailDAO.update(orderDetail);
                req.getSession().setAttribute("successMessage", "Đã sửa chi tiết đơn hàng thành công!");

            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi thêm đơn hàng. Vui lòng thử lại sau.");
            e.printStackTrace();
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=chitietdonhang");
        }
    }

    private void deleteOrderDetail(HttpServletRequest req, HttpServletResponse resp, OrderDetail orderDetail) throws ServletException, IOException {
        try {
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            int ketqua = orderDetailDAO.delete(orderDetail);

            if (ketqua > 0) {
                req.getSession().setAttribute("successMessage", "Đã xoá chi tiết đơn hàng thành công!");
            } else {
                req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xoá chi tiết đơn hàng. Vui lòng thử lại sau.");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xoá chi tiết đơn hàng. Vui lòng thử lại sau.");
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=chitietdonhang");

        }

    }


    //-----------------------------------------------END CHI TIET DON HANG--------------------------------------//


    //------------------------------------------TÀI KHOẢN-------------------------------------------------------//
    private void showTaikhoan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        ;
        List<Account> listacc = accountDAO.getAll();
        req.setAttribute("listacc", listacc);
        req.getRequestDispatcher("/WEB-INF/admin/taikhoan.jsp").forward(req, resp);
    }

    private void createAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random rs = new Random();
        String accountId = "TK" + System.currentTimeMillis() + rs.nextInt(10);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        password = Encryption.toSHA1(password);
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
        try {
            AccountDAO accountDAO = new AccountDAO();
            int ketqua = accountDAO.delete(account);

            if (ketqua > 0) {
                req.getSession().setAttribute("successMessage", "Đã xoá tài khoản thành công!");
            } else {
                req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xoá tài khoản. Vui lòng thử lại sau.");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi khi xoá tài khoản. Vui lòng thử lại sau.");
        } finally {
            resp.sendRedirect(req.getContextPath() + "/admin?url=taikhoan");

        }

    }

    private void editAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountId = req.getParameter("accountId");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        password = Encryption.toSHA1(password);
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

//------------------------------------------END TÀI KHOẢN------------------------------------------------------//

    //--------------------------------------NGƯỜI DÙNG-------------------------------------------------------//
    private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> listUsers = userDAO.getAll();
        req.setAttribute("listUsers", listUsers);
        req.getRequestDispatcher("/WEB-INF/admin/khachhang.jsp").forward(req, resp);
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random rd = new Random();
        String userId = "NV" + System.currentTimeMillis() + rd.nextInt(1000);// Phương thức để sinh mã người dùng ngẫu nhiên, bạn có thể triển khai riêng
        String accountId = req.getParameter("accountId");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String avatar = req.getParameter("avatar");

        // Tạo đối tượng User từ thông tin nhận được
        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setEmail(email);
        newUser.setFullName(fullname);
        newUser.setAddress(address);
        newUser.setPhone(phone);
        newUser.setAvatar(avatar);

        // Tạo đối tượng Account và đặt accountId
        Account newAccount = new Account();
        newAccount.setAccountId(accountId);
        newUser.setAccount(newAccount);
        UserDAO userDAO = new UserDAO();
        int insertedRows = userDAO.insert(newUser);

        if (insertedRows > 0) {
            // Chèn thành công, điều hướng hoặc thông báo thành công
            resp.sendRedirect(req.getContextPath() + "/admin?url=khachhang");
        } else {
            // Chèn thất bại, điều hướng hoặc thông báo lỗi
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        userDAO.delete(user);
        req.getSession().setAttribute("successMessage", "Đã xoá KH thành công!");
        resp.sendRedirect(req.getContextPath() + "/admin?url=khachhang");
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id_user");
        String accountId = req.getParameter("id_taikhoan");
        String fullName = req.getParameter("fullname");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String avatar = req.getParameter("avatar");
        Account account = new Account();
        account.setAccountId(accountId);
        User user = new User(userId, account, email, fullName, address, phone, avatar);
        UserDAO userDao = new UserDAO();
        userDao.update(user);
        resp.sendRedirect(req.getContextPath() + "/admin?url=khachhang");
        System.out.println(userDao.update(user));
    }
    //-------------------------------END USER-------------------------------------------------------------------//


    //---------------------------------------NHÂN VIÊN---------------------------------------------------------//
    private void showEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Employees employees = new Employees();
        List<Employees> listemp = employeesDAO.getAll();
        req.setAttribute("listemp", listemp);
        req.getRequestDispatcher("/WEB-INF/admin/nhanvien.jsp").forward(req, resp);
    }

    private void showDanhmuc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        List<Category> listemp = categoryDAO.getAll();
        req.setAttribute("listemp", listemp);
        req.getRequestDispatcher("/WEB-INF/admin/danhmuc.jsp").forward(req, resp);
    }

    private void category(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId;
        Random rd = new Random();
        categoryId = "DM" + System.currentTimeMillis() + rd.nextInt(1000);
        String categoryName = req.getParameter("Tendanhmuc");
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category(categoryId, categoryName);
        categoryDAO.insert(category);
        // Chuyển hướng người dùng về trang quản lý đơn hàng
        resp.sendRedirect(req.getContextPath() + "/admin?url=danhmuc");
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp, Category category) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.delete(category);
        req.getSession().setAttribute("successMessage", "Đã xoá danh mục thành công!");
        resp.sendRedirect(req.getContextPath() + "/admin?url=danhmuc");
    }

    private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("category_id");
        String categoryName = req.getParameter("category_name");
        Category category = new Category(categoryId, categoryName);
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.update(category);
        resp.sendRedirect(req.getContextPath() + "/admin?url=danhmuc");
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp, Employees employees) throws ServletException, IOException {
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
    }

    private void showBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BrandDAO brandDAO = new BrandDAO();
        List<Brand> listbrand = brandDAO.getAll();
        req.setAttribute("listbrand", listbrand);
        req.getRequestDispatcher("/WEB-INF/admin/brand.jsp").forward(req, resp);
    }

    private void createBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các thông tin từ form
        Random rd = new Random();
        String brandId = "br" + System.currentTimeMillis() + rd.nextInt(1000);
        String brandName = req.getParameter("brand_name");
        String brandImage = req.getParameter("image");

        // Tạo đối tượng Brand
        Brand brand = new Brand(brandId, brandName, brandImage);

        // Sử dụng DAO để thêm brand mới vào cơ sở dữ liệu
        BrandDAO brandDAO = new BrandDAO();
        brandDAO.insert(brand);

        // Chuyển hướng đến trang quản lý thương hiệu (hoặc trang bạn muốn)
        resp.sendRedirect(req.getContextPath() + "/admin?url=brand");
    }

    private void editBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các thông tin từ form
        String brandId = req.getParameter("brand_id");
        String brandName = req.getParameter("brand_name");
        String brandImage = req.getParameter("image");

        // Tạo đối tượng Brand
        Brand brand = new Brand(brandId, brandName, brandImage);

        // Sử dụng DAO để cập nhật brand trong cơ sở dữ liệu
        BrandDAO brandDAO = new BrandDAO();
        brandDAO.update(brand);

        // Chuyển hướng đến trang quản lý thương hiệu (hoặc trang bạn muốn)
        resp.sendRedirect(req.getContextPath() + "/admin?url=brand");
    }

    private void deleteBrand(HttpServletRequest req, HttpServletResponse resp, Brand brand) throws ServletException, IOException {
        BrandDAO brandDAO = new BrandDAO();
        brandDAO.delete(brand);
        req.getSession().setAttribute("successMessage", "Da xoa thanh cong");
        resp.sendRedirect(req.getContextPath() + "/admin?url=brand");
    }


//------------------------------------END NHÂN VIÊN-------------------------------------------------------------//
}
