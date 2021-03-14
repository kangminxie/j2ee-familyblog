<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="./WEB-INF/_styles.jsp"/>
    <title>Welcome</title>
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Family Blog</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="nav navbar-nav mr-auto" id="myTab1" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab"
                           href="#home" role="tab" aria-controls="home" aria-selected="true"><span class="fa fa-home"></span> Index</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="about-tab" data-toggle="tab"
                           href="#about" role="tab" aria-controls="about" aria-selected="false"><span class="fa fa-leaf"></span> About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="contact-tab" data-toggle="tab"
                           href="#contact" role="tab" aria-controls="contact" aria-selected="false"><span class="fa fa-envelope"></span> Contact</a>
                    </li>
                </ul>

                <ul class="navbar-nav ml-auto">
                    <c:if test="${(empty sessionScope.user)}">
                        <li class="nav-item active"><a class="nav-link" href="register.do"><span class="fa fa-user"></span> Register</a></li>
                        <li class="nav-item active"><a class="nav-link" href="login.do"><span class="fa fa-sign-in-alt"></span> Login</a></li>
                    </c:if>

                    <c:if test="${!(empty sessionScope.user)}">
                        <li class="nav-item active"><a class="nav-link" href="home.do"><span class="fa fa-home"></span> Home</a></li>
                        <li class="nav-item active dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="fa fa-cog"></span> Settings
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="editInformation.do"><span class="fa fa-edit"></span> Edit My Information</a>
                                <a class="dropdown-item" href="changePassword.do"><span class="fa fa-edit"></span> Change Password</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout.do"><span class="fa fa-sign-out-alt"></span> Logout</a>
                            </div>
                        </li>
                        <li class="nav-item active"><a class="nav-link" href="logout.do"><span class="fa fa-sign-out-alt"></span> Logout</a></li>
                    </c:if>
                </ul>
            </div><%-- <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
        </div><%-- <div class="container">--%>
    </nav>
    <!-- Header for Index Page -->
    <header id="index-main-header" class="mt-0">
        <div class="mx-auto">
            <div class="bg-info text-white">
                <div class="h1 text-center py-3">
                    :-) Welcome to Family Blog
                </div>
            </div>
        </div>
    </header>
    <div class="container mt-2">
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- home-tab -->
            <div class="tab-pane active"
                 id="home" role="tabpanel" aria-labelledby="home-tab">
                <div class="row mb-2">
                    <div class="col col-lg-8 col-md-10 col-sm-12 mx-auto">
                        <div id="carouselExampleIndicators"
                             class="carousel slide align-content-center"
                             style="width:600px; height:400px; margin:auto;"
                             data-ride="carousel"
                        >
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                            </ol>

                            <!-- The slideshow -->
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="static/images/index_image_01.png" alt="First slide">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>Life is Quick</h5>
                                        <p>Cherish every moment: Happiness is the way</p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="static/images/index_image_02.png?" alt="Second slide">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>Life is Good</h5>
                                        <p>To be productive: My hear is in the work</p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="static/images/index_image_03.png?" alt="Third slide">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>Life is a Gift</h5>
                                        <p>Enjoy the present: Yesterday is history, tomorrow is mystery</p>
                                    </div>
                                </div>
                            </div>

                            <!-- Left and right controls -->
                            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>

                        <div class="m-3 pt-2 px-5 bg-light">
                            <h5>Choice for Users</h5>
                            <div>
                                <form method="GET" id="loginForm" action="login.do">
                                    <div class="form-group">
                                        <label for="loginButton">
                                            Already have an account? Please Login Here:&nbsp;&nbsp;
                                        </label>
                                        <input type="submit" name="loginButton" id="loginButton"
                                               class="btn btn-success btn-md" value="login.do"/>
                                        <!-- <p> or press Login icon in the right corner</p> -->
                                    </div>
                                </form>
                            </div>
                            <div>
                                <form method="GET" id="registerForm" action="register.do">
                                    <div class="form-group">
                                        <label for="registerButton">Want to create a new account? Register Here:&nbsp;</label>
                                        <input type="submit" id="registerButton" name="registerButton"
                                               class="btn btn-success btn-md" value="register.do"/>
                                        <!-- <p> or press Register icon in the right corner</p> -->
                                    </div>
                                </form>
                            </div>
                            <div class="my-2">
                                <h5>Choice for Administrators</h5>
                                <div class="pb-2">
                                    <form method="GET" id="adminLoginForm" action="adminLogin.do">
                                        <div class="form-group">
                                            <label for="adminLoginButton">Please login to Administration Page from Here:</label>
                                            <input type="submit" id="adminLoginButton" name="adminLoginButton"
                                                   class="btn btn-dark btn-md" value="adminLogin.do"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- END of home-tab -->

            <!-- about-tab -->
            <div class="tab-pane" id="about" role="tabpanel" aria-labelledby="about-tab">
                <div class="container">
                    <h2>About this web application</h2>
                    <div>
                        <p>Based on JEE MVC framework<br>
                            Front-End: HTML/CSS/Bootstrap4/JSP<br>
                            Back-End: MySQL/JDBC/GenericDAO<br>
                            Version: 1.03 (updated on JULY-2018)<br></p>
                    </div>
                    <div>
                        <p class="btn btn-secondary" data-toggle="collapse"
                           data-target="#implementedList">
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
                        <p class="btn btn-secondary" data-toggle="collapse" data-target="#tobeImpleList">
                            -> Actions/Functions to be implemented
                        </p>
                        <div id="tobeImpleList" class="collapse in">
                            <ul>
                                <li>User last login time</li>
                                <li>Add friends function</li>
                            </ul>
                        </div>
                    </div>

                    <div>
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
            </div>

            <!-- contact-tab -->
            <div class="tab-pane" id="contact" role="tabpanel"
                 aria-labelledby="contact-tab">
                <div class="container">
                    <h2>Contact Information</h2>
                    <br>
                    <div class="row">
                        <div class="col-lg-4">
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
                        </div>
                        <div class="show-map col-lg-6">
                            <div>
                                <p> <b>&nbsp;&nbsp;&nbsp;Find CMU Pittsburgh Campus by maps below</b> </p>
                            </div>

                            <div class="col-lg-6 col-md-4 map">
                                <iframe width="400" height="300" style="border:0"
                                        src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJkZqj4iPyNIgRkRiPR6mXTt4&key=[api_key]"></iframe>
                            </div>
                        </div>
                    </div> <!-- <div class="row"> -->

                </div><!-- <div class="container"> -->
            </div><!-- contact-tab -->

        </div><!-- <div class="tab-content"> -->
    </div><!-- <div class="container"> -->

    <jsp:include page="/WEB-INF/_footer.jsp" />
    <jsp:include page="/WEB-INF/_scripts.jsp"/>

    <script type="text/javascript">
        $('#myTab1 a').on('click', function (e) {
            e.preventDefault()
            $(this).tab('show')
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.carousel').carousel({
                interval: 3000
            });
        })
    </script>
</body>
</html>
