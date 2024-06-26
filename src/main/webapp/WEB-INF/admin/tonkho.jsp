

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
      <nav
              class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom"
      >
        <div class="container-fluid">
          <nav
                  class="navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex"
          >
            <div class="input-group">
              <div class="input-group-prepend">
                <button type="submit" class="btn btn-search pe-1">
                  <i class="fa fa-search search-icon"></i>
                </button>
              </div>
              <input
                      type="text"
                      placeholder="Search ..."
                      class="form-control"
              />
            </div>
          </nav>

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
                  <h4 class="card-title">Danh sách hàng tồn kho</h4>
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
                        <form>
                          <div class="row">
                            <div class="col-sm-12">
                              <div class="form-group form-group-default">
                                <label>Name</label>
                                <input
                                        id="addName"
                                        type="text"
                                        class="form-control"
                                        placeholder="fill name"
                                />
                              </div>
                            </div>
                            <div class="col-md-6 pe-0">
                              <div class="form-group form-group-default">
                                <label>Position</label>
                                <input
                                        id="addPosition"
                                        type="text"
                                        class="form-control"
                                        placeholder="fill position"
                                />
                              </div>
                            </div>
                            <div class="col-md-6">
                              <div class="form-group form-group-default">
                                <label>Office</label>
                                <input
                                        id="addOffice"
                                        type="text"
                                        class="form-control"
                                        placeholder="fill office"
                                />
                              </div>
                            </div>
                          </div>
                        </form>
                      </div>
                      <div class="modal-footer border-0">
                        <button
                                type="submit"
                                class="btn btn-primary"

                        >
                          Add
                        </button>
                        <button
                                type="button"
                                class="btn btn-danger"
                                data-dismiss="modal"
                        >
                          Close
                        </button>
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
                      <th>Name</th>
                      <th>Position</th>
                      <th>Office</th>
                      <th style="width: 10%">Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                      <th>Name</th>
                      <th>Position</th>
                      <th>Office</th>
                      <th>Action</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr>
                      <td>Tiger Nixon</td>
                      <td>System Architect</td>
                      <td>Edinburgh</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Garrett Winters</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Ashton Cox</td>
                      <td>Junior Technical Author</td>
                      <td>San Francisco</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Cedric Kelly</td>
                      <td>Senior Javascript Developer</td>
                      <td>Edinburgh</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Airi Satou</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Brielle Williamson</td>
                      <td>Integration Specialist</td>
                      <td>New York</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Herrod Chandler</td>
                      <td>Sales Assistant</td>
                      <td>San Francisco</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Rhona Davidson</td>
                      <td>Integration Specialist</td>
                      <td>Tokyo</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Colleen Hurst</td>
                      <td>Javascript Developer</td>
                      <td>San Francisco</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>Sonya Frost</td>
                      <td>Software Engineer</td>
                      <td>Edinburgh</td>
                      <td>
                        <div class="form-button-action">
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-primary btn-lg"
                                  data-original-title="Edit Task"
                          >
                            <i class="fa fa-edit"></i>
                          </button>
                          <button
                                  type="button"
                                  data-bs-toggle="tooltip"
                                  title=""
                                  class="btn btn-link btn-danger"
                                  data-original-title="Remove"
                          >
                            <i class="fa fa-times"></i>
                          </button>
                        </div>
                      </td>
                    </tr>
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

