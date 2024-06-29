<%@ page import="com.quach.shop_giay.database.OrderDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Kaiadmin - Bootstrap 5 Admin Dashboard</title>
    <meta
            content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
            name="viewport"
    />
    <link
            rel="icon"
            href="../../assets/img/kaiadmin/favicon.ico"
            type="image/x-icon"
    />

    <!-- Fonts and icons -->
    <script src="../../assets/js/plugin/webfont/webfont.min.js"></script>
    <script>
        WebFont.load({
            google: {families: ["Public Sans:300,400,500,600,700"]},
            custom: {
                families: [
                    "Font Awesome 5 Solid",
                    "Font Awesome 5 Regular",
                    "Font Awesome 5 Brands",
                    "simple-line-icons",
                ],
                urls: ["assets/css/fonts.min.css"],
            },
            active: function () {
                sessionStorage.fonts = true;
            },
        });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="../../assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../assets/css/plugins.min.css"/>
    <link rel="stylesheet" href="../../assets/css/kaiadmin.min.css"/>

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="../../assets/css/demo.css"/>
</head>
<body>
<div class="wrapper">
    <jsp:include page="../../layouts/sidebar.jsp"></jsp:include>

    <div class="main-panel">
        <div class="main-header">
            <div class="main-header-logo">
                <!-- Logo Header -->
                <div class="logo-header" data-background-color="dark">
                    <a href="index.html" class="logo">
                        <img
                                src="assets/img/kaiadmin/logo_light.svg"
                                alt="navbar brand"
                                class="navbar-brand"
                                height="20"
                        />
                    </a>
                    <div class="nav-toggle">
                        <button class="btn btn-toggle toggle-sidebar">
                            <i class="gg-menu-right"></i>
                        </button>
                        <button class="btn btn-toggle sidenav-toggler">
                            <i class="gg-menu-left"></i>
                        </button>
                    </div>
                    <button class="topbar-toggler more">
                        <i class="gg-more-vertical-alt"></i>
                    </button>
                </div>
                <!-- End Logo Header -->
            </div>
          <jsp:include page="../../layouts/navbaradmin.jsp"></jsp:include>
        </div>

        <div class="container">
            <div class="page-inner">

                <div class="row">
                    <div class="col-sm-6 col-md-3">
                        <div class="card card-stats card-round">
                            <div class="card-body">
                                <div class="row align-items-center">
                                    <div class="col-icon">
                                        <div
                                                class="icon-big text-center icon-primary bubble-shadow-small"
                                        >
                                            <i class="fas fa-users"></i>
                                        </div>
                                    </div>
                                    <div class="col col-stats ms-3 ms-sm-0">
                                        <div class="numbers">
                                            <p class="card-category">Visitors</p>
                                            <h4 class="card-title">1,294</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <div class="card card-stats card-round">
                            <div class="card-body">
                                <div class="row align-items-center">
                                    <div class="col-icon">
                                        <div
                                                class="icon-big text-center icon-info bubble-shadow-small"
                                        >
                                            <i class="fas fa-user-check"></i>
                                        </div>
                                    </div>
                                    <div class="col col-stats ms-3 ms-sm-0">
                                        <div class="numbers">
                                            <p class="card-category">Subscribers</p>
                                            <h4 class="card-title">1303</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <div class="card card-stats card-round">
                            <div class="card-body">
                                <div class="row align-items-center">
                                    <div class="col-icon">
                                        <div
                                                class="icon-big text-center icon-success bubble-shadow-small"
                                        >
                                            <i class="fas fa-luggage-cart"></i>
                                        </div>
                                    </div>
                                    <div class="col col-stats ms-3 ms-sm-0">
                                        <div class="numbers">
                                            <p class="card-category">Sales</p>
                                            <h4 class="card-title">$ 1,345</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <div class="card card-stats card-round">
                            <div class="card-body">
                                <div class="row align-items-center">
                                    <div class="col-icon">
                                        <div
                                                class="icon-big text-center icon-secondary bubble-shadow-small"
                                        >
                                            <i class="far fa-check-circle"></i>
                                        </div>
                                    </div>
                                    <%OrderDAO orderDAO=new OrderDAO();

                                    %>
                                    <div class="col col-stats ms-3 ms-sm-0">
                                        <div class="numbers">
                                            <p class="card-category">Order</p>
                                            <h4 class="card-title"><%=orderDAO.countTotalOrders()%></h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



            </div>
        </div>

        <footer class="footer">
            <div class="container-fluid d-flex justify-content-between">
                <nav class="pull-left">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link" href="">
                                Nhóm 1
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> Help </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> Licenses </a>
                        </li>
                    </ul>
                </nav>
                <div class="copyright">
                    2024, made with <i class="fa fa-heart heart text-danger"></i> by
                    <a href="">Nhóm 1</a>
                </div>

            </div>
        </footer>
    </div>

    <!-- Custom template | don't include it in your project! -->
    <div class="custom-template">
        <div class="title">Settings</div>
        <div class="custom-content">
            <div class="switcher">
                <div class="switch-block">
                    <h4>Logo Header</h4>
                    <div class="btnSwitch">
                        <button
                                type="button"
                                class="selected changeLogoHeaderColor"
                                data-color="dark"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="blue"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="purple"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="light-blue"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="green"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="orange"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="red"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="white"
                        ></button>
                        <br/>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="dark2"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="blue2"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="purple2"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="light-blue2"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="green2"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="orange2"
                        ></button>
                        <button
                                type="button"
                                class="changeLogoHeaderColor"
                                data-color="red2"
                        ></button>
                    </div>
                </div>
                <div class="switch-block">
                    <h4>Navbar Header</h4>
                    <div class="btnSwitch">
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="dark"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="blue"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="purple"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="light-blue"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="green"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="orange"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="red"
                        ></button>
                        <button
                                type="button"
                                class="selected changeTopBarColor"
                                data-color="white"
                        ></button>
                        <br/>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="dark2"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="blue2"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="purple2"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="light-blue2"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="green2"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="orange2"
                        ></button>
                        <button
                                type="button"
                                class="changeTopBarColor"
                                data-color="red2"
                        ></button>
                    </div>
                </div>
                <div class="switch-block">
                    <h4>Sidebar</h4>
                    <div class="btnSwitch">
                        <button
                                type="button"
                                class="changeSideBarColor"
                                data-color="white"
                        ></button>
                        <button
                                type="button"
                                class="selected changeSideBarColor"
                                data-color="dark"
                        ></button>
                        <button
                                type="button"
                                class="changeSideBarColor"
                                data-color="dark2"
                        ></button>
                    </div>
                </div>
            </div>
        </div>
        <div class="custom-toggle">
            <i class="icon-settings"></i>
        </div>
    </div>
    <!-- End Custom template -->
