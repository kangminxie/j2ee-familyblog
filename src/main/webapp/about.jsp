<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="./WEB-INF/_styles.jsp"/>
    <title>About</title>
</head>
<body>
<jsp:include page="/WEB-INF/_navi.jsp"/>
<!-- Header for About Page -->
<header id="about-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                <span class="fa fa-leaf"></span> About this web application
            </div>
        </div>
    </div>
</header>
<div class="tab-pane" id="about" role="tabpanel" aria-labelledby="about-tab">
    <div class="container">
        <p>Based on JEE MVC framework<br>
            Front-End: HTML/CSS/Bootstrap4/JSP<br>
            Back-End: MySQL/JDBC/GenericDAO<br>
            Version: 1.1 (updated on March-2021)<br></p>
        <div>
            <p class="btn btn-secondary" data-toggle="collapse" data-target="#impleList">
                -> Actions/Functions implemented (authority checked)
            </p>
            <div id="implementedList" class="collapse show">
                <ul>
                    <li>Login/logout</li>
                    <li>Add Post/ Delete Post</li>
                    <li>Add Comment/ Delete Comment</li>
                    <li>Edit Information</li>
                    <li>Change Password</li>
                    <li>Password Reset</li>
                    <li>Password SHA hash</li>
                    <li>Anti-CSRF Token</li>
                </ul>
            </div>

        </div>
        <div>
            <p type="text" class="btn btn-secondary" data-toggle="collapse" data-target="#tobeImpleList">
                -> Actions/Functions to be implemented
            </p>
            <div id="tobeImplementedList" class="collapse in">
                <ul>
					<li>User last login time</li>
					<li>Add friends function</li>
                </ul>
            </div>
        </div>

        <p class="btn btn-secondary" data-toggle="collapse" data-target="#utilities">
            -> Library Utilities
        </p>
        <div id="utilities" class="collapse in">
            <ul>
                <li>tomcat 8.5.23 as container</li>
                <li>mysql + genericdao 3.0.2</li>
                <li>jstl 1.1.2</li>
                <li>Bootstrap 4.1.2</li>
            </ul>
        </div>
    </div>
</div>
<br>
<jsp:include page="/WEB-INF/_footer.jsp"/>
<jsp:include page="/WEB-INF/_scripts.jsp"/>
</body>
</html>