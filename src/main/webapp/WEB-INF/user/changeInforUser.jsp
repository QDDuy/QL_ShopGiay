<%@page import="java.util.Date" %>
<%@page import="com.quach.shop_giay.model.User" %>
<%@ page import="com.quach.shop_giay.model.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quach.shop_giay.database.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<style>
    .green {
        color: green;
    }
</style>
<body>
<jsp:include page="../../layouts/header.jsp"></jsp:include>
<%
    // Get the login check object from the session
    Object obj = session.getAttribute("checkLogin");
    Account account = null;
    if (obj != null) {
        account = (Account) obj;
    }

    // Check if the user is logged in
    if (account == null) {
%>
<!-- Display a message if the user is not logged in -->
<h1>You are not logged into the website</h1>
<%
} else {
    // Get the error message attribute from the request
    String msgError = (String) request.getAttribute("msgError");
    if (msgError == null) {
        msgError = "";
    }
    User user= (User) request.getAttribute("userLoging");
    // Retrieve user details using UserDAO
// Ensure getIdTk sets user details appropriately
    String userId=user.getUserId();
    String fullName = user.getFullName();
    String address = user.getAddress();
    String phone = user.getPhone();
    String email = user.getEmail();
%>
<div class="container">
    <h1 class="text-center mt-3">Information user</h1>
    <div class=" text-center green" id="msgError">
        <%=msgError%>
    </div>
    <form action="changeInforUser" method="post">
        <input type="hidden" name="userId" value="<%=userId%>">
        <div class="row">
            <div class="">
                <h3>User Info.</h3>
                <div class="mb-3">
                    <label for="fullName" class="form-label success">Full name</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" value="<%=fullName%>">
                </div>

                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="<%=address%>">
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="tel" class="form-control" id="phone" name="phone" value="<%=phone%>">
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%=email%>">
                </div>
                <hr>

                <button type="submit" class="btn btn-primary form-control" id="submit">Change</button>
            </div>
        </div>
    </form>
    <a href="home">
        <button class="btn btn-primary mt-2 mb-5">Go back</button>
    </a>
</div>
<%
    }
%>
<!-- Footer -->
<jsp:include page="../../layouts/footer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="../../js/mdb.umd.min.js"></script>
</html>
