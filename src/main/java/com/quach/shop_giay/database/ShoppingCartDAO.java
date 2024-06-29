package com.quach.shop_giay.database;

import com.quach.shop_giay.model.Account;
import com.quach.shop_giay.model.Product;
import com.quach.shop_giay.model.ShoppingCart;
import com.quach.shop_giay.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class ShoppingCartDAO implements DAOInterface<ShoppingCart> {
    @Override
    public ArrayList<ShoppingCart> getAll() {
        ArrayList<ShoppingCart> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM shoppingcart";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String cartId = rs.getString("cart_id");
                String userId = rs.getString("user_id");
                String productId = rs.getString("product_id");
                int quantity = rs.getInt("quantity");
                User user = new UserDAO().getId(new User(userId, new Account(), "", "", "", "", ""));
                Product product = new Product();
                product.setProductId(productId);
                ShoppingCart shoppingCart = new ShoppingCart(cartId, user, product, quantity);
                ketqua.add(shoppingCart);
            }
            JDBCUtil.closeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public ArrayList<ShoppingCart> getItemUserId(String userId) {
        ArrayList<ShoppingCart> ketqua = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT *FROM shoppingcart WHERE user_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String cartId = rs.getString("cart_id");
                String productId = rs.getString("product_id");
                int quantity = rs.getInt("quantity");
                User user = new UserDAO().getId(new User(userId, new Account(), "", "", "", "", ""));
                Product product = new Product();
                product.setProductId(productId);
                ShoppingCart shoppingCart = new ShoppingCart(cartId, user, product, quantity);
                ketqua.add(shoppingCart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public static void main(String[] args) {
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
        ArrayList<ShoppingCart> shoppingCarts = shoppingCartDAO.getAll();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            System.out.println(shoppingCart.getCartId());
        }
    }

    public int getTotalCart(String userId) {
        int dem = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) AS total FROM shoppingcart WHERE user_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                dem = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dem;
    }


    @Override
    public ShoppingCart getId(ShoppingCart shoppingCart) {
        ShoppingCart result = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM shoppingcart WHERE cart_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, shoppingCart.getCartId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String cartId = rs.getString("cart_id");
                String userId = rs.getString("user_id");
                String productId = rs.getString("product_id");
                int quantity = rs.getInt("quantity");

                User user = new UserDAO().getId(new User(userId, new Account(), "", "", "", "", ""));
                Product product = new Product();
                product.setProductId(productId);

                result = new ShoppingCart(cartId, user, product, quantity);
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int insert(ShoppingCart shoppingCart) {
        int ketqua = 0;
        Connection conn = null;
        try {
            String cartId = UUID.randomUUID().toString();
            ShoppingCart cartItem = getCartItem(shoppingCart.getuser().getUserId(), shoppingCart.getProductId().getProductId());
            if (cartItem != null) {
                int quantity = cartItem.getQuantity();
                quantity += shoppingCart.getQuantity();
                cartItem.setQuantity(quantity);
                ketqua = update(cartItem);
            } else {
                conn = JDBCUtil.getConnection();
                String sql = "INSERT INTO shoppingcart (cart_id, user_id, product_id, quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, cartId);
                st.setString(2, shoppingCart.getuser().getUserId());
                st.setString(3, shoppingCart.getProductId().getProductId());
                st.setInt(4, shoppingCart.getQuantity());
                ketqua = st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                JDBCUtil.closeConnection(conn);
            }
        }
        return ketqua;
    }

    public ShoppingCart getLastCart(String userId) {
        ShoppingCart ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM shoppingcart WHERE user_id = ? ORDER BY cart_id DESC LIMIT 1";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String cartId = rs.getString("cart_id");
                String productId = rs.getString("product_id");
                int quantity = rs.getInt("quantity");
                User user = new UserDAO().getId(new User(userId, new Account(), "", "", "", "", ""));
                Product product = new Product();
                product.setProductId(productId);
                ketqua = new ShoppingCart(cartId, user, product, quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public ShoppingCart getCartItem(String userId, String productId) {
        ShoppingCart ketqua = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM shoppingcart WHERE user_id = ? AND product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, userId);
            st.setString(2, productId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String cartId = rs.getString("cart_id");
                int quantity = rs.getInt("quantity");
                User user = new UserDAO().getId(new User(userId, new Account(), "", "", "", "", ""));
                Product product = new Product();
                product.setProductId(productId);
                ketqua = new ShoppingCart(cartId, user, product, quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    @Override
    public int insertAll(ArrayList<ShoppingCart> arr) {
        return 0;
    }

    @Override
    public int delete(ShoppingCart shoppingCart) {
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<ShoppingCart> arr) {
        int dem=0;
        for (ShoppingCart shoppingCart:arr){
            dem+=this.delete(shoppingCart);
        }
        return dem;
    }

    @Override
    public int update(ShoppingCart shoppingCart) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE shoppingcart SET user_id=?, product_id=?,quantity=? WHERE cart_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, shoppingCart.getuser().getUserId());
            st.setString(2, shoppingCart.getProductId().getProductId());
            st.setInt(3, shoppingCart.getQuantity());
            st.setString(4, shoppingCart.getCartId());
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public int updateAll(ArrayList<ShoppingCart> shoppingCarts) {
        int dem = 0;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            dem += this.update(shoppingCart);
        }
        return dem;
    }

}
