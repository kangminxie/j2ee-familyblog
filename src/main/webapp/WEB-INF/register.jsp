<%--@elvariable id="form" type="com.kangmin.blog.formbean.RegisterForm"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="_styles.jsp"/>
    <title>Register Page</title>
</head>
<body onload="isAccepted()">
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Family Blog</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <c:if test="${(empty sessionScope.user)}">
                    <li class="nav-item active">
                        <a class="nav-link" href="login.do">
                            <span class="fa fa-sign-in-alt"></span> Login
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<!-- Header for Register Page -->
<header id="register-main-header" class="mt-0">
    <div class="mx-auto">
        <div class="bg-info text-white">
            <div class="h1 text-center py-3">
                <i class="fas fa-user"></i> New User Registration
            </div>
        </div>
    </div>
</header>

<jsp:include page="_errors.jsp"/>

<div class="container">
    <div class="text-center my-2">
        <i><b>Please fill in all * fields before hit "Register" button</b></i>
    </div>
    <div class="bg-light col-lg-8 col-md-6 mx-auto py-2">
        <form method="POST" id="registerForm" action="register.do">
            <div class="form-group row">
                <div class="form-group col-lg-6">
                    <label for="firstName">First Name (*)</label>
                    <input type="text" name="firstName" id="firstName" class="form-control" placeholder="John"
                           value="${form.firstName}">
                </div>
                <div class="form-group col-lg-6">
                    <label for="lastName"> Last Name (*)</label>
                    <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Smith"
                           value="${form.lastName}">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-12">
                    <div class="form-group">
                        <label for="emailAddress"> Email Address (*)</label>
                        <input type="email" name="emailAddress" class="form-control" id="emailAddress"
                               placeholder="johnsmith@gmail.com" value="${form.emailAddress}">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="form-group col-lg-6">
                    <label for="userInputPassword"> Password (*)</label>
                    <input type="password" name="password" class="form-control" id="userInputPassword"
                           placeholder="Type password here">
                </div>
                <div class="form-group col-lg-6">
                    <label for="userInputPassword2">Password Confirm (*)</label>
                    <input type="password" name="password2" class="form-control" id="userInputPassword2"
                           placeholder="Confirm password here">
                </div>
            </div>
            <div class="form-group row">
                <div class="form-group col-lg-6">
                    <label for="userInputAddressLine1">Address Line 1</label>
                    <input type="text" name="addressLine1" class="form-control" id="userInputAddressLine1"
                           placeholder="123 Main Street">
                </div>
                <div class="form-group col-lg-6">
                    <label for="userInputAddressLine2">Address Line 2</label>
                    <input type="text" name="addressLine2" class="form-control" id="userInputAddressLine2"
                           placeholder="APT 4 (if possible)">
                </div>
            </div>

            <div class="form-group row">
                <div class="form-group col-lg-4">
                    <label for="userInputCity">City</label>
                    <input type="text" name="city" class="form-control" id="userInputCity" placeholder="Pittsburgh">
                </div>
                <div class="form-group col-lg-4">
                    <label for="userInputState">State</label>
                    <input type="text" name="state" class="form-control" id="userInputState" placeholder="PA">
                </div>
                <div class="form-group col-lg-4">
                    <label for="userInputZipCode">Zipcode</label>
                    <input type="text" name="zipcode" class="form-control"
                           id="userInputZipCode" placeholder="12345">
                </div>
            </div>
            <div class="form-group row">
                <div class="form-group col-lg-6">
                    <label for="userInputCountry">Country</label>
                    <input type="text" name="country" class="form-control" id="userInputCountry" placeholder="USA">
                </div>
                <div class="form-group col-lg-6">
                    <label for="userInputPhoneNumber">Phone number</label>
                    <input type="text" name="phoneNumber" class="form-control" id="userInputPhoneNumber"
                           placeholder="123-456-7890">
                </div>
            </div>

            <div class="form-group">
                <!-- Button trigger modal -->
                <label for="userInputPhoneNumber">Fill all * fields before Registration</label><br>
                <%--@elvariable id="csrfToken" type="java.lang.String"--%>
                <input type="hidden" name="csrfToken" value="${csrfToken}"/>
                <button type="button" class="form-control btn btn-dark btn-lg col-lg-12" data-toggle="modal"
                        data-target="#registerModalCenter">
                    Register
                </button>

                <!-- Modal -->
                <div class="modal fade" id="registerModalCenter" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalCenterTitle">User Agreement</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h1>Privacy Policy</h1>

                                <p>Effective date: July 26, 2018</p>

                                <p>MountKingX ("us", "we", or "our") operates the http://18.209.43.43/FamilyBlog4/
                                    website (the "Service").</p>

                                <p>This page informs you of our policies regarding the collection, use, and disclosure
                                    of personal data when you use our Service and the choices you have associated with
                                    that data. This Privacy Policy for MountKingX is powered by <a
                                            href="https://www.freeprivacypolicy.com/free-privacy-policy-generator.php">FreePrivacyPolicy.com</a>.
                                </p>

                                <p>We use your data to provide and improve the Service. By using the Service, you agree
                                    to the collection and use of information in accordance with this policy. Unless
                                    otherwise defined in this Privacy Policy, terms used in this Privacy Policy have the
                                    same meanings as in our Terms and Conditions, accessible from
                                    http://18.209.43.43/FamilyBlog4/</p>


                                <h2>Information Collection And Use</h2>

                                <p>We collect several different types of information for various purposes to provide and
                                    improve our Service to you.</p>

                                <h3>Types of Data Collected</h3>

                                <h4>Personal Data</h4>

                                <p>While using our Service, we may ask you to provide us with certain personally
                                    identifiable information that can be used to contact or identify you ("Personal
                                    Data"). Personally identifiable information may include, but is not limited to:</p>

                                <ul>
                                    <li>Email address</li>
                                    <li>First name and last name</li>
                                    <li>Phone number</li>
                                    <li>Address, State, Province, ZIP/Postal code, City</li>
                                    <li>Cookies and Usage Data</li>
                                </ul>

                                <h4>Usage Data</h4>

                                <p>We may also collect information how the Service is accessed and used ("Usage Data").
                                    This Usage Data may include information such as your computer's Internet Protocol
                                    address (e.g. IP address), browser type, browser version, the pages of our Service
                                    that you visit, the time and date of your visit, the time spent on those pages,
                                    unique device identifiers and other diagnostic data.</p>

                                <h4>Tracking & Cookies Data</h4>
                                <p>We use cookies and similar tracking technologies to track the activity on our Service
                                    and hold certain information.</p>
                                <p>Cookies are files with small amount of data which may include an anonymous unique
                                    identifier. Cookies are sent to your browser from a website and stored on your
                                    device. Tracking technologies also used are beacons, tags, and scripts to collect
                                    and track information and to improve and analyze our Service.</p>
                                <p>You can instruct your browser to refuse all cookies or to indicate when a cookie is
                                    being sent. However, if you do not accept cookies, you may not be able to use some
                                    portions of our Service.</p>
                                <p>Examples of Cookies we use:</p>
                                <ul>
                                    <li><strong>Session Cookies.</strong> We use Session Cookies to operate our Service.
                                    </li>
                                    <li><strong>Preference Cookies.</strong> We use Preference Cookies to remember your
                                        preferences and various settings.
                                    </li>
                                    <li><strong>Security Cookies.</strong> We use Security Cookies for security
                                        purposes.
                                    </li>
                                </ul>

                                <h2>Use of Data</h2>

                                <p>MountKingX uses the collected data for various purposes:</p>
                                <ul>
                                    <li>To provide and maintain the Service</li>
                                    <li>To notify you about changes to our Service</li>
                                    <li>To allow you to participate in interactive features of our Service when you
                                        choose to do so
                                    </li>
                                    <li>To provide customer care and support</li>
                                    <li>To provide analysis or valuable information so that we can improve the Service
                                    </li>
                                    <li>To monitor the usage of the Service</li>
                                    <li>To detect, prevent and address technical issues</li>
                                </ul>

                                <h2>Transfer Of Data</h2>
                                <p>Your information, including Personal Data, may be transferred to — and maintained on
                                    — computers located outside of your state, province, country or other governmental
                                    jurisdiction where the data protection laws may differ than those from your
                                    jurisdiction.</p>
                                <p>If you are located outside United States and choose to provide information to us,
                                    please note that we transfer the data, including Personal Data, to United States and
                                    process it there.</p>
                                <p>Your consent to this Privacy Policy followed by your submission of such information
                                    represents your agreement to that transfer.</p>
                                <p>MountKingX will take all steps reasonably necessary to ensure that your data is
                                    treated securely and in accordance with this Privacy Policy and no transfer of your
                                    Personal Data will take place to an organization or a country unless there are
                                    adequate controls in place including the security of your data and other personal
                                    information.</p>

                                <h2>Disclosure Of Data</h2>

                                <h3>Legal Requirements</h3>
                                <p>MountKingX may disclose your Personal Data in the good faith belief that such action
                                    is necessary to:</p>
                                <ul>
                                    <li>To comply with a legal obligation</li>
                                    <li>To protect and defend the rights or property of MountKingX</li>
                                    <li>To prevent or investigate possible wrongdoing in connection with the Service
                                    </li>
                                    <li>To protect the personal safety of users of the Service or the public</li>
                                    <li>To protect against legal liability</li>
                                </ul>

                                <h2>Security Of Data</h2>
                                <p>The security of your data is important to us, but remember that no method of
                                    transmission over the Internet, or method of electronic storage is 100% secure.
                                    While we strive to use commercially acceptable means to protect your Personal Data,
                                    we cannot guarantee its absolute security.</p>

                                <h2>Service Providers</h2>
                                <p>We may employ third party companies and individuals to facilitate our Service
                                    ("Service Providers"), to provide the Service on our behalf, to perform
                                    Service-related services or to assist us in analyzing how our Service is used.</p>
                                <p>These third parties have access to your Personal Data only to perform these tasks on
                                    our behalf and are obligated not to disclose or use it for any other purpose.</p>


                                <h2>Links To Other Sites</h2>
                                <p>Our Service may contain links to other sites that are not operated by us. If you
                                    click on a third party link, you will be directed to that third party's site. We
                                    strongly advise you to review the Privacy Policy of every site you visit.</p>
                                <p>We have no control over and assume no responsibility for the content, privacy
                                    policies or practices of any third party sites or services.</p>


                                <h2>Children's Privacy</h2>
                                <p>Our Service does not address anyone under the age of 18 ("Children").</p>
                                <p>We do not knowingly collect personally identifiable information from anyone under the
                                    age of 18. If you are a parent or guardian and you are aware that your Children has
                                    provided us with Personal Data, please contact us. If we become aware that we have
                                    collected Personal Data from children without verification of parental consent, we
                                    take steps to remove that information from our servers.</p>


                                <h2>Changes To This Privacy Policy</h2>
                                <p>We may update our Privacy Policy from time to time. We will notify you of any changes
                                    by posting the new Privacy Policy on this page.</p>
                                <p>We will let you know via email and/or a prominent notice on our Service, prior to the
                                    change becoming effective and update the "effective date" at the top of this Privacy
                                    Policy.</p>
                                <p>You are advised to review this Privacy Policy periodically for any changes. Changes
                                    to this Privacy Policy are effective when they are posted on this page.</p>


                                <h2>Contact Us</h2>
                                <p>If you have any questions about this Privacy Policy, please contact us:</p>
                                <ul>
                                    <li>By email: kangmin.xie@gmail.com</li>

                                </ul>
                            </div>

                            <div class="modal-footer">
                                <div class="row">
                                    <div class="form-group checkbox">
                                        <label>
                                            <input id="checkForAgree" class="form-check-input" type="checkbox"
                                                   onclick="isAccepted()">Check here if you agree<br>
                                        </label>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <button type="button" class="btn btn-secondary btn-md btn-danger"
                                            data-dismiss="modal">Not Agree
                                    </button>
                                    <!-- <button type="button" class="btn btn-primary">Confirm Register</button> -->
                                    <input id="registerButton" type="submit" name="registerButton"
                                           class="btn btn-success btn-md pull-right" value="Register"/>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


                <!-- <button type="submit" name="loginButton" class="btn btn-default">Submit</button> -->
                <!-- <input type="submit" name="registerButton" class="btn btn-primary btn-lg pull-right" value="Register"/> -->
            </div>
        </form>
    </div>
</div>

<jsp:include page="_footer.jsp"/>
<jsp:include page="_scripts.jsp"/>
<script>
    function isAccepted() {
        document.getElementById("registerButton").disabled = document.getElementById("checkForAgree").checked !== true;
    }
</script>
</body>
</html>
