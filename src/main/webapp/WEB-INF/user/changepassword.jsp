<%@page import="com.quach.shop_giay.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css"
            rel="stylesheet"
    />
    <!-- MDB -->

</head>
<style>
    .red {
        color: red;
    }

    .gradient-custom {
        /* fallback for old browsers */
        background: #6a11cb;
        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1),
        rgba(37, 117, 252, 1));
        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right, rgba(106, 17, 203, 1),
        rgba(37, 117, 252, 1))
    }

</style>
<body>

<%
    Object obj = session.getAttribute("checkLogin");
    String currentPasswor = request.getAttribute("currentPassword") + "";
    currentPasswor = (currentPasswor.equals("null")) ? "" : currentPasswor;
    String passwordChange = request.getAttribute("passwordChange") + "";
    passwordChange = (passwordChange.equals("null")) ? "" : passwordChange;
    String passwordConfirm = request.getAttribute("passwordConfirm") + "";
    passwordConfirm = (passwordConfirm.equals("null")) ? "" : passwordConfirm;
    String userName = request.getAttribute("userName") + "";
    userName = (userName.equals("null")) ? "" : userName;
    User user = null;
    if (obj != null)
        user = (User) obj;
    if (user == null) {
%>
<h1>You are not logged into the web site. Please go back to the
    home page</h1>
<a href="login.jsp">Login</a>
<%
} else {
    String msgError = request.getAttribute("msgError") + "";
    if (msgError.equals("null")) {
        msgError = "";
    }
%>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div
                class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Change Password</h2>

                            <span class="red"><%=msgError%></span>
                            <form action="customer" method="post">
                                <input type="hidden" name="act" value="changePassword">
                                <div class="form-outline form-white mb-4">
                                    <input type="text" id="userName" value="<%=userName%>"
                                           class="form-control form-control-lg" name="userName"/> <label
                                        class="form-label" for="userName">Username</label>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="currentPassword"
                                           name="currentPassword" value="<%=currentPasswor%>"
                                           class="form-control form-control-lg"/> <label
                                        class="form-label" for="currentPassword">Current
                                    Password</label>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="passwordChange"
                                           value="<%=passwordChange%>" name="passwordChange"
                                           class="form-control form-control-lg"/> <label
                                        class="form-label" for="passwordChange">Password
                                    Change</label>
                                </div>
                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="passwordConfirm"
                                           value="<%=passwordConfirm%>" name="passwordConfirm"
                                           class="form-control form-control-lg"/> <label
                                        class="form-label" for="passwordConfirm">Password
                                    Confirm</label>
                                </div>


                                <button
                                        class="btn btn-outline-light btn-lg px-5 form-control mb-3"
                                        type="submit">Change
                                </button>
                                <p class="small mb-3 pb-lg-2">
                                    <a class="text-white-50" href="myweb.jsp">Go Back</a>
                                </p>
                            </form>

                        </div>

                        <div>


                            <p class="mb-0">
                                Don't have an account? <a href="register"
                                                          class="text-white-50 fw-bold">Sign Up</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%
    }
%>

<script type="text/javascript" src="../../js/mdb.umd.min.js"></script>
</body>
</html>