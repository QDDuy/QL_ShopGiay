package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Brand;
import com.quach.shop_giay.model.Category;
import com.quach.shop_giay.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAOInterface<Product> {
    @Override
    public Product getId(Product product) {
        Product ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products WHERE product_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getProductId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productName = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String categoryId = rs.getString("category_id");
                String brandId = rs.getString("brand_id");
                Category category = new CategoryDAO().getId(new Category(categoryId, ""));
                Brand brand = new BrandDAO().getId(new Brand(brandId, "", ""));
                String image = rs.getString("image");

                ketqua = new Product(productId, productName, description, price, category, brand, image);

            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public ArrayList<Product> getAll() {
        ArrayList<Product> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productName = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String categoryId = rs.getString("category_id");
                String brandId = rs.getString("brand_id");
                Category category = new CategoryDAO().getId(new Category(categoryId, ""));
                Brand brand = new BrandDAO().getId(new Brand(brandId, "", ""));
                String image = rs.getString("image");
                Product product = new Product(productId, productName, description, price, category, brand, image);
                ketqua.add(product);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }


/*    public static void main(String[] args) {
        ProductDAO productDAO=new ProductDAO();
        List<Product> product=productDAO.getAll();
        for (Product p:product){
            System.out.println(p);
        }
    }*/


    public Product getProductId(String productId) {
        Product ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products WHERE product_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String categoryId = rs.getString("category_id");
                String brandId = rs.getString("brand_id");
                Category category = new CategoryDAO().getId(new Category(categoryId, ""));
                Brand brand = new BrandDAO().getId(new Brand(brandId, "", ""));
                String image = rs.getString("image");

                ketqua = new Product(productId, productName, description, price, category, brand, image);

            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }


    @Override
    public int insert(Product product) {
        return 0;
    }

    @Override
    public int insertAll(ArrayList<Product> arr) {
        return 0;
    }

    @Override
    public int delete(Product product) {
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<Product> arr) {
        return 0;
    }

    @Override
    public int update(Product product) {
        return 0;
    }

    public ArrayList<Product> getBrandProducts(String brandId) {
        ArrayList<Product> ketqua = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT * FROM products WHERE brand_id = ?")) {

            st.setString(1, brandId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String productId = rs.getString("product_id");
                    String productName = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String categoryId = rs.getString("category_id");
                    // Lưu ý: Không cần lấy lại brandId từ ResultSet vì chúng ta đã truyền vào từ tham số
                    Category category = new CategoryDAO().getId(new Category(categoryId, ""));
                    Brand brand = new BrandDAO().getId(new Brand(brandId, "", ""));
                    String image = rs.getString("image");

                    Product resultProduct = new Product(productId, productName, description, price, category, brand, image);
                    ketqua.add(resultProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

        public ArrayList<Product> getCategoryProducts(String categoryId) {
            ArrayList<Product> ketqua = new ArrayList<>();
            try (Connection conn = JDBCUtil.getConnection();
                 PreparedStatement st = conn.prepareStatement("SELECT * FROM products WHERE category_id = ?")) {

                st.setString(1, categoryId);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        String productId = rs.getString("product_id");
                        String productName = rs.getString("name");
                        String description = rs.getString("description");
                        double price = rs.getDouble("price");
                        String brandId = rs.getString("brand_id");
                        Category category = new CategoryDAO().getId(new Category(categoryId, ""));
                        Brand brand = new BrandDAO().getId(new Brand(brandId, "", ""));
                        String image = rs.getString("image");

                        Product resultProduct = new Product(productId, productName, description, price, category, brand, image);
                        ketqua.add(resultProduct);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ketqua;
        }

        public static void main(String[] args) {
            ProductDAO productDAO = new ProductDAO();

            System.out.println(productDAO.getCategoryProducts("c1"));

        }

    public List<Product> getNewProducts() {
        List<Product> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM products ORDER BY product_id COLLATE utf8mb4_general_ci DESC";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String productName = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String categoryId = rs.getString("category_id");
                String brandId = rs.getString("brand_id");
                Category category = new CategoryDAO().getId(new Category(categoryId, ""));
                Brand brand = new BrandDAO().getId(new Brand(brandId, "", ""));
                String image = rs.getString("image");

                Product product = new Product(productId, productName, description, price, category, brand, image);
                ketqua.add(product);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }


}