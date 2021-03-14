<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_styles.jsp"/>
    <title>Home Page</title>
</head>
<body>
<jsp:include page="adminNavi.jsp"/>
<!-- Header for ADMIN Home Page -->
<header id="admin-home-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <%--@elvariable id="admin" type="com.kangmin.blog.databean.Admin"--%>
            <div class="h1 text-center py-3">
                <i class="fas fa-home"></i>
                Welcome to Admin Home Page, ${admin.firstName} ${admin.lastName}
            </div>
        </div>
    </div>
</header>
<jsp:include page="_errors.jsp"/>

<div class="container">
    <jsp:include page="_remind.jsp"/>
    <div id="content">
        <div class="col-lg-12" id="zone">
            <h3>List of Blog Users</h3>
            <table id="manageUserTable" class="table table-striped table-bordered">
                <thead class="thead-dark">
                <tr>
                    <!--     		  <thead class="thead">
                                  <tr class="table-primary">  -->
                    <th>
                        User's Email Address
                    </th>
                    <th>
                        Full Name
                    </th>
                    <th>
                        Phone Number
                    </th>
                    <th>
                        Address
                    </th>
                    <th>
                        Enroll Date
                    </th>
                    <th>
                        Action
                    </th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="users" type="com.kangmin.blog.databean.User"--%>
                <c:if test="${!(empty users)}">
                    <c:forEach var="eachUser" items="${users}">
                        <tr>
                            <td>
                                    ${eachUser.userName}
                            </td>
                            <td>
                                    ${eachUser.firstName} ${eachUser.lastName}
                            </td>
                            <td>
                                    ${eachUser.phoneNumber}
                            </td>
                            <td>
                                    ${eachUser.addressLine1} ${eachUser.addressLine2}, ${eachUser.city} ${eachUser.state}
                            </td>
                            <td>
                                    ${eachUser.enrollDate}
                            </td>
                            <td> <%--@elvariable id="csrfToken" type="java.lang.String"--%>
                                <form method="POST" id="resetUserPasswordForm" action="resetPassword.do">
                                    <input name="userName" type="hidden" readonly="readonly"
                                           value="${eachUser.userName}"/>
                                    <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                                    <input class="form-control btn btn-danger btn-sm pull-right" type="submit"
                                           name="resetPasswordButton" value="Reset Password"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div> <!-- <div class="col-lg-8 col-md-6"> -->

        <br>
        <div class="col-lg-10" id="postZone">
            <h3>List of Blog Admins</h3>
            <table id="manageAdminTable" class="table table-striped">
                <thead class="thead-dark">
                <tr class="table-primary">
                    <th>
                        AdminName
                    </th>
                    <th>
                        Full Name
                    </th>
                    <th>
                        Enroll Date here
                    </th>
                    <th>
                        Action
                    </th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="admins" type="java.util.List<com.kangmin.blog.databean.Admin>"--%>
                <c:if test="${!(empty admins)}">
                    <c:forEach var="eachAdmin" items="${admins}">
                        <tr>
                            <td>
                                    ${eachAdmin.adminName}
                            </td>
                            <td>
                                    ${eachAdmin.firstName} ${eachAdmin.lastName}
                            </td>
                            <td>
                                    ${eachAdmin.enrollDate}
                            </td>
                            <td>
                                <form method="POST" id="resetAdminPasswordForm" action="resetPassword.do">
                                    <input name="adminName" type="hidden" readonly="readonly"
                                           value="${eachAdmin.adminName}"/>
                                    <input class="form-control btn btn-danger btn-sm pull-right" type="submit"
                                           name="resetPasswordButton" value="Reset Password"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div> <!-- <div class="col-lg-8 col-md-6"> -->
    </div> <!-- id="content" -->
</div>
<br>
<jsp:include page="/WEB-INF/_footer.jsp"/>
<jsp:include page="/WEB-INF/_scripts.jsp"/>

<script type="text/javascript">
    $(document).ready(function () {
        $('#manageUserTable').DataTable({});
        $('#manageAdminTable').DataTable({});
    });
</script>

</body>
</html>
