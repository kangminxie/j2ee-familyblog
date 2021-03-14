<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="remind">
        <%--@elvariable id="reminds" type="java.util.List"--%>
        <c:if test="${!(empty reminds)}">
            <c:forEach var="each" items="${reminds}">
                <h3 style="color:green; margin:0;"> ${each} </h3>
            </c:forEach>
        </c:if>
        <div>
            <%--@elvariable id="remind" type="java.lang.String"--%>
            <h3 style="color:green; margin:0;"> ${remind} </h3>
        </div>
    </div>
</div>
