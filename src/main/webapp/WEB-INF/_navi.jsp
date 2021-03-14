<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="home.do">Family Blog</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about.jsp"><span
                            class="fa fa-leaf"></span> About <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contact.jsp"><span
                            class="fa fa-envelope"></span> Contact</a>
                </li>
                <li class="nav-item active dropdown">
                    <a class="nav-link dropdown-toggle" href="#"
                       id="navbarDropdown2" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <span class="fa fa-users"></span> People's Blog
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <%--@elvariable id="users" type="java.util.List"--%>
                        <c:forEach var="each" items="${users}">
                            <a class="dropdown-item"
                               href="visitor.do?email=${each.userName}"> ${each.firstName} ${each.lastName}</a>
                        </c:forEach>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="home.do"> Back to My Home Page</a>
                    </div>
            </ul>

            <ul class="navbar-nav ml-auto">
                <c:if test="${(empty sessionScope.user)}">
                    <li class="nav-item active"><a class="nav-link" href="register.do"><span class="fa fa-user"></span>
                        Register</a></li>
                    <li class="nav-item active"><a class="nav-link" href="login.do"><span
                            class="fa fa-sign-in-alt"></span> Login</a></li>
                </c:if>
                <c:if test="${!(empty sessionScope.user)}">
                    <li class="nav-item active"><a class="nav-link" href="home.do"><span class="fa fa-home"></span> Home</a>
                    </li>

                    <li class="nav-item active dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="fa fa-cog"></span> Settings
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="editInformation.do"><span class="fa fa-edit"></span> Edit My
                                Information</a>
                            <a class="dropdown-item" href="changePassword.do"><span class="fa fa-edit"></span> Change
                                Password</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="logout.do"><span class="fa fa-sign-out-alt"></span>
                                Logout</a>
                        </div>
                    </li>
                    <li class="nav-item active"><a class="nav-link" href="logout.do"><span
                            class="fa fa-sign-out-alt"></span> Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
