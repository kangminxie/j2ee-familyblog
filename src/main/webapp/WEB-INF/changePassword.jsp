<%--@elvariable id="csrfToken" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_styles.jsp"/>
    <title>Change Password</title>
</head>
<body>
<jsp:include page="_navi.jsp"/>
<!-- Header for Change Password Page -->
<header id="change-password-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                Change Current Password
            </div>
        </div>
    </div>
</header>
<jsp:include page="_errors.jsp"/>
<div class="container">
    <jsp:include page="_remind.jsp"/>
    <div class="row">
        <div class="jumbotron col col-lg-6 col-md-6 col-sm">
            <form method="POST" id="changePasswordForm" action="changePassword.do">
                <div class="form-group">
                    <label for="userInputPassword">Current Password</label>
                    <input type="password" name="password" class="form-control" id="userInputPassword"
                           placeholder="Type current password here...">
                </div>
                <div class="form-group">
                    <label for="userInputNewPassword">New Password</label>
                    <input type="password" name="newPassword" class="form-control" id="userInputNewPassword"
                           placeholder="Type new password here...">
                </div>
                <div class="form-group">
                    <label for="userInputNewPassword2">Confirm new Password</label>
                    <input type="password" name="newPassword2" class="form-control" id="userInputNewPassword2"
                           placeholder="Type new password again here...">
                </div>
                <div class="form-group">
                    <!-- <button type="submit" name="loginButton" class="btn btn-default">Submit</button> -->
                    <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                    <input type="submit" name="changePasswordButton" class="btn btn-primary btn-lg pull-right"
                           value="changePassword"/>
                </div>
            </form>
            <br>
            <div>
                <a class="pull-right" href="adminLogin.do">Admin? Click here for Admin Login</a>
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
                <li class="nav-item">
                    <a class="nav-link disabled" href="adminLogin.do">Click here for Admin Login</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/_footer.jsp"/>
<jsp:include page="/WEB-INF/_scripts.jsp"/>
</body>
</html>