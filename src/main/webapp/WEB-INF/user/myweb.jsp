<%@ page import="com.quach.shop_giay.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quach.shop_giay.model.Brand" %>
<%@ page import="com.quach.shop_giay.model.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Header -->
<jsp:include page="../../layouts/header.jsp"></jsp:include>
<%
    List<Brand> listB = (List<Brand>) session.getAttribute("listB");
    List<Product> listP = (List<Product>) session.getAttribute("listP");
    List<Category> listC=(List<Category>) session.getAttribute("listC");
%>

<!-- Hero Section Begin -->
<section class="hero">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="hero__categories">
                    <div class="hero__categories__all">
                        <i class="fa fa-bars"></i>
                        <span>All departments</span>
                    </div>
                    <ul>
                        <%if(listC!=null){
                            for (Category category:listC){
                        %>
                        <li><a href="shop?id=<%=category.getCategoryId()%>"><%=category.getCategoryName()%>
                        </a></li>
                        <%}}%>
                    </ul>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="hero__search">
                    <div class="hero__search__form">
                        <form action="#">
                            <div class="hero__search__categories">
                                All Categories
                            </div>
                            <input type="text" placeholder="What do yo u need?">
                            <button type="submit" class="site-btn">SEARCH</button>
                        </form>
                    </div>
                    <div class="hero__search__phone">
                        <div class="hero__search__phone__icon">
                            <i class="fa fa-phone"></i>
                        </div>
                        <div class="hero__search__phone__text">
                            <h5>+84 363273201</h5>
                            <span>support 24/7 time</span>
                        </div>
                    </div>
                </div>
                <div class="hero__item set-bg" data-setbg="../../img/banner/4.png">
                    <div class="hero__text">
                        <span>Shose</span>
                        <h2>Giày <br/>100% Chính Hãng</h2>
                        <p>Free Pickup and Delivery Available</p>
                        <a href="shop" class="primary-btn">SHOP NOW</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->
<!-- Categories Section Begin -->
<section class="categories">
    <div class="container">
        <div class="row">

            <div class="categories__slider owl-carousel">


                <%
                    if (listB != null) {
                        for (Brand brand : listB) {
                %>
                <div class="col-lg-3">
                    <div class="categories__item set-bg" data-setbg="../../img/brand/<%=brand.getBrandImage()%>">
                        <h5><a href="shop?id=<%=brand.getBrandId()%>"><%=brand.getBrandName()%>
                        </a></h5>
                    </div>
                </div>
                <%
                        }
                    }
                %>

            </div>

        </div>
    </div>
</section>
<!-- Categories Section End -->
<!-- Featured Section Begin -->
<section class="featured spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>Sản phẩm nổi bật</h2>
                </div>
                <div class="featured__controls">
                    <ul>
                        <li class="active" data-filter="*">All</li>
                        <% if (listC != null) {
                            for (Category category:listC) {%>
                        <li data-filter=".<%=category.getCategoryId()%>"><%=category.getCategoryName()%>
                        </li>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row featured__filter">
            <% if (listP != null) {
                for (Product product : listP) {%>
            <div class="col-lg-3 col-md-4 col-sm-6 mix  <%=product.getCategoryId().getCategoryId()%>">
                <div class="featured__item">
                    <div class="featured__item__pic set-bg" data-setbg="../../img/products/<%=product.getImage()%>">
<%--                        <img src="../../img/products/<%=product.getImage()%>" alt="">--%>
                        <ul class="featured__item__pic__hover">

                            <li><a  href="cart?productId=<%=product.getProductId()%>"><i class="fa fa-shopping-cart"></i></a></li>
                        </ul>
                    </div>
                    <div class="featured__item__text">
                        <h6><a href="shop-detail?id=<%=product.getProductId()%>"><%=product.getProductName()%>
                        </a></h6>
                        <h5><%=product.getPrice()%> VNĐ</h5>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>

        </div>
    </div>
</section>
<!-- Featured Section End -->

<!-- Footer -->
<jsp:include page="../../layouts/footer.jsp"></jsp:include>
