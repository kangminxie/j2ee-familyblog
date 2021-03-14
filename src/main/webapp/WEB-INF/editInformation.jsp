<%--@elvariable id="form" type="com.kangmin.blog.formbean.RegisterForm"--%>
<%--@elvariable id="user" type="com.kangmin.blog.databean.User"--%>
<%--@elvariable id="csrfToken" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_styles.jsp"/>
    <title>Edit Information Page</title>
</head>
<body>
<jsp:include page="_navi.jsp"/>
<!-- Header for Edit Information Page -->
<header id="edit-info-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                Edit Information Page
            </div>
        </div>
    </div>
</header>
<jsp:include page="_errors.jsp"/>

<div class="container mb-3">
    <jsp:include page="_remind.jsp"/>
    <div class="bg-light col-lg-6 col-md-8 mx-auto py-3">
        <form method="POST" id="editInformationForm" action="editInformation.do">
            <div class="form-group">
                <label for="firstName">Edit First Name:</label>
                <input type="text" name="firstName" id="firstName" class="form-control" placeholder="${user.firstName}"
                       value="${form.firstName}">
            </div>
            <div class="form-group">
                <label for="lastName">Edit Last Name</label>
                <input type="text" name="lastName" id="lastName" class="form-control" placeholder="${user.lastName}"
                       value="${form.lastName}">
            </div>
            <div class="form-group">
                <label for="userName">Email Address</label>
                <input type="text" name="userName" id="userName" class="form-control" readonly="readonly"
                       placeholder="${user.userName}" value="${form.lastName}">
            </div>
            <div class="form-group">
                <label for="userInputAddressLine1">Edit Address Line 1</label>
                <input type="text" name="addressLine1" class="form-control" id="userInputAddressLine1"
                       placeholder="${user.addressLine1}" value="${form.addressLine1}">
            </div>
            <div class="form-group">
                <label for="userInputAddressLine2">Edit Address Line 2</label>
                <input type="text" name="addressLine2" class="form-control" id="userInputAddressLine2"
                       placeholder="${user.addressLine2}" value="${form.addressLine2}">
            </div>
            <div class="form-group">
                <label for="userInputCity">Edit City</label>
                <input type="text" name="city" class="form-control" id="userInputCity" placeholder="${user.city}"
                       value="${form.city}">
            </div>
            <div class="form-group">
                <label for="userInputState">Edit State</label>
                <input type="text" name="state" class="form-control" id="userInputState" placeholder="${user.state}"
                       value="${form.state}">
            </div>
            <div class="form-group">
                <label for="userInputCountry">Edit Country</label>
                <input type="text" name="country" class="form-control" id="userInputCountry"
                       placeholder="${user.country}" value="${form.country}">
            </div>
            <div class="form-group">
                <label for="userInputZipCode">Edit Zipcode</label>
                <input type="text" name="zipcode" class="form-control" id="userInputZipCode"
                       placeholder="${user.zipcode}" value="${form.zipcode}">
            </div>
            <div class="form-group">
                <label for="userInputPhoneNumber">Edit Phone number</label>
                <input type="text" name="phoneNumber" class="form-control" id="userInputPhoneNumber"
                       placeholder="${user.phoneNumber}" value="${form.phoneNumber}">
            </div>
            <div class="form-group">
                <!-- <button type="submit" name="loginButton" class="btn btn-default">Submit</button> -->
                <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                <input type="submit" name="editInformationButton" class="btn btn-primary btn-lg pull-right"
                       value="Edit Information"/>
            </div>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/_footer.jsp"/>
<jsp:include page="/WEB-INF/_scripts.jsp"/>
</body>
</html>