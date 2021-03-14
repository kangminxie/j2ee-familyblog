<%--@elvariable id="csrfToken" type="java.lang.String"--%>
<%--@elvariable id="user" type="com.kangmin.blog.formbean.RegisterForm"--%>
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
<jsp:include page="_navi.jsp"/>
<jsp:include page="_errors.jsp"/>
<jsp:include page="_remind.jsp"/>
<!-- Header for Home Page -->
<header id="home-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                <i class="fas fa-home"></i>
                Welcome Home, ${user.firstName} ${user.lastName}
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div id="content">
        <div class="col-lg-8 col-md-10 col-sm-12 mx-auto">
            <form method="POST" id="newPostForm" action="addPost.do">
                <div class="form-group">
                    <label for="exampleFormControlTextarea1" class="row">
                        <span class="h4 my-2">
							<span class="fa fa-edit"></span> Create a New Post here :
						</span>
                    </label>
                    <textarea class="form-control form-control-md" name="newPostItem" id="exampleFormControlTextarea1"
                              rows="4" placeholder="Start your new story here...maximum to 1024 characters"></textarea>
                </div>
                <div class="form-group">
                    <!-- <button type="submit" name="loginButton" class="btn btn-default">Submit</button> -->
                    <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                    <input type="submit" name="newPostButton" class="form-control btn btn-dark btn-md pull-right"
                           value="Post"/>
                </div>
            </form>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12 mx-auto" id="postZone">
            <%--@elvariable id="posts" type="java.util.List"--%>
            <table id="postTable" class="table table-hover">
                <c:if test="${!(empty posts)}">
                    <c:forEach var="eachPost" items="${posts}">
                        <tr>
                            <td style="vertical-align: top;">
                                <form class="delete-form" method="POST" id="deletePostForm" action="deletePost.do">

                                    <!-- Button trigger modal -->
                                    <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                                    <button type="button" class="delButton btn btn-sm btn-outline-danger"
                                            data-toggle="modal"
                                            data-target="#deletePostModalCenter${eachPost.id}" value="X">
                                        X
                                    </button>

                                    <input class="textForDelButton" name="deletePostId" type="hidden" size="1"
                                           readonly="readonly" value="${eachPost.id}"/>
                                    <!-- Modal -->
                                    <div class="modal fade" id="deletePostModalCenter${eachPost.id}" tabindex="-1"
                                         role="dialog"
                                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalCenterTitle">Confirm
                                                        Removing</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Do you really want to remove this Post?
                                                    All comments attached will also be removed
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary btn-md"
                                                            data-dismiss="modal">
                                                        Cancel
                                                    </button>

                                                    <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
                                                    <input class="delButton btn btn-md btn-danger"
                                                           name="deletePostButton"
                                                           type="submit" value="Remove"/>
                                                    <input class="textForDelButton" name="deletePostId" type="hidden"
                                                           size="1"
                                                           readonly="readonly" value="${eachPost.id}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </form>
                            </td>
                            <td class="post">
                                <div class="alert alert-primary" role="alert">
                                    <p class="post"> ${eachPost.content}
                                        <i> --- [Post on <fmt:formatDate value="${eachPost.date}" type="both"
                                                                         pattern="MMM/dd/yyyy hh:mm:ss aa"/>] </i>
                                    </p>
                                </div>
                                    <%--@elvariable id="filteredComments" type="java.util.List"--%>
                                <c:forEach var="eachComment" items="${filteredComments}">
                                    <c:if test="${eachComment.replyTo == eachPost.id}">
                                        <table>
                                            <tr>
                                                <td style="vertical-align: top;">
                                                    <form class="delete-form" method="POST" id="deleteCommentForm"
                                                          action="deleteComment.do">
                                                        <input class="delButton btn btn-sm btn-outline-danger"
                                                               name="deleteCommentButton" type="submit" size="20"
                                                               value="X"/>
                                                        <input class="textForDelButton" name="deleteCommentId"
                                                               type="hidden" size="1"
                                                               readonly="readonly" value="${eachComment.id}"/>
                                                        <input class="textForDelButton" name="deleteReplyToId"
                                                               type="hidden" size="1"
                                                               readonly="readonly" value="${eachPost.id}"/>
                                                        <input class="textForDelButton" name="deleteSource"
                                                               type="hidden" size="1"
                                                               readonly="readonly" value="home"/>
                                                    </form>
                                                </td>
                                                <td class="comment">
                                                    <div class="alert alert-warning" role="alert">
                                                        <p class="comment"><span
                                                                class="commentBy"> <i> Comment by ${eachComment.byWho}:</i> </span>
                                                                ${eachComment.content}
                                                            <span class="commentTime">
			          		  		<i> --- [<fmt:formatDate value="${eachComment.date}" type="both"
                                                             pattern="MMM/dd/yyyy hh:mm:ss aa"/>]</i>
			          		  	</span>
                                                        </p>
                                                    </div>
                                                </td>
                                        </table>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div> <!-- <div class="col-lg-8 col-md-6"> -->
    </div> <!-- id="content" -->
</div>
<jsp:include page="_footer.jsp"/>
<jsp:include page="_scripts.jsp"/>
</body>
</html>
