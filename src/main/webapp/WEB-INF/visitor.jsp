<%--@elvariable id="entry" type="com.kangmin.blog.databean.User"--%>
<%--@elvariable id="csrfToken" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_styles.jsp"/>
    <title> Visitor </title>
</head>
<body>
<jsp:include page="_navi.jsp"/>
<!-- Header for Visitor Page -->
<header id="visitor-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                <i class="fas fa-book"></i> Visitor Page
            </div>
        </div>
    </div>
</header>
<div class="container">
    <h2> You are visiting ${entry.firstName} ${entry.lastName}'s Blog </h2>
    <br>
    <jsp:include page="_errors.jsp"/>
    <div id="content">
        <%--@elvariable id="posts" type="java.util.List"--%>
        <div class="col-lg-8" id="postZone">
            <table class="table table-hover">
                <c:if test="${(empty posts)}">
                    <div class="alert alert-primary" role="alert">
                        <p class="post">
                            Currently, ${entry.firstName} ${entry.lastName} does not have any post yet
                        </p>
                    </div>
                </c:if>
                <c:if test="${!(empty posts)}">
                    <c:forEach var="eachPost" items="${posts}">
                        <tr>
                            <td style="vertical-align: top;"></td>
                            <td class="post">
                                <div class="alert alert-primary" role="alert">
                                    <p class="post"> ${eachPost.content}
                                        <i>--[<fmt:formatDate value="${eachPost.date}" type="both"
                                                              pattern="MM/dd/yyyy hh:mm aa"/> ]</i>
                                    </p>
                                </div>
                                    <%--@elvariable id="filteredComments" type="java.util.List"--%>
                                <c:forEach var="eachComment" items="${filteredComments}">
                                    <c:if test="${eachComment.replyTo == eachPost.id}">
                                        <table>
                                            <tr>
                                                <td style="vertical-align: top;">
                                                        <%--@elvariable id="user" type="com.kangmin.blog.databean.User"--%>
                                                    <c:if test="${user.userName == eachComment.byEmail}">
                                                        <form class="delete-form" method="POST" id="deleteCommentForm"
                                                              action="deleteComment.do">
                                                            <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                                                            <input class="delButton btn btn-sm btn-outline-danger"
                                                                   name="deleteCommentButton" type="submit" value="X"/>
                                                            <input class="textForDelButton" name="deleteCommentId"
                                                                   type="hidden" size="1" readonly="readonly"
                                                                   value="${eachComment.id}"/>
                                                            <input class="textForDelButton" name="deleteReplyToId"
                                                                   type="hidden" size="1" readonly="readonly"
                                                                   value="${eachPost.id}"/>
                                                            <input class="textForDelButton" name="deleteSource"
                                                                   type="hidden" size="1" readonly="readonly"
                                                                   value="visitor"/>
                                                        </form>
                                                    </c:if>
                                                    <c:if test="${user.userName != eachComment.byEmail}">
                                                        <span style="margin-left:35px;"> </span>
                                                    </c:if>
                                                </td>
                                                <td class="comment">
                                                    <div class="alert alert-warning" role="alert">
                                                        <p class="comment"><span
                                                                class="commentBy"><i>Comment by ${eachComment.byWho}:</i> </span>
                                                                ${eachComment.content}
                                                            <span class="commentTime">
				          		  <i>--[<fmt:formatDate value="${eachComment.date}" type="both"
                                                        pattern="MM/dd/yyyy hh:mm aa"/>]</i>
				          		  </span>
                                                        </p>
                                                    </div>
                                                </td>
                                            <tr>
                                        </table>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <blockquote>
                                    <form method="POST" id="newCommentForm" action="addComment.do">
                                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        <span class="fa fa-edit"></span> <label>
                                        <input class="commText" name="newCommentItem"
                                               type="text" size="50" value=""
                                               placeholder=" Enter your comment here...maximum to 255 characters"/>
                                    </label>
                                        <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                                        <input class="commButton btn btn-dark btn-md pull-right" name="newCommentButton"
                                               type="submit" value="Comment"/>
                                        <input class="commentText" name="replyToId" type="hidden" size="1"
                                               readonly="readonly" value="${eachPost.id}"/>
                                    </form>
                                </blockquote>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
<jsp:include page="_scripts.jsp"/>
</body>
</html>