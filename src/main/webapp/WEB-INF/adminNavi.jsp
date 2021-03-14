<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Family Blog</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about.jsp">
                        <span class="fa fa-leaf"></span> About
                    </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contact.jsp">
                        <span class="fa fa-envelope"></span> Contact
                    </a>
                </li>
                <li class="nav-item active dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        All Admins
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <%--@elvariable id="admins" type="java.util.List"--%>
                        <c:forEach var="each" items="${admins}">
                            <a class="dropdown-item">${each.adminName} > ${each.firstName} ${each.lastName}</a>
                        </c:forEach>
                    </div>
                </li>
            </ul>

            <ul class="navbar-nav ml-auto">
                <c:if test="${(empty sessionScope.admin)}">
                    <li class="nav-item active"><a class="nav-link" href="adminLogin.do"><span
                            class="fa fa-sign-in-alt"></span> AdminLogin</a></li>
                    <li class="nav-item active"><a class="nav-link" href="login.do"><span
                            class="fa fa-sign-in-alt"></span> UserLogin</a></li>
                </c:if>
                <c:if test="${!(empty sessionScope.admin)}">
                <li><a class="nav-link active" href="adminHome.do"><span class="fa fa-home"></span> Home</a></li>

                <li class="nav-item active dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="fa fa-cog"></span> Settings
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="changePassword.do"><span class="fa fa-edit"></span> Edit My
                            Information</a>
                        <a class="dropdown-item" href="changePassword.do"><span class="fa fa-edit"></span> Change
                            Password</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li><a class="nav-link active" href="logout.do"><span class="fa fa-sign-out-alt"></span> Logout</a></li>
            </ul>
            </c:if>
        </div>
    </div>
</nav>
