<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet"
          href="<c:url value="resources/css/external/materialize.css" />"/>
    <link rel="stylesheet"
          href="<c:url value="resources/css/external/materialize.min.css" />"/>
    <link rel="stylesheet"
          href="<c:url value="resources/css/external/clockpicker.css" />"/>
    <link rel="stylesheet"
          href="<c:url value="resources/css/external/material-icons.css" />"/>
    <link rel="stylesheet"
          href="<c:url value="resources/css/myStyle.css" />"/>


    <script
            src="<c:url value="resources/js/external/jquery-3.1.1.min.js" />"></script>
    <script src="<c:url value="resources/js/external/materialize.js" />"></script>
    <script src="<c:url value="resources/js/external/materialize.min.js" />"></script>
    <script src="<c:url value="resources/js/external/dateformat.js" />"></script>
    <script src="<c:url value="resources/js/external/timeformat.js" />"></script>

    <script src="<c:url value="resources/js/index.js" />"></script>
    <script src="<c:url value="resources/js/admin.js" />"></script>
    <script src="<c:url value="resources/js/account.js" />"></script>
    <script src="<c:url value="resources/js/reservation.js" />"></script>
    <script src="<c:url value="resources/js/search.js" />"></script>
    <script src="<c:url value="resources/js/room.js" />"></script>
    <script src="<c:url value="/resources/js/websocketHandler.js" />"></script>

