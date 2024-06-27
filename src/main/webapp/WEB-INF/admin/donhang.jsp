<%@ page import="java.util.List" %>
<%@ page import="com.quach.shop_giay.model.Order" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Quản lý đơn hàng</title>
    <meta
            content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
            name="viewport"
    />
    <link
            rel="icon"
            href=""
            type="image/x-icon"
    />

    <!-- Fonts and icons -->
    <script src="../assets/js/plugin/webfont/webfont.min.js"></script>
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
                urls: ["../assets/css/fonts.min.css"],
            },
            active: function () {
                sessionStorage.fonts = true;
            },
        });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../assets/css/plugins.min.css"/>
    <link rel="stylesheet" href="../assets/css/kaiadmin.min.css"/>

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="../assets/css/demo.css"/>
</head>
<body>
<div class="wrapper">
    <jsp:include page="../../layouts/sidebar.jsp"></jsp:include>

    <div class="main-panel">
        <div class="main-header">
            <div class="main-header-logo">
                <!-- Logo Header -->
                <div class="logo-header" data-background-color="dark">
                    <a href="../index.html" class="logo">
                        <img
                                src="../assets/img/kaiadmin/logo_light.svg"
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
            <!-- Navbar Header -->
            <nav
                    class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom"
            >
                <div class="container-fluid">
                    <%
                        String successMessage = (String) session.getAttribute("successMessage");
                        System.out.println(successMessage);
                        String errorMessage = (String) session.getAttribute("errorMessage");
                        System.out.println(errorMessage);
                        session.removeAttribute("successMessage");
                        session.removeAttribute("errorMessage");
                    %>
                    <% if (successMessage != null && !successMessage.isEmpty()) { %>
                    <div id="successMessage" class="alert alert-success ml-4 alert-slide" role="alert">
                        <%= successMessage %>
                    </div>
                    <% } else if (errorMessage != null && !errorMessage.isEmpty()) { %>
                    <div id="errorMessage" class="alert alert-danger ml-4 alert-slide" role="alert">
                        <%= errorMessage %>
                    </div>
                    <% } %>


                    <ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
                        <li
                                class="nav-item topbar-icon dropdown hidden-caret d-flex d-lg-none"
                        >
                            <a
                                    class="nav-link dropdown-toggle"
                                    data-bs-toggle="dropdown"
                                    href="#"
                                    role="button"
                                    aria-expanded="false"
                                    aria-haspopup="true"
                            >
                                <i class="fa fa-search"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-search animated fadeIn">
                                <form class="navbar-left navbar-form nav-search">
                                    <div class="input-group">
                                        <input
                                                type="text"
                                                placeholder="Search ..."
                                                class="form-control"
                                        />
                                    </div>
                                </form>
                            </ul>
                        </li>
                        <li class="nav-item topbar-icon dropdown hidden-caret">
                            <a
                                    class="nav-link dropdown-toggle"
                                    href="#"
                                    id="messageDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                            >
                                <i class="fa fa-envelope"></i>
                            </a>
                            <ul
                                    class="dropdown-menu messages-notif-box animated fadeIn"
                                    aria-labelledby="messageDropdown"
                            >
                                <li>
                                    <div
                                            class="dropdown-title d-flex justify-content-between align-items-center"
                                    >
                                        Messages
                                        <a href="#" class="small">Mark all as read</a>
                                    </div>
                                </li>
                                <li>
                                    <div class="message-notif-scroll scrollbar-outer">
                                        <div class="notif-center">
                                            <a href="#">
                                                <div class="notif-img">
                                                    <img
                                                            src="../assets/img/jm_denis.jpg"
                                                            alt="Img Profile"
                                                    />
                                                </div>
                                                <div class="notif-content">
                                                    <span class="subject">Jimmy Denis</span>
                                                    <span class="block"> How are you ? </span>
                                                    <span class="time">5 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-img">
                                                    <img
                                                            src="../assets/img/chadengle.jpg"
                                                            alt="Img Profile"
                                                    />
                                                </div>
                                                <div class="notif-content">
                                                    <span class="subject">Chad</span>
                                                    <span class="block"> Ok, Thanks ! </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-img">
                                                    <img
                                                            src="../assets/img/mlane.jpg"
                                                            alt="Img Profile"
                                                    />
                                                </div>
                                                <div class="notif-content">
                                                    <span class="subject">Jhon Doe</span>
                                                    <span class="block">
                                Ready for the meeting today...
                              </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-img">
                                                    <img
                                                            src="../assets/img/talha.jpg"
                                                            alt="Img Profile"
                                                    />
                                                </div>
                                                <div class="notif-content">
                                                    <span class="subject">Talha</span>
                                                    <span class="block"> Hi, Apa Kabar ? </span>
                                                    <span class="time">17 minutes ago</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <a class="see-all" href="javascript:void(0);"
                                    >See all messages<i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item topbar-icon dropdown hidden-caret">
                            <a
                                    class="nav-link dropdown-toggle"
                                    href="#"
                                    id="notifDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-haspopup="true"
                                    aria-expanded="false"
                            >
                                <i class="fa fa-bell"></i>
                                <span class="notification">4</span>
                            </a>
                            <ul
                                    class="dropdown-menu notif-box animated fadeIn"
                                    aria-labelledby="notifDropdown"
                            >
                                <li>
                                    <div class="dropdown-title">
                                        You have 4 new notification
                                    </div>
                                </li>
                                <li>
                                    <div class="notif-scroll scrollbar-outer">
                                        <div class="notif-center">
                                            <a href="#">
                                                <div class="notif-icon notif-primary">
                                                    <i class="fa fa-user-plus"></i>
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block"> New user registered </span>
                                                    <span class="time">5 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-icon notif-success">
                                                    <i class="fa fa-comment"></i>
                                                </div>
                                                <div class="notif-content">
                              <span class="block">
                                Rahmad commented on Admin
                              </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-img">
                                                    <img
                                                            src="../assets/img/profile2.jpg"
                                                            alt="Img Profile"
                                                    />
                                                </div>
                                                <div class="notif-content">
                              <span class="block">
                                Reza send messages to you
                              </span>
                                                    <span class="time">12 minutes ago</span>
                                                </div>
                                            </a>
                                            <a href="#">
                                                <div class="notif-icon notif-danger">
                                                    <i class="fa fa-heart"></i>
                                                </div>
                                                <div class="notif-content">
                                                    <span class="block"> Farrah liked Admin </span>
                                                    <span class="time">17 minutes ago</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <a class="see-all" href="javascript:void(0);"
                                    >See all notifications<i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item topbar-icon dropdown hidden-caret">
                            <a
                                    class="nav-link"
                                    data-bs-toggle="dropdown"
                                    href="#"
                                    aria-expanded="false"
                            >
                                <i class="fas fa-layer-group"></i>
                            </a>
                            <div class="dropdown-menu quick-actions animated fadeIn">
                                <div class="quick-actions-header">
                                    <span class="title mb-1">Quick Actions</span>
                                    <span class="subtitle op-7">Shortcuts</span>
                                </div>
                                <div class="quick-actions-scroll scrollbar-outer">
                                    <div class="quick-actions-items">
                                        <div class="row m-0">
                                            <a class="col-6 col-md-4 p-0" href="#">
                                                <div class="quick-actions-item">
                                                    <div class="avatar-item bg-danger rounded-circle">
                                                        <i class="far fa-calendar-alt"></i>
                                                    </div>
                                                    <span class="text">Calendar</span>
                                                </div>
                                            </a>
                                            <a class="col-6 col-md-4 p-0" href="#">
                                                <div class="quick-actions-item">
                                                    <div
                                                            class="avatar-item bg-warning rounded-circle"
                                                    >
                                                        <i class="fas fa-map"></i>
                                                    </div>
                                                    <span class="text">Maps</span>
                                                </div>
                                            </a>
                                            <a class="col-6 col-md-4 p-0" href="#">
                                                <div class="quick-actions-item">
                                                    <div class="avatar-item bg-info rounded-circle">
                                                        <i class="fas fa-file-excel"></i>
                                                    </div>
                                                    <span class="text">Reports</span>
                                                </div>
                                            </a>
                                            <a class="col-6 col-md-4 p-0" href="#">
                                                <div class="quick-actions-item">
                                                    <div
                                                            class="avatar-item bg-success rounded-circle"
                                                    >
                                                        <i class="fas fa-envelope"></i>
                                                    </div>
                                                    <span class="text">Emails</span>
                                                </div>
                                            </a>
                                            <a class="col-6 col-md-4 p-0" href="#">
                                                <div class="quick-actions-item">
                                                    <div
                                                            class="avatar-item bg-primary rounded-circle"
                                                    >
                                                        <i class="fas fa-file-invoice-dollar"></i>
                                                    </div>
                                                    <span class="text">Invoice</span>
                                                </div>
                                            </a>
                                            <a class="col-6 col-md-4 p-0" href="#">
                                                <div class="quick-actions-item">
                                                    <div
                                                            class="avatar-item bg-secondary rounded-circle"
                                                    >
                                                        <i class="fas fa-credit-card"></i>
                                                    </div>
                                                    <span class="text">Payments</span>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>

                        <li class="nav-item topbar-user dropdown hidden-caret">
                            <a
                                    class="dropdown-toggle profile-pic"
                                    data-bs-toggle="dropdown"
                                    href="#"
                                    aria-expanded="false"
                            >
                                <div class="avatar-sm">
                                    <img
                                            src="../assets/img/profile.jpg"
                                            alt="..."
                                            class="avatar-img rounded-circle"
                                    />
                                </div>
                                <span class="profile-username">
                      <span class="op-7">Hi,</span>
                      <span class="fw-bold">Hizrian</span>
                    </span>
                            </a>
                            <ul class="dropdown-menu dropdown-user animated fadeIn">
                                <div class="dropdown-user-scroll scrollbar-outer">
                                    <li>
                                        <div class="user-box">
                                            <div class="avatar-lg">
                                                <img
                                                        src="../assets/img/profile.jpg"
                                                        alt="image profile"
                                                        class="avatar-img rounded"
                                                />
                                            </div>
                                            <div class="u-text">
                                                <h4>Hizrian</h4>
                                                <p class="text-muted">hello@example.com</p>
                                                <a
                                                        href="profile.html"
                                                        class="btn btn-xs btn-secondary btn-sm"
                                                >View Profile</a
                                                >
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">My Profile</a>
                                        <a class="dropdown-item" href="#">My Balance</a>
                                        <a class="dropdown-item" href="#">Inbox</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Account Setting</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Logout</a>
                                    </li>
                                </div>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- End Navbar -->
        </div>

        <div class="container">
            <div class="page-inner">
                <%--                <%--%>
                <%--                    String message = request.getAttribute("successMessage").toString();--%>
                <%--                    if (!message.isEmpty()) {--%>
                <%--                %>--%>
                <%--                <p><%=message%></p>--%>
                <%--                <%}%>--%>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <div class="d-flex align-items-center">
                                    <h4 class="card-title">Danh sách hàng đơn hàng</h4>

                                    <button
                                            class="btn btn-primary btn-round ms-auto"
                                            data-bs-toggle="modal"
                                            data-bs-target="#addRowModal"
                                    >
                                        <i class="fa fa-plus"></i>
                                        Add Row
                                    </button>
                                </div>
                            </div>
                            <div class="card-body">
                                <!-- Modal -->
                                <div
                                        class="modal fade"
                                        id="addRowModal"
                                        tabindex="-1"
                                        role="dialog"
                                        aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header border-0">
                                                <h5 class="modal-title">
                                                    <span class="fw-mediumbold"> New</span>
                                                    <span class="fw-light"> Row </span>
                                                </h5>

                                            </div>
                                            <form method="post" action="admin">
                                                <input type="hidden" name="action" value="createOrder"/>

                                                <div class="modal-body">
                                                    <p class="small">
                                                        Create a new row using this form, make sure you
                                                        fill them all
                                                    </p>

                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <div class="form-group form-group-default">
                                                                <label>User Id</label>
                                                                <input
                                                                        id="addName"
                                                                        type="text"
                                                                        class="form-control"
                                                                        placeholder="User id"
                                                                        name="userId"
                                                                        required
                                                                />
                                                            </div>
                                                            <div class="form-group form-group-default">
                                                                <label>Order date</label>
                                                                <input
                                                                        id="order_date"
                                                                        type="date"
                                                                        class="form-control"
                                                                        placeholder="User id"
                                                                        name="order_date"
                                                                        required
                                                                />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6 pe-0">
                                                            <div class="form-group form-group-default">
                                                                <label>Total amount</label>
                                                                <input
                                                                        id="addPosition"
                                                                        type="text"
                                                                        class="form-control"
                                                                        placeholder="Total Amounts"
                                                                        name="totalAmount"
                                                                        required
                                                                />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group form-group-default">
                                                                <label>Order status</label>
                                                                <input
                                                                        id="addOffice"
                                                                        type="text"
                                                                        class="form-control"
                                                                        placeholder="fill office"
                                                                        name="orderStatus"
                                                                        required
                                                                />

                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                `
                                                <div class="modal-footer border-0">
                                                    <button
                                                            type="submit"
                                                            class="btn btn-primary">
                                                        Add
                                                    </button>

                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                                <div class="table-responsive">

                                    <table id="add-row" class="display table table-striped table-hover">
                                        <thead>
                                        <tr>
                                            <th>Mã Đơn Hàng</th>
                                            <th>Mã khách hàng</th>
                                            <th>Ngày đặt hàng</th>
                                            <th>Tổng tiền</th>
                                            <th>Trạng thái đơn hàng</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tfoot>
                                        <tr>
                                            <th>Mã Đơn Hàng</th>
                                            <th>Mã khách hàng</th>
                                            <th>Ngày đặt hàng</th>
                                            <th>Tổng tiền</th>
                                            <th>Trạng thái đơn hàng</th>
                                            <th>Action</th>
                                        </tr>
                                        </tfoot>
                                        <tbody>
                                        <% List<Order> listOrders = (List<Order>) request.getAttribute("listOrders");
                                            String er = (String) request.getAttribute("error"); // Cast to String directly
                                        %>

                                        <% if (listOrders != null && !listOrders.isEmpty()) { %>
                                        <% for (Order order : listOrders) { %>
                                        <tr>
                                            <td><%= order.getOrderId() %>
                                            </td>
                                            <td><%= order.getUser().getUserId() %>
                                            </td>
                                            <td><%= order.getOrderDate() %>
                                            </td>
                                            <td><%= order.getTotalAmount() %>
                                            </td>
                                            <td><%= order.getOrderStatus() %>
                                            </td>
                                            <td>
                                                <div class="form-button-action">
                                                    <button type="button" title="Edit Task"
                                                            class="btn btn-link btn-primary btn-lg"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#editOrder<%= order.getOrderId() %>">
                                                        <i class="fa fa-edit"></i>
                                                    </button>
                                                    <form action="${pageContext.request.contextPath}/admin" method="get"
                                                          style="display:inline;">
                                                        <input type="hidden" name="url" value="deleteOrder"/>
                                                        <input type="hidden" name="orderId"
                                                               value="<%= order.getOrderId() %>"/>
                                                        <button type="submit" class="btn btn-link btn-danger"
                                                                data-bs-toggle="tooltip" title="Xóa">
                                                            <i class="fa fa-times"></i>
                                                        </button>
                                                    </form>

                                                    <!-- Edit Order Modal -->
                                                    <div class="modal fade" id="editOrder<%= order.getOrderId() %>"
                                                         tabindex="-1" role="dialog" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header border-0">
                                                                    <h5 class="modal-title">
                                                                        <span class="fw-mediumbold">Edit</span>
                                                                        <span class="fw-light">Order</span>
                                                                    </h5>
                                                                </div>
                                                                <form method="post" action="admin">
                                                                    <input type="hidden" name="action"
                                                                           value="editOrder"/>
                                                                    <input type="hidden" name="orderId"
                                                                           value="<%= order.getOrderId() %>"/>
                                                                    <div class="modal-body">
                                                                        <p class="small">Edit the details of the
                                                                            order</p>
                                                                        <%-- Display error message if exists --%>
                                                                        <% if (er != null && !er.isEmpty()) { %>
                                                                        <p class="text-danger"><%= er %>
                                                                        </p>
                                                                        <% } %>
                                                                        <div class="row">
                                                                            <div class="col-sm-12">
                                                                                <div class="form-group form-group-default">
                                                                                    <label>User Id</label>
                                                                                    <input type="text"
                                                                                           class="form-control"
                                                                                           name="userId"
                                                                                           value="<%= order.getUser().getUserId() %>"/>
                                                                                </div>
                                                                                <div class="form-group form-group-default">
                                                                                    <label>Order Date</label>
                                                                                    <input type="date"
                                                                                           class="form-control"
                                                                                           name="orderDate"
                                                                                           value="<%= order.getOrderDate() %>"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-6 pe-0">
                                                                                <div class="form-group form-group-default">
                                                                                    <label>Total Amount</label>
                                                                                    <input type="text"
                                                                                           class="form-control"
                                                                                           name="totalAmount"
                                                                                           value="<%= order.getTotalAmount() %>"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-6">
                                                                                <div class="form-group form-group-default">
                                                                                    <label>Order Status</label>
                                                                                    <input type="text"
                                                                                           class="form-control"
                                                                                           name="orderStatus"
                                                                                           value="<%= order.getOrderStatus() %>"/>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer border-0">
                                                                        <button type="submit" class="btn btn-primary">
                                                                            Save changes
                                                                        </button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <% } %>
                                        <% } else { %>
                                        <tr>
                                            <td colspan="5">Không có đơn hàng nào.</td>
                                        </tr>
                                        <% } %>


                                        </tbody>
                                    </table>
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
                        <a class="nav-link" href="http://www.themekita.com">
                            ThemeKita
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
                <a href="http://www.themekita.com">ThemeKita</a>
            </div>
            <div>
                Distributed by
                <a target="_blank" href="https://themewagon.com/">ThemeWagon</a>.
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
                            class="selected changeLogoHeaderColor"
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
                            class="changeTopBarColor"
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
                            class="selected changeTopBarColor"
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
                            class="selected changeSideBarColor"
                            data-color="white"
                    ></button>
                    <button
                            type="button"
                            class="changeSideBarColor"
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
<script src="../assets/js/core/jquery-3.7.1.min.js"></script>
<script src="../assets/js/core/popper.min.js"></script>
<script src="../assets/js/core/bootstrap.min.js"></script>

<!-- jQuery Scrollbar -->
<script src="../assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>

<script src="../assets/js/plugin/datatables/datatables.min.js"></script>
<!-- Kaiadmin JS -->
<script src="../assets/js/kaiadmin.min.js"></script>
<!-- Kaiadmin DEMO methods, don't include it in your project! -->
<script src="../assets/js/setting-demo2.js"></script>
<script>
    $(document).ready(function () {
        $("#basic-datatables").DataTable({});

        $("#multi-filter-select").DataTable({
            pageLength: 5,
            initComplete: function () {
                this.api()
                    .columns()
                    .every(function () {
                        var column = this;
                        var select = $(
                            '<select class="form-select"><option value=""></option></select>'
                        )
                            .appendTo($(column.footer()).empty())
                            .on("change", function () {
                                var val = $.fn.dataTable.util.escapeRegex($(this).val());

                                column
                                    .search(val ? "^" + val + "$" : "", true, false)
                                    .draw();
                            });

                        column
                            .data()
                            .unique()
                            .sort()
                            .each(function (d, j) {
                                select.append(
                                    '<option value="' + d + '">' + d + "</option>"
                                );
                            });
                    });
            },
        });

        // Add Row
        $("#add-row").DataTable({
            pageLength: 5,
        });

        var action =
            '<td> <div class="form-button-action"> <button type="button" data-bs-toggle="tooltip" title="" class="btn btn-link btn-primary btn-lg" data-original-title="Edit Task"> <i class="fa fa-edit"></i> </button> <button type="button" data-bs-toggle="tooltip" title="" class="btn btn-link btn-danger" data-original-title="Remove"> <i class="fa fa-times"></i> </button> </div> </td>';

        $("#addRowButton").click(function () {
            $("#add-row")
                .dataTable()
                .fnAddData([
                    $("#addName").val(),
                    $("#addPosition").val(),
                    $("#addOffice").val(),
                    action,
                ]);
            $("#addRowModal").modal("hide");
        });
    });
    setTimeout(function() {
        var successMessageDiv = document.getElementById('successMessage');
        if (successMessageDiv) {
            successMessageDiv.style.display = 'none';
        }
    }, 3000);
</script>
</body>
</html>

