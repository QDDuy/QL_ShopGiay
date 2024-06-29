<%@ page import="java.util.List" %>
<%@ page import="com.quach.shop_giay.model.Order" %>
<%@ page import="com.quach.shop_giay.model.Brand" %>

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
      <jsp:include page="../../layouts/navbaradmin.jsp"></jsp:include>

    </div>

    <div class="container">
      <div class="page-inner">

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
                        <button
                                type="button"
                                class="close"
                                data-dismiss="modal"
                                aria-label="Close"
                        >
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
                      <form method="post" action="admin">
                        <input type="hidden" name="action" value="createBrand" />

                        <div class="modal-body">
                          <p class="small">
                            Create a new row using this form, make sure you
                            fill them all
                          </p>

                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Brand Name</label>
                                <input
                                        id="brandName"
                                        type="text"
                                        class="form-control"
                                        placeholder="Brand name"
                                        name="brand_name"
                                        required
                                />
                              </div>
                              <div class="form-group form-group-default">
                                <label>Image URL</label>
                                <input
                                        id="brandImage"
                                        type="text"
                                        class="form-control"
                                        placeholder="Image URL"
                                        name="image"
                                        required
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

                <div class="table-responsive">
                  <table id="add-row" class="display table table-striped table-hover">
                    <thead>
                    <tr>
                      <th>Mã thương hiệu</th>
                      <th>Tên thương hiệu</th>
                      <th>Hình ảnh</th>
                      <th>Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                      <th>Mã thương hệu</th>
                      <th>Tên thương hiệu</th>
                      <th>Hình ảnh</th>
                      <th>Action</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <% List<Brand> listBrand = (List<Brand>) request.getAttribute("listbrand");
                      if (listBrand != null && !listBrand.isEmpty()) {
                        for (Brand brand : listBrand) {
                    %>
                    <tr>
                      <td><%= brand.getBrandId() %></td>
                      <td><%= brand.getBrandName() %></td>
                      <td><%= brand.getBrandImage() %></td>
                      <td>
                        <div class="form-button-action">
                          <form action="${pageContext.request.contextPath}/admin" method="get" style="display:inline;">
                            <input type="hidden" name="url" value="deleteBrand" />
                            <input type="hidden" name="brandId" value="<%= brand.getBrandId() %>" />
                            <button type="submit" class="btn btn-link btn-danger" data-bs-toggle="tooltip" title="Xóa">
                              <i class="fa fa-times"></i>
                            </button>
                          </form>
                          <button type="button" title="Edit Task" class="btn btn-link btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#editOrder<%= brand.getBrandId() %>">
                            <i class="fa fa-edit"></i>
                          </button>

                          <div class="modal fade" id="editOrder<%= brand.getBrandId() %>" tabindex="-1" role="dialog" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                              <div class="modal-content">
                                <div class="modal-header border-0">
                                  <h5 class="modal-title">
                                    <span class="fw-mediumbold">Edit</span>
                                    <span class="fw-light">Brand</span>
                                  </h5>
                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                  </button>
                                </div>
                                <form method="post" action="admin">
                                  <input type="hidden" name="action" value="editBrand" />
                                  <input type="hidden" name="brand_id" value="<%= brand.getBrandId() %>" />
                                  <div class="modal-body">
                                    <p class="small">Edit the details of the brand</p>
                                    <div class="row">
                                      <div class="col-sm-12">
                                        <div class="form-group form-group-default">
                                          <label>Brand Name</label>
                                          <input type="text" class="form-control" name="brand_name" value="<%= brand.getBrandName() %>" />
                                        </div>
                                        <div class="form-group form-group-default">
                                          <label>Image</label>
                                          <input type="text" class="form-control" name="image" value="<%= brand.getBrandImage() %>" />
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                  <div class="modal-footer border-0">
                                    <button type="submit" class="btn btn-primary">Save changes</button>
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
                      <td colspan="5">Thương hiệu chưa được thêm vào.</td>
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