</head>
<body>
<header>
    <div class="navbar-fixed">

        <nav>
            <div class="nav-wrapper blue-grey lighten-3">
                <c:if test="${!sessionScope.containsKey('user') || sessionScope.user.role == 'CLIENT'}">
                    <a href="Controller?command=index" class="brand-logo left">Photostudio</a>
                </c:if>
                <ul id="main-nav" class="right">
                    <li class="dropdown">
                        <a class="dropdown-button" type="button" data-constrainWidth='false' data-activates='language-list' data-beloworigin='true'>
                            <fmt:message key='navbar.lang'/>
                        </a>
                    </li>
                    <c:if test="${!sessionScope.containsKey('user')}">
                        <li>
                            <a id="registration-btn" data-target="registration-modal" type="button"
                               onclick="openRegModal()">
                                <fmt:message key='navbar.sign_up'/>
                            </a>
                        </li>
                        <li>
                            <a id="login-btn" data-target="login-modal" type="button" onclick="openLoginModal()">
                                <fmt:message key="navbar.sign_in"/>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.containsKey('user')}">
                        <li>
                            <a id="log-out-btn" type="button" href="Controller?command=logout">
                                <fmt:message key='navbar.logout'/>
                            </a>
                        </li>
                        <c:if test="${sessionScope.user.role == 'CLIENT'}">
                            <li>
                                <a href="Controller?command=account" type="button">
                                    <i class="material-icons">home</i>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                            <li>
                                <a href="Controller?command=redirect" type="button">
                                    <i class="material-icons">home</i>
                                </a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
                <ul id='language-list' class="dropdown-content">
                    <li onclick='changeLocale("ru")'>
                        <span class="blue-text text-darken-2 left-align"><fmt:message key="navbar.ru"/></span>
                    </li>
                    <li  onclick='changeLocale("en")'>
                        <span class="blue-text text-darken-2 left-align"><fmt:message key="navbar.en"/></span>
                    </li>
                </ul>
            </div>
        </nav>
    </div>

    <div id="registration-modal" class="modal mo">
        <div class="modal-content">
            <h4>
                <fmt:message key='sign_up.header'/>
            </h4>
            <form id="registration-form" name="registration">
                <div class="input-field">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="reg-input-name" name="inputName" type="text" class="validate"
                           pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$">
                    <label data-error="Enter valid name. Use only сyrillic or roman alphabet" for="reg-input-name">
                        <fmt:message key='sign_up.name'/>
                    </label>
                    <span class="error" id="name-error"></span>
                </div>

                <div class="input-field">
                    <i class="material-icons prefix">phone</i>
                    <input id="reg-input-phone" name="inputPhone" type="text" class="validate"
                           pattern="[+]?[0-9 -]{6,17}">
                    <label data-error="Enter valid phone. Example: +38 050 111 22 33" for="reg-input-phone">
                        <fmt:message key='sign_up.phone'/>
                    </label>
                    <span class="error" id="phone-error"></span>
                </div>

                <div class="input-field">
                    <i class="material-icons prefix">email</i>
                    <input id="reg-input-email" name="inputEmail" type="text"
                           pattern="[a-zA-Z0-9._-]*@[a-zA-Z0-9]+\.[a-zA-Z0-9]+(.[a-zA-Z0-9]+)?" class="validate">
                    <label data-error="Enter valid email" for="reg-input-email">
                        <fmt:message key='sign_up.email'/>
                    </label>
                    <span class="error" id="email-error"></span>
                </div>

                <div class="row">
                    <div class="input-field col s6">
                        <i class="material-icons prefix">lock_outline</i>
                        <input id="reg-input-password" name="inputPassword" type="password" class="validate"
                               pattern=".{6,25}">
                        <label data-error="Your password must contain 6-25 symbols" for="reg-input-password">
                            <fmt:message key='sign_up.password'/>
                        </label>
                    </div>

                    <div class="input-field col s6">
                        <i class="material-icons prefix">lock</i>
                        <input id="reg-input-password-confirm" name="inputPasswordConfirm" type="password"
                               class="validate" pattern=".{6,25}">
                        <label for="reg-input-password-confirm">
                            <fmt:message key='sign_up.confirm_password'/>
                        </label>
                    </div>
                    <span class="error" id="password-confirm-error"></span>
                    <span class="error" id="form-error"></span>
                </div>

                <a class="waves-effect waves-light btn green" id="submit-registration" onclick="validateRegForm()">
                    <i class="material-icons left">done</i>
                    <fmt:message key='sign_up.sign_up'/>
                </a>
                <a class="waves-effect waves-light btn red" onclick="closeModal()">
                    <i class="material-icons left">cancel</i>
                    <fmt:message key='sign_up.cancel'/>
                </a>
            </form>
        </div>
    </div>

    <div id="login-modal" class="modal">
        <div class="modal-content">
            <h4>
                <fmt:message key='sign_in.header'/>
            </h4>
            <form id="login-form" name="login">
                <div class="input-field">
                    <i class="material-icons prefix">email</i>
                    <input id="log-input-email" name="email" type="text"
                           pattern="[a-zA-Z0-9.-_]*@[a-zA-Z0-9]+\.[a-zA-Z0-9]+(.[a-zA-Z0-9]+)?" class="validate">
                    <label data-error="Enter valid email" for="log-input-email">
                        <fmt:message key='sign_in.email'/>
                    </label>
                </div>

                <div class="input-field col s6">
                    <i class="material-icons prefix">lock_outline</i>
                    <input id="log-input-password" name="password" type="password" class="validate" pattern=".{6,25}">
                    <label data-error="Your password must contain 6-25 symbols" for="log-input-password">
                        <fmt:message key='sign_in.password'/>
                    </label>
                </div>

                <div>
                    <span class="error" id="log-form-error"> </span>
                </div>

                <a class="waves-effect waves-light btn green" id="submit-login" onclick="signIn()">
                    <i class="material-icons left">done</i>
                    <fmt:message key='sign_in.sign_in'/>
                </a>
                <a class="waves-effect waves-light btn red" onclick="closeModal()">
                    <i class="material-icons left">cancel</i>
                <fmt:message key='sign_in.cancel'/>
                </a>
            </form>
        </div>
    </div>


</header>
<main>
    <jsp:doBody/>
</main>
<footer class="page-footer  blue-grey lighten-3">
    <div class="footer-copyright">
        <div class="container">
            © 2017
            <fmt:message key='footer.author'/>
            <a class="grey-text text-lighten-4 right" href="https://">
                <fmt:message key='footer.contact'/>
            </a>
        </div>
    </div>
</footer>
</body>