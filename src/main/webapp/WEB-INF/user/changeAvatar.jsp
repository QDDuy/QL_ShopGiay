<%@page import="java.util.Date" %>
<%@page import="com.quach.shop_giay.model.User" %>
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
    Object obj = session.getAttribute("checkLogin");
    User user = null;
    if (obj != null) {
        user = (User) obj;
    }
    if (user == null) {
%>
<h1>You are logged into the web site</h1>
<%
} else {
    String msgError = request.getAttribute("msgError") + "";
    if (msgError.equals("null")) {
        msgError = "";
    }
    String avatar=user.getAvatar();
%>
<div class="container">
    <h1 class="text-center mt-3">Information user</h1>
    <div class=" text-center green" id="msgError">
        <%=msgError%>
    </div>
    <form action="changeAvatar" method="post" enctype="multipart/form-data">
        <input type="hidden" name="act" value="changeAvatar">
        <div class="row">
            <div class="">
                <h3>User Info.</h3>
                <img src="../../img/avatar/<%=avatar%>" alt="" width="200" height="200">
                <div class="mb-3">
                    <label for="avatar" class="form-label success">Full name</label>
                    <input type="file" class="form-control" id="avatar" name="avatar" value="">
                </div>



                <button type="submit" class="btn btn-primary form-control" id="submit">Change</button>
            </div>
        </div>
    </form>
    <a href="home">
        <button class="btn btn-primary  mt-2 mb-5">Go back</button>
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