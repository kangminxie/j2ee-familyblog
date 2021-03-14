<%--@elvariable id="form" type="com.kangmin.blog.formbean.AdminLoginForm"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_styles.jsp"/>
    <title>Admin Login</title>
</head>
<body>
<jsp:include page="adminNavi.jsp"/>
<!-- Header for ADMIN Login Page -->
<header id="admin-login-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                <i class="fas fa-plane"></i> Welcome to Admin Login!
            </div>
        </div>
    </div>
</header>
<jsp:include page="_errors.jsp"/>

<div class="container">
    <div class="row">
        <div class="jumbotron col col-lg-6 col-md-6 col-sm">
            <form method="POST" id="AdminLoginForm" action="adminLogin.do">
                <div class="form-group">
                    <label for="adminName">Admin Name</label>
                    <input type="text" name="adminName" class="form-control" id="adminName"
                           placeholder="Type admin name here..." value="${form.adminName}">
                </div>
                <div class="form-group">
                    <label for="adminInputPassword">Password</label>
                    <input type="password" name="password" class="form-control" id="adminInputPassword"
                           placeholder="Type password here...">
                </div>
                <%--@elvariable id="csrfToken" type="java.lang.String"--%>
                <div class="form-group">
                    <!-- <button type="submit" name="loginButton" class="btn btn-default">Submit</button> -->
                    <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                    <input type="submit" name="loginButton" class="form-control btn btn-success btn-lg pull-right"
                           value="adminLogin"/>
                </div>
            </form>
            <br>
            <div class="row justify-content-end">
                <a class="text-center" href="login.do">User? Click here for User Login</a>
            </div>
        </div>
        <div class="col col-md-1 col-lg-1">
        </div>
        <div class="jumbotron col col-md-2 col-lg-4">
            <h4>Recommendations</h4>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="register.do">Click here for new account Registration</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="register.do">A valid email address is needed</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contact.jsp">Click here for Technical
                        Support</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="${pageContext.request.contextPath}/">Click here to leave
                        message</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/_footer.jsp"/>
<jsp:include page="/WEB-INF/_scripts.jsp"/>
</body>
</html>
