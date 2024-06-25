<%@page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Register</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>

<style>
    .red {
        color: red;
    }

    #msg {
        color: red;
    }
</style>

<body>
<%
    String msgError = (request.getAttribute("msgError") + "");
    msgError = (msgError.equals("null")) ? "" : msgError;

    String userName = request.getAttribute("userName") + "";
    userName = (userName.equals("null")) ? "" : userName;

    String fullName = request.getAttribute("fullName") + "";
    fullName = (fullName.equals("null")) ? "" : fullName;


    String address = request.getAttribute("address") + "";
    address = (address.equals("null")) ? "" : address;


    String phone = request.getAttribute("phone") + "";
    phone = (phone.equals("null")) ? "" : phone;

    String email = request.getAttribute("email") + "";
    email = (email.equals("null")) ? "" : email;
    String avatar=request.getAttribute("avatar")+"";
    avatar=(avatar.equals("null"))?"":avatar;

%>
<div class="container">
    <h1 class="text-center mt-3">Register Account</h1>
    <div class="red" id="msgError">
        <%=msgError%>
    </div>
    <form action="user" method="post">
        <input type="hidden" name="act" value="register">
        <div class="row">
            <div class="">
                <h3>Account.</h3>
                <div class="mb-3">
                    <label for="userName" class="form-label">User Name <span
                            class=red>*</span></label> <input type="text" class="form-control"
                                                              id="userName" name="userName" required="required"
                                                              value="<%=userName%>">
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password <span
                            class=red>*</span></label> <span class="msg"></span> <input
                        type="password" class="form-control" id="password"
                        name="password" required="required">
                </div>
                <div class="mb-3">
                    <label for="passwordConfirm" class="form-label">Password
                        Confirm <span class=red>*</span>
                    </label><span id="msg" class="red"></span> <input type="password"
                                                                      class="form-control" id="passwordConfirm"
                                                                      name="passwordConfirm"
                                                                      required="required" onkeyup="checkPassword()">
                </div>
            </div>
            <hr>

            <div class="">
                <h3>Customer Info.</h3>
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full name</label> <input
                        type="text" class="form-control" id="fullName" name="fullName"
                        value="<%=fullName%>">
                </div>

                <div class="mb-3">
                    <label for="address" class="form-label">Address</label> <input
                        type="text" class="form-control" id="address" name="address"
                        value="<%=address%>">
                </div>


                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label> <input
                        type="tel" class="form-control" id="phone" name="phone"
                        value="<%=phone%>">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label> <input
                        type="email" class="form-control" id="email" name="email"
                        value="<%=email%>">
                </div>
                <div class="mb-3">
                    <label for="avatar" class="form-label">Avatar</label> <input
                        type="text" class="form-control" id="avatar" name="avatar"
                        value="<%=avatar%>">
                </div>
                </div>

                <input type="hidden" class="form-control " id="role" name="role"
                       value="user">


                <button type="submit" class="btn btn-primary form-control"
                        id="submit">Register
                </button>
            </div>
        </div>
    </form>
</div>

<script src="../js/main.js"></script>
</body>
</html>