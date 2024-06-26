<%--
  Created by IntelliJ IDEA.
  User: quach
  Date: 26/06/2024
  Time: 10:33 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Sidebar -->
<div class="sidebar" data-background-color="dark">
    <div class="sidebar-logo">
        <!-- Logo Header -->
        <div class="logo-header" data-background-color="dark">
            <a href="index.html" class="logo">
                <img
                        src="../../assets/img/kaiadmin/logo_light.svg"
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
    <div class="sidebar-wrapper scrollbar scrollbar-inner">
        <div class="sidebar-content">
            <ul class="nav nav-secondary">
                <li class="nav-item active">
                    <a
                            data-bs-toggle="collapse"
                            href="#dashboard"
                            class="collapsed"
                            aria-expanded="false"
                    >
                        <i class="fas fa-home"></i>
                        <p>Dashboard</p>
                        <span class="caret"></span>
                    </a>

                </li>
                <li class="nav-section">
                <span class="sidebar-mini-icon">
                  <i class="fa fa-ellipsis-h"></i>
                </span>
                    <h4 class="text-section">Components</h4>
                </li>
                <li class="nav-item">
                    <a data-bs-toggle="collapse" href="#base">
                        <i class="fas fa-layer-group"></i>
                        <p>Quản Lý Sản Phẩm</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="base">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="admin?url=product">
                                    <span class="sub-item">Sản Phẩm</span>
                                </a>
                            </li>

                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a data-bs-toggle="collapse" href="#sidebarLayouts">
                        <i class="fas fa-th-list"></i>
                        <p>Quản lý kho</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="sidebarLayouts">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="admin?url=kho">
                                    <span class="sub-item">Kho</span>
                                </a>
                            </li>
                            <li>
                                <a href="admin?url=tonkho">
                                    <span class="sub-item">Tồn kho</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a data-bs-toggle="collapse" href="#maps">
                        <i class="fas fa-map-marker-alt"></i>
                        <p>Quản lý đơn hàng</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="maps">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="admin?url=donhang">
                                    <span class="sub-item">Đơn hàng</span>
                                </a>
                            </li>
                            <li>
                                <a href="admin?url=chitietdonhang">
                                    <span class="sub-item">Chi tiết đơn hàng</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a data-bs-toggle="collapse" href="#nhanvien">
                        <i class="far fa-chart-bar"></i>
                        <p>Nhân Viên</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="nhanvien">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="admin?url=nhanvien">
                                    <span class="sub-item">Nhân Viên</span>
                                </a>
                            </li>

                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a data-bs-toggle="collapse" href="#khachhang">
                        <i class="far fa-chart-bar"></i>
                        <p>Khách Hàng</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="khachhang">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="admin?url=khachhang">
                                    <span class="sub-item">Khách Hàng</span>
                                </a>
                            </li>

                        </ul>
                    </div>
                </li><li class="nav-item">
                <a data-bs-toggle="collapse" href="#taikhoan">
                    <i class="fa-solid fa-user"></i>
                    <p>Quản lý Tài khoản</p>
                    <span class="caret"></span>
                </a>
                <div class="collapse" id="taikhoan">
                    <ul class="nav nav-collapse">
                        <li>
                            <a href="admin?url=khachhang">
                                <span class="sub-item">Tài khoản</span>
                            </a>
                        </li>

                    </ul>
                </div>
            </li>

                <li class="nav-item">
                    <a data-bs-toggle="collapse" href="#forms">
                        <i class="fas fa-pen-square"></i>
                        <p>Forms</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="forms">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="forms/forms.html">
                                    <span class="sub-item">Basic Form</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item">
                    <a data-bs-toggle="collapse" href="#tables">
                        <i class="fas fa-table"></i>
                        <p>Tables</p>
                        <span class="caret"></span>
                    </a>
                    <div class="collapse" id="tables">
                        <ul class="nav nav-collapse">
                            <li>
                                <a href="tables/tables.html">
                                    <span class="sub-item">Basic Table</span>
                                </a>
                            </li>
                            <li>
                                <a href="tables/datatables.html">
                                    <span class="sub-item">Datatables</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- End Sidebar -->
</body>
</html>
