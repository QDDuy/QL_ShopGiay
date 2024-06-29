<%@ page import="com.quach.shop_giay.model.Account" %><%--
  Created by IntelliJ IDEA.
  User: quach
  Date: 28/06/2024
  Time: 12:06 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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



            <%Account account= (Account) session.getAttribute("checkLogin");
                if(account!=null){%>
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
                      <span class="op-7">Chào,</span>
                      <span class="fw-bold">Bạn</span>
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
                            </div>
                        </li>
                        <li>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="user?act=logout">Logout</a>
                        </li>
                    </div>
                </ul>
                <%}%>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
