<%@ page import="java.util.List" %>
<%@ page import="java.text.BreakIterator" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.quach.shop_giay.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BookStore</title>
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
    <!-- MDB -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css" rel="stylesheet"/>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
    <!-- Css Styles -->
    <link rel="stylesheet" href="../css/user/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../css/user/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../css/user/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../css/user/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../css/user/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="../css/user/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../css/user/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../css/user/style.css" type="text/css">
</head>

<body>
<%
    List<Brand> listB = (List<Brand>) session.getAttribute("listB");
    Account account = (Account) session.getAttribute("checkLogin");
    List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) session.getAttribute("shoppingCarts");
    int totalQuantity = 0;
    if (shoppingCarts != null) {
        for (ShoppingCart cart : shoppingCarts) {
            totalQuantity += cart.getQuantity();
        }
    }
%>

<!-- Page Preloader -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Humberger Menu Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="#"><img src="../img/Logo.png" alt="" height="100" width="100"></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>
            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
            <li><a href="shoppingcart"><i class="fa fa-shopping-bag"></i> <span><%= totalQuantity %></span></a></li>
        </ul>
        <div class="header__cart__price">item: <span>$150.00</span></div>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__auth">
            <%
                if (account != null && account.getRole().equals("user")) {
            %>
            <li class="nav-item dropdown">
                <a data-mdb-dropdown-init class="nav-link dropdown-toggle d-flex align-items-center" href="#"
                   id="navbarDropdownMenuLink" role="button" aria-expanded="false">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp"
                         class="rounded-circle no-outline" style="outline: none" height="22" alt="Portrait of a Woman"
                         loading="lazy"/>
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <li><a class="dropdown-item" href="changeInforUser">My profile</a></li>
                    <li><a class="dropdown-item" href="changeAvatar">Change Avatar</a></li>
                    <li><a class="dropdown-item" href="#">Settings</a></li>
                    <li><a class="dropdown-item" href="user?act=logout">Logout</a></li>
                </ul>
            </li>
            <%
            } else {
            %>
            <a href="login"><i class="fa fa-user"></i> Login</a>
            <%
                }
            %>
        </div>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class="active"><a href="home">Home</a></li>
            <li><a href="shop">Shop</a></li>
            <li><a href="#">Pages</a>
                <ul class="header__menu__dropdown">
                    <li><a href="./shop-details.html">Shop Details</a></li>
                    <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                    <li><a href="./checkout.html">Check Out</a></li>
                    <li><a href="./blog-details.html">Blog Details</a></li>
                </ul>
            </li>
            <li><a href="./blog.html">Blog</a></li>
            <li><a href="contact">Contact</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="header__top__right__social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-pinterest-p"></i></a>
    </div>
    <div class="humberger__menu__contact">
        <ul>
            <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
            <li>Free Shipping for all Order of $99</li>
        </ul>
    </div>
</div>
<!-- Humberger Menu End -->

<!-- Header Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                            <li>Free Shipping for all Order of $99</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-pinterest-p"></i></a>
                        </div>
                        <div class="header__top__right__auth">
                            <%
                                if (account != null && account.getRole().equals("user")) {
                            %>
                            <li class="nav-item dropdown">
                                <a data-mdb-dropdown-init class="nav-link dropdown-toggle d-flex align-items-center"
                                   href="#" id="navbarDropdownMenuLink2" role="button" aria-expanded="false">
                                    <img src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp"
                                         class="rounded-circle" height="22" alt="Portrait of a Woman" loading="lazy"/>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <li><a class="dropdown-item" href="changeInforUser">My profile</a></li>
                                    <li><a class="dropdown-item" href="changeAvatar">Change Avatar</a></li>
                                    <li><a class="dropdown-item" href="#">Settings</a></li>
                                    <li><a class="dropdown-item" href="user?act=logout">Logout</a></li>
                                </ul>
                            </li>
                            <%
                            } else {
                            %>
                            <a href="login"><i class="fa fa-user"></i> Login</a>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="home"><img src="../img/Logo.png" alt="" height="100" width="100"></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li class="active"><a href="home">Home</a></li>
                        <li><a href="shop">Shop</a></li>
                        <li><a href="contact">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <ul>
                        <li><a href="shoppingcart"><i class="fa fa-shopping-bag"></i> <span><%= totalQuantity %></span></a></li>
                    </ul>
                    <div class="header__cart__price">item: <span>$150.00</span></div>
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
<!-- Header End -->

<!-- Add the rest of your HTML content here -->

<!-- Scripts -->
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.nice-select.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/jquery.slicknav.js"></script>
<script src="../js/mixitup.min.js"></script>
<script src="../js/owl.carousel.min.js"></script>
<script src="../js/main.js"></script>
</body>
</html>
