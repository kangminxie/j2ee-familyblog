<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="error text-center">
        <%--@elvariable id="errors" type="java.util.List"--%>
        <c:if test="${!(empty errors)}">
            <c:forEach var="each" items="${errors}">
                <h4 style="color:red; margin:0;"> ${each} </h4>
            </c:forEach>
        </c:if>
        <%--@elvariable id="error" type="java.lang.String"--%>
        <div class="text-center">
            <h4 style="color:red; margin:0;"> ${error} </h4>
        </div>
    </div>
</div>
