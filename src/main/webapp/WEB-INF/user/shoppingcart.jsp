<%@ page import="java.util.List" %>
<%@ page import="com.quach.shop_giay.model.ShoppingCart" %>
<%@ page import="com.quach.shop_giay.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../../layouts/header.jsp"></jsp:include>

<%
    List<ShoppingCart> shoppingcart = (List<ShoppingCart>) session.getAttribute("shoppingCarts");
    System.out.println("list +" + shoppingcart);
    Account account = (Account) session.getAttribute("checkLogin");
%>

<!-- Shoping Cart Section Begin -->
<section class="shoping-cart spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__table">
                    <table>
                        <thead>
                        <tr>
                            <th class="shoping__product">Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            if (shoppingcart != null && !shoppingcart.isEmpty() && account != null) {
                                for (ShoppingCart cartItem : shoppingcart) {
                                    if (account.getAccountId().equals(cartItem.getuser().getUserId())) {
                        %>
                        <tr>
                            <td class="shoping__cart__item">
                                <img src="../../img/products/<%= cartItem.getProductId().getImage() %>" alt="">
                                <h5><%= cartItem.getProductId().getProductName() %></h5>
                            </td>
                            <td class="shoping__cart__price">
                                $<%= cartItem.getProductId().getPrice() %>
                            </td>
                            <td class="shoping__cart__quantity">
                                <div class="quantity">
                                    <div class="pro-qty">
                                        <input type="text" value="<%= cartItem.getQuantity() %>">
                                    </div>
                                </div>
                            </td>
                            <td class="shoping__cart__total">
                                $<%= cartItem.getProductId().getPrice() * cartItem.getQuantity() %>
                            </td>
                            <td class="shoping__cart__item__close">
                                <a href="#" class="fas fa-times"></a>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5">Your shopping cart is empty.</td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__btns">
                    <a href="shop" class="primary-btn cart-btn">CONTINUE SHOPPING</a>
                    <a href="#" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                        Update Cart</a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="shoping__continue">
                    <div class="shoping__discount">
                        <h5>Discount Codes</h5>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">APPLY COUPON</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="shoping__checkout">
                    <h5>Cart Total</h5>
                    <ul>
                        <%
                            double totalCart = 0;
                            if (shoppingcart != null && !shoppingcart.isEmpty()) {
                                for (ShoppingCart cartItem : shoppingcart) {
                                    double productTotal = cartItem.getProductId().getPrice() * cartItem.getQuantity();
                                    totalCart += productTotal;
                                }
                            }
                        %>
                        <li>Subtotal <span>$<%= totalCart %></span></li>
                        <li>Total <span>$<%= totalCart %></span></li>
                    </ul>
                    <a href="#" class="primary-btn">PROCEED TO CHECKOUT</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Shoping Cart Section End -->

<jsp:include page="../../layouts/footer.jsp"></jsp:include>
