<%@page import="com.quach.shop_giay.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%--   <link
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
       <!-- MDB -->--%>

    <link rel="icon" type="image/png" href="../../img/login/icons/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="../../vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="../../vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="../../vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/login/util.css">
    <link rel="stylesheet" type="text/css" href="../../css/login/main.css">

</head>
<style>
    .red {
        color: red;
    }


</style>
<body>
<%
    String msgError = (String) request.getAttribute("msgError");
    String msgErro = (msgError != null) ? msgError : "";

%>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="https://i.pinimg.com/originals/93/94/13/939413e47ea9fa0244b72ce32c4326fb.jpg" alt="IMG">
            </div>

            <form action="user" method="post" class="login100-form validate-form">
					<span class="login100-form-title">
						<%=msgErro%> fgdgdfgdfgdfgd
					</span>
                <input type="hidden" name="act" value="login">
                <div class="wrap-input100 validate-input" data-validate="Valid user name is required: abcdxyz">
                    <input class="input100" type="text" name="userName" id="userName" placeholder="User Name">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input class="input100" type="password" name="password" id="password" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit">
                        Login
                    </button>
                </div>



                <div class="text-center p-t-136">
                    <a class="txt2" href="register">
                        Create your Account
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- MDB -->
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"
></script>


<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/tilt/tilt.jquery.min.js"></script>
<script>
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="../../js/login.js"></script>
</body>

</html>