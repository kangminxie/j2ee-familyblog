<%--@elvariable id="form" type="com.kangmin.blog.formbean.LoginForm"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_styles.jsp"/>
    <title>Login Page</title>
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Family Blog</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <c:if test="${(empty sessionScope.user)}">
                    <li class="nav-item active">
                        <a class="nav-link" href="register.do">
                            <span class="fa fa-user"></span> Register
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<!-- Header for Login Page -->
<header id="login-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                <i class="fas fa-plane"></i> Login Page
            </div>
        </div>
    </div>
</header>
<jsp:include page="_errors.jsp"/>
<div class="tab-content">
    <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
        <div class="row">
            <div class="col-12 col-sm-10 col-md-8 col-lg-5 mx-auto">
                <h3>Please Login Here</h3>
                <br>
                <h5><i>Type your email and password below to login </i></h5>
                <div class="bg-light px-3">
                    <form method="POST" id="loginForm" action="login.do">
                        <br>
                        <div class="form-group">
                            <label for="userEmailAddress"><b>Email address</b></label>
                            <input type="email" name="emailAddress" class="form-control"
                                   id="userEmailAddress" placeholder="Type email address here..."
                                   value="${form.userName}">
                        </div>
                        <div class="form-group">
                            <label for="userInputPassword"><b>Password</b></label>
                            <input type="password" name="password" class="form-control"
                                   id="userInputPassword" placeholder="Type password here...">
                        </div>
                        <br>
                        <%--@elvariable id="csrfToken" type="java.lang.String"--%>
                        <div class="form-group">
                            <!-- <button type="submit" name="loginButton" class="btn btn-default">Submit</button> -->
                            <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                            <input type="submit" name="loginButton"
                                   class="form-control btn btn-dark btn-lg pull-right" value="Login"/>
                        </div>
                    </form>
                    <br>
                    <div class="row justify-content-end mx-2">
                        <a class="text-right pull-right" href="register.do">
                            Not a user yet? Click here to Register</a>
                    </div>
                    <br>
                    <div class="row justify-content-end mx-2">
                        <a class="text-right pull-right" href="adminLogin.do">
                            Administrator? Click here for Admin Login</a>
                    </div>
                    <br>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
<jsp:include page="_scripts.jsp"/>
</body>
</html>
