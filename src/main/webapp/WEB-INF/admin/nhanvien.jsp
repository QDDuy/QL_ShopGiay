<%@ page import="com.quach.shop_giay.model.Employees" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>Datatables - Kaiadmin Bootstrap 5 Admin Dashboard</title>
  <meta
          content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
          name="viewport"
  />
  <link
          rel="icon"
          href="../assets/img/kaiadmin/favicon.ico"
          type="image/x-icon"
  />

  <!-- Fonts and icons -->
  <script src="../assets/js/plugin/webfont/webfont.min.js"></script>
  <script>
    WebFont.load({
      google: { families: ["Public Sans:300,400,500,600,700"] },
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
  <link rel="stylesheet" href="../assets/css/bootstrap.min.css" />
  <link rel="stylesheet" href="../assets/css/plugins.min.css" />
  <link rel="stylesheet" href="../assets/css/kaiadmin.min.css" />

  <!-- CSS Just for demo purpose, don't include it in your project -->
  <link rel="stylesheet" href="../assets/css/demo.css" />
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
      <jsp:include page="../../layouts/navbaradmin.jsp"></jsp:include>

      <!-- End Navbar -->
    </div>

    <div class="container">
      <div class="page-inner">
        <div class="page-header">
          <h3 class="fw-bold mb-3">DataTables.Net</h3>
          <ul class="breadcrumbs mb-3">
            <li class="nav-home">
              <a href="#">
                <i class="icon-home"></i>
              </a>
            </li>
            <li class="separator">
              <i class="icon-arrow-right"></i>
            </li>
            <li class="nav-item">
              <a href="#">Tables</a>
            </li>
            <li class="separator">
              <i class="icon-arrow-right"></i>
            </li>
            <li class="nav-item">
              <a href="#">Datatables</a>
            </li>
          </ul>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <div class="d-flex align-items-center">
                  <h4 class="card-title">Danh sách Nhân Viên</h4>
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
                        aria-hidden="true"
                >
                  <div class="modal-dialog" role="document">
                    <div class="modal-content">
                      <div class="modal-header border-0">
                        <h5 class="modal-title">
                          <span class="fw-mediumbold"> New</span>
                          <span class="fw-light"> Row </span>
                        </h5>
                        <button
                                type="button"
                                class="close"
                                data-dismiss="modal"
                                aria-label="Close"
                        >
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <div class="modal-body">
                        <p class="small">
                          Create a new row using this form, make sure you
                          fill them all
                        </p>
                        <form method="post" action="admin">
                          <input type="hidden" name="action" value="createEmployee" />
                          <div class="modal-body">
                            <p class="small">
                              Create a new employee using this form, make sure you fill them all
                            </p>
                            <div class="row">
                              <div class="col-sm-12">
                                <div class="form-group form-group-default">
                                  <label>Account ID</label>
                                  <input
                                          id="accountId"
                                          type="text"
                                          class="form-control"
                                          placeholder="Account ID"
                                          name="accountId"
                                  />
                                </div>
                                <div class="form-group form-group-default">
                                  <label>Full Name</label>
                                  <input
                                          id="fullname"
                                          type="text"
                                          class="form-control"
                                          placeholder="Full Name"
                                          name="fullname"
                                  />
                                </div>
                                <div class="form-group form-group-default">
                                  <label>Address</label>
                                  <input
                                          id="address"
                                          type="text"
                                          class="form-control"
                                          placeholder="Address"
                                          name="address"
                                  />
                                </div>
                                <div class="form-group form-group-default">
                                  <label>Age</label>
                                  <input
                                          id="age"
                                          type="number"
                                          class="form-control"
                                          placeholder="Age"
                                          name="age"
                                  />
                                </div>
                                <div class="form-group form-group-default">
                                  <label>Gender</label>
                                  <input
                                          id="gender"
                                          type="text"
                                          class="form-control"
                                          placeholder="Gender"
                                          name="gender"
                                  />
                                </div>
                                <div class="form-group form-group-default">
                                  <label>Phone</label>
                                  <input
                                          id="phone"
                                          type="text"
                                          class="form-control"
                                          placeholder="Phone"
                                          name="phone"
                                  />
                                </div>
                                <div class="form-group form-group-default">
                                  <label>Email</label>
                                  <input
                                          id="email"
                                          type="email"
                                          class="form-control"
                                          placeholder="Email"
                                          name="email"
                                  />
                                </div>
                                <div class="form-group form-group-default">
                                  <label>Salary</label>
                                  <input
                                          id="salary"
                                          type="text"
                                          class="form-control"
                                          placeholder="Salary"
                                          name="salary"
                                  />
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="modal-footer border-0">
                            <button type="submit" class="btn btn-primary">Add</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                          </div>
                        </form>

                      </div>

                    </div>
                  </div>
                </div>

                <div class="table-responsive">
                  <table
                          id="add-row"
                          class="display table table-striped table-hover"
                  >
                    <thead>
                    <tr>
                      <th>ID EMP</th>
                      <th>ID ACC</th>
                      <th>FULL name</th>
                      <th>ADDress</th>
                      <th>Email</th>
                      <th>Phone</th>
                      <th>Gender</th>
                      <th>Age</th>
                      <th style="width: 10%">Salary</th>
                      <th>Control</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                      <th>ID EMP</th>
                      <th>ID ACC</th>
                      <th>Full Name</th>
                      <th>Address</th>
                      <th>Email</th>
                      <th>Phone</th>
                      <th>Gender</th>
                      <th>Age</th>
                      <th>Salary</th>
                      <th>Control</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <% List<Employees> listemp = (List<Employees>) request.getAttribute("listemp");
                      if (listemp != null && !listemp.isEmpty()) {
                        for (Employees emp : listemp) {
                    %>
                    <tr>
                      <td><%= emp.getIdEmploye() %></td>
                      <td><%= emp.getTaikhoan().getAccountId() %></td>
                      <td><%= emp.getFullname() %></td>
                      <td><%= emp.getAddress() %></td>
                      <td><%= emp.getEmail() %></td>
                      <td><%= emp.getPhone() %></td>
                      <td><%= emp.getGender() %></td>
                      <td><%= emp.getAge() %></td>
                      <td><%= emp.getLuong() %></td>
                      <td>
                        <div class="form-button-action">
                          <form action="${pageContext.request.contextPath}/admin" method="get" style="display:inline;">
                            <input type="hidden" name="url" value="deleteEmployee" />
                            <input type="hidden" name="id_employe" value="<%= emp.getIdEmploye() %>" />
                            <button type="submit" class="btn btn-link btn-danger" data-bs-toggle="tooltip" title="Xóa">
                              <i class="fa fa-times"></i>
                            </button>
                          </form>
                          <button type="button" title="Edit Employee" class="btn btn-link btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#editEmployee<%= emp.getIdEmploye() %>">
                            <i class="fa fa-edit"></i>
                          </button>

                          <div class="modal fade" id="editEmployee<%= emp.getIdEmploye() %>" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                              <div class="modal-content">
                                <div class="modal-header border-0">
                                  <h5 class="modal-title">
                                    <span class="fw-mediumbold">Chỉnh sửa</span>
                                    <span class="fw-light">Nhân viên</span>
                                  </h5>
                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                  </button>
                                </div>
                                <form method="post" action="admin">
                                  <input type="hidden" name="action" value="editEmployee" />
                                  <input type="hidden" name="id_employe" value="<%= emp.getIdEmploye() %>" />
                                  <div class="modal-body">
                                    <p class="small">Chỉnh sửa thông tin nhân viên</p>
                                    <div class="row">
                                      <div class="col-sm-12">
                                        <div class="form-group form-group-default">
                                          <label>Tài khoản</label>
                                          <input type="text" class="form-control" name="id_taikhoan" value="<%= emp.getTaikhoan().getAccountId() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Họ và tên</label>
                                          <input type="text" class="form-control" name="fullname" value="<%= emp.getFullname() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Địa chỉ</label>
                                          <input type="text" class="form-control" name="address" value="<%= emp.getAddress() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Email</label>
                                          <input type="email" class="form-control" name="email" value="<%= emp.getEmail() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Số điện thoại</label>
                                          <input type="text" class="form-control" name="phone" value="<%= emp.getPhone() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Giới tính</label>
                                          <input type="text" class="form-control" name="gender" value="<%= emp.getGender() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Tuổi</label>
                                          <input type="number" class="form-control" name="age" value="<%= emp.getAge() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Lương</label>
                                          <input type="number" class="form-control" name="luong" value="<%= emp.getLuong() %>" />
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                  <div class="modal-footer border-0">
                                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                  </div>
                                </form>
                              </div>
                            </div>
                          </div>
                        </div>
                      </td>
                    </tr>
                    <% }
                    } else { %>
                    <tr>
                      <td colspan="10">Không có nhân viên nào.</td>
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
            <br />
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
            <br />
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
<!-- Datatables -->
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
</script>
</body>
</html>

