<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 13.09.2021
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/fontawesome-free/css/all.min.css" />"
          rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value="${pageContext.request.contextPath}/resources/theme/css/sb-admin-2.min.css"/>"
          rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form:form class="user" action="/register" method="post" modelAttribute="user">
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <form:input path="firstName" type="text" class="form-control form-control-user"
                                                placeholder="First Name"/>
                                </div>
                                <div class="col-sm-6">
                                    <form:input path="lastName" type="text" class="form-control form-control-user"
                                                placeholder="Last Name"/>
                                </div>
                                <div>
                                    <form:errors path="firstName" class="text-danger"/>
                                </div>
                                <div>
                                    <form:errors path="lastName" class="text-danger"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:input type="email" path="email" class="form-control form-control-user"
                                            placeholder="Email Address"/>
                            </div>
                            <div>
                                <form:errors path="email" class="text-danger"/>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <form:input path="password" type="password" class="form-control form-control-user"
                                                placeholder="Password"/>
                                </div>
                                <div>
                                    <form:errors path="password" class="text-danger"/>
                                </div>
                                <div class="col-sm-6">
                                    <form:input path="passwordMatcher" type="password"
                                                class="form-control form-control-user"
                                                placeholder="Repeat Password"/>
                                </div>
                                <div>
                                    <form:errors path="passwordMatcher" class="text-danger"/>
                                </div>
                                <div>
                                    <form:errors objectError="user" class="text-danger"/>
                                </div>
                            </div>
                            <form:button type="submit" class="btn btn-primary btn-user btn-block">
                                Register Account
                            </form:button>
                        </form:form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="/login">Already have an account? Login!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="${pageContext.request.contextPath}/resources/theme/js/sb-admin-2.min.js"/>"></script>
</body>
</html>
