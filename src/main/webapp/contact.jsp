<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<jsp:include page="./WEB-INF/_styles.jsp"/>
	<title>Contact</title>
</head>
<body>
	<jsp:include page="/WEB-INF/_navi.jsp"/>
	<!-- Header for Contact Page -->
	<header id="contact-main-header" class="mt-0">
		<div class="mx-auto">
			<div class="bg-info text-white">
				<div class="h1 text-center py-3">
					<span class="fa fa-envelope"></span> Contact Information
				</div>
			</div>
		</div>
	</header>
	<div class="container">
		<address>
		  <strong>Kangmin Xie</strong><br>
		  <a href="mailto:#">kangmin.xie@gmail.com</a><br>
		  <abbr title="Phone">Phone:</abbr> (803)719-3971
		</address>
		<address>
			  <strong>Carnegie Mellon University</strong><br>
			  5000 Forbes Avenue<br>
			  Pittsburgh, Pennsylvania 15213<br>
			  United States
		</address>
		<hr>
	   	<div class="show-map">
            <div style="margin-top: 20px; margin-bottom: 20px;">
                <p> <b>Find CMU Pittsburgh Campus by maps below</b> </p>
            </div>

            <div class=" col-md-6 map" style="margin-bottom: 30px;">
                <iframe width="600" height="450" frameborder="0" style="border:0"
						src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJkZqj4iPyNIgRkRiPR6mXTt4&key=[api_key]"></iframe>
            </div>
            <br>
        </div>
	</div>
	<jsp:include page="/WEB-INF/_footer.jsp" />
	<jsp:include page="/WEB-INF/_scripts.jsp"/>
</body>
</html>