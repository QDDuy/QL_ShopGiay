<%@ page import="com.quach.shop_giay.model.Brand" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quach.shop_giay.model.Product" %>
<%@ page import="com.quach.shop_giay.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="../../layouts/header.jsp"></jsp:include>

<%
    List<Brand> listB = (List<Brand>) session.getAttribute("listB");
    List<Category> listC = (List<Category>) session.getAttribute("listC");
    List<Product> listCP = (List<Product>) request.getAttribute("listCP");
    List<Product> listPN = (List<Product>) request.getAttribute("listPN");
    List<Product> listSearch = (List<Product>) request.getAttribute("searchResults");
%>
<!-- Hero Section Begin -->
<section class="hero hero-normal">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="hero__categories">
                    <div class="hero__categories__all">
                        <i class="fa fa-bars"></i>
                        <span>All departments</span>
                    </div>
                    <ul>
                        <%
                            if (listC != null) {
                                for (Category category : listC) {
                        %>
                        <li><a href="shop?id=<%=category.getCategoryId()%>"><%=category.getCategoryName()%>
                        </a></li>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="col-lg-9">
                <div class="hero__search">
                    <div class="hero__search__form">
                        <form method="get" action="shop">
                            <div class="hero__search__categories">
                                All Categories
                            </div>
                            <input type="text" name="search" placeholder="What do yo u need?">
                            <button type="submit" class="site-btn">SEARCH</button>
                        </form>
                    </div>
                    <div class="hero__search__phone">
                        <div class="hero__search__phone__icon">
                            <i class="fa fa-phone"></i>
                        </div>
                        <div class="hero__search__phone__text">
                            <h5>+65 11.188.888</h5>
                            <span>support 24/7 time</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="../../img/banner/2.png">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>Organi Shop</h2>
                    <div class="breadcrumb__option">
                        <a href="home">Home</a>
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Product Section Begin -->
<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-5">
                <div class="sidebar">
                    <div class="sidebar__item">
                        <h4>Department</h4>
                        <ul>
                            <%
                                if (listB != null) {
                                    for (Brand brand : listB) {
                            %>
                            <li><a href="shop?id=<%=brand.getBrandId()%>"><%=brand.getBrandName()%>
                            </a></li>
                            <%
                                    }
                                }
                            %>
                        </ul>
                    </div>



                    <div class="sidebar__item">
                        <div class="latest-product__text">
                            <h4>Latest Products</h4>
                            <div class="latest-product__slider owl-carousel">
                                <div class="latest-prdouct__slider__item">
                                    <%
                                        if (listPN != null) {
                                            for (Product product : listPN) {
                                    %>

                                    <a href="shop-detail?id=<%=product.getProductId()%>" class="latest-product__item">
                                        <div class="latest-product__item__pic">
                                            <img src="../../img/products/<%=product.getImage()%>" alt="">
                                        </div>
                                        <div class="latest-product__item__text">
                                            <h6><%=product.getProductName()%>
                                            </h6>
                                            <span>$<%=product.getPrice()%></span>
                                        </div>
                                    </a>
                                    <%
                                            }
                                        }
                                    %>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-9 col-md-7">
                <div class="filter__item">
                    <div class="row">

                        <div class="col-lg-4 col-md-4">
                            <div class="filter__found">
                                <%
                                    int totalProduct = 0;
                                    if (listSearch != null) {
                                        for (Product product : listSearch) {
                                            totalProduct += 1;
                                        }
                                    } else if (listCP != null) {
                                        for (Product product : listCP) {
                                            totalProduct += 1;
                                        }
                                    }
                                %>
                                <h6><span><%=totalProduct%></span> Products found</h6>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="row">
                        <div class="row">
                            <% if (listSearch != null && !listSearch.isEmpty()) {
                                for (Product productSearch : listSearch) { %>
                            <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg"
                                         data-setbg="../../img/products/<%=productSearch.getImage()%>">
                                        <ul class="product__item__pic__hover">
                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                        </ul>
                                    </div>
                                    <div class="product__item__text">
                                        <h6>
                                            <a href="shop-detail?id=<%=productSearch.getProductId()%>"><%=productSearch.getProductName()%>
                                            </a></h6>
                                        <h5>$<%=productSearch.getPrice()%>
                                        </h5>
                                    </div>
                                </div>
                            </div>
                            <% }
                            } else if (listCP != null && !listCP.isEmpty()) {
                                for (Product product : listCP) { %>
                            <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg"
                                         data-setbg="../../img/products/<%=product.getImage()%>">
                                        <ul class="product__item__pic__hover">
                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                        </ul>
                                    </div>
                                    <div class="product__item__text">
                                        <h6>
                                            <a href="shop-detail?id=<%=product.getProductId()%>"><%=product.getProductName()%>
                                            </a></h6>
                                        <h5>$<%=product.getPrice()%>
                                        </h5>
                                    </div>
                                </div>
                            </div>
                            <% }
                            } else { %>
                            <div class="col-12">
                                <p>Không có sản phẩm đang tìm.</p>
                            </div>
                            <% } %>
                        </div>


                        <div class="product__pagination">
                            <a href="#">1</a>
                            <a href="#">2</a>
                            <a href="#">3</a>
                            <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Section End -->
<jsp:include page="../../layouts/footer.jsp"></jsp:include>