</div>
<!--   Core JS Files   -->
<script src="../../assets/js/core/jquery-3.7.1.min.js"></script>
<script src="../../assets/js/core/popper.min.js"></script>
<script src="../../assets/js/core/bootstrap.min.js"></script>

<!-- jQuery Scrollbar -->
<script src="../../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

<!-- Chart JS -->
<script src="../../assets/js/plugin/chart.js/chart.min.js"></script>

<!-- jQuery Sparkline -->
<script src="../../assets/js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

<!-- Chart Circle -->
<script src="../../assets/js/plugin/chart-circle/circles.min.js"></script>

<!-- Datatables -->
<script src="../../assets/js/plugin/datatables/datatables.min.js"></script>

<!-- Bootstrap Notify -->
<script src="../../assets/js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

<!-- jQuery Vector Maps -->
<script src="../../assets/js/plugin/jsvectormap/jsvectormap.min.js"></script>
<script src="../../assets/js/plugin/jsvectormap/world.js"></script>

<!-- Sweet Alert -->
<script src="../../assets/js/plugin/sweetalert/sweetalert.min.js"></script>

<!-- Kaiadmin JS -->
<script src="../../assets/js/kaiadmin.min.js"></script>

<!-- Kaiadmin DEMO methods, don't include it in your project! -->
<script src="../../assets/js/setting-demo.js"></script>
<script src="../../assets/js/demo.js"></script>
<script>
    $("#lineChart").sparkline([102, 109, 120, 99, 110, 105, 115], {
        type: "line",
        height: "70",
        width: "100%",
        lineWidth: "2",
        lineColor: "#177dff",
        fillColor: "rgba(23, 125, 255, 0.14)",
    });

    $("#lineChart2").sparkline([99, 125, 122, 105, 110, 124, 115], {
        type: "line",
        height: "70",
        width: "100%",
        lineWidth: "2",
        lineColor: "#f3545d",
        fillColor: "rgba(243, 84, 93, .14)",
    });

    $("#lineChart3").sparkline([105, 103, 123, 100, 95, 105, 115], {
        type: "line",
        height: "70",
        width: "100%",
        lineWidth: "2",
        lineColor: "#ffa534",
        fillColor: "rgba(255, 165, 52, .14)",
    });
</script>
</body>
</html>
