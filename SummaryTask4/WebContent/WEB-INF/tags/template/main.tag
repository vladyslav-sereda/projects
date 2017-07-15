<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message key='title.library'/></title>
    <!-- Bootstrap -->
    <link href="<c:url value="resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="resources/css/my.css"/>" rel="stylesheet">


</head>
<body>
<header>
    <nav class="navbar navbar-default fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar-collapse" aria-expanded="false">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand btn btn-link btn-lg" type="button" href="Controller?command=index">
                    <fmt:message key='title.library'/>
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">
                            <fmt:message key='main.language'/>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="text-center" onclick='changeLocale("en")'><fmt:message key='main.english'/></li>
                            <li role="separator" class="divider"></li>
                            <li class="text-center" onclick='changeLocale("ru")'><fmt:message key='main.russian'/></li>
                        </ul>
                    </li>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${!sessionScope.containsKey('user')}">
                        <li>
                            <a type="button" class="btn btn-link btn-lg" data-toggle="modal"
                               data-target="#registrationModal"><fmt:message key='main.sign_up'/>
                            </a>
                        </li>
                        <li>
                            <a type="button" class="btn btn-link btn-lg" data-toggle="modal"
                               data-target="#loginModal">
                                <fmt:message key='main.sign_in'/>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.containsKey('user')}">
                        <li>
                            <a type="button" class="btn btn-link btn-lg" href="Controller?command=logout">
                                <fmt:message key='main.logout'/>
                            </a>
                        </li>
                        <c:if test="${sessionScope.user.role == 'READER'}">
                            <li>
                                <a type="button" class="btn btn-link btn-lg" href="Controller?command=account">
                                    <fmt:message key='main.home'/>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'LIBRARIAN' || sessionScope.user.role == 'ADMIN'}">
                            <li>
                                <a type="button" class="btn btn-link btn-lg" href="Controller?command=redirect">
                                    <fmt:message key='main.home'/>
                                </a>
                            </li>
                        </c:if>
                    </c:if>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <!-- registration Modal -->
    <div class="modal fade" id="registrationModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="registerModalLabel"><fmt:message key='main.sign_up'/></h4>
                </div>
                <div class="modal-body">
                    <form data-toggle="validator">
                        <div class="form-group " id="regForm">
                            <label for="InputName"><fmt:message key='user.name'/></label>
                            <input type="text" class="form-control" id="InputName"
                                   placeholder="<fmt:message key='modal.enter_name'/>"
                                   pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$"
                                   data-error="<fmt:message key='modal.name_error'/>"
                                   name="inputName" required>
                            <div class="help-block with-errors error" id="name-error"></div>
                        </div>
                        <div class="form-group">
                            <label for="reg-InputEmail"><fmt:message key='user.email'/></label>
                            <input type="email" class="form-control" id="reg-InputEmail"
                                   placeholder="<fmt:message key='modal.enter_email'/>"
                                   name="inputEmail" data-error="<fmt:message key='modal.email_error'/>" required>
                            <div class="help-block with-errors error" id="email-error"></div>
                        </div>
                        <div class="form-group">
                            <label for="reg-InputPassword"><fmt:message key='modal.password'/></label>
                            <input type="password" class="form-control" id="reg-InputPassword"
                                   placeholder="<fmt:message key='modal.password'/>"
                                   pattern=".{6,12}" data-error="<fmt:message key='modal.pass_error'/>"
                                   name="inputPassword" required>
                            <input type="password" class="form-control" id="InputConfirmPassword"
                                   placeholder="<fmt:message key='modal.confirm'/><fmt:message key='modal.password'/>"
                                   name="inputPasswordConfirm"
                                   data-error="<fmt:message key='modal.pass_error'/>" pattern=".{6,12}" required>
                            <div class="help-block with-errors error" id="conf-pass-error"></div>
                        </div>
                        <div class="help-block with-errors error" id="reg-form-error"></div>
                        <div class="modal-footer">
                            <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key='modal.close'/>
                            </button>
                            <button class="btn btn-primary" id="submit-registration"
                                    type="button" onclick="validateRegForm()"><fmt:message key='modal.confirm'/>
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>

    <!-- login Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="loginModalLabel"><fmt:message key='main.sign_in'/></h4>
                </div>
                <div class="modal-body">
                    <form data-toggle="validator" id="logForm">
                        <div class="form-group">
                            <label for="log-InputEmail"><fmt:message key='modal.enter_email'/></label>
                            <input type="email" class="form-control" id="log-InputEmail"
                                   placeholder="<fmt:message key='modal.enter_email'/>"
                                   data-error="<fmt:message key='modal.email_error'/>" required>
                            <div class="help-block with-errors error"></div>
                        </div>
                        <div class="form-group">
                            <label for="log-InputPassword"><fmt:message key='modal.password'/></label>
                            <input type="password" class="form-control" id="log-InputPassword"
                                   placeholder="<fmt:message key='modal.password'/>"
                                   pattern=".{6,12}"
                                   data-error="<fmt:message key='modal.pass_error'/>" required>
                            <div class="help-block with-errors error"></div>
                        </div>
                        <div class="help-block with-errors" id="log-form-error"></div>
                        <div class="modal-footer">
                            <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key='modal.close'/>
                            </button>
                            <button type="button" class="btn btn-primary" id="submit-login"
                                    onclick="signIn()">
                                <fmt:message key='modal.confirm'/>
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>

</header>

<main>
    <jsp:doBody/>
</main>

<footer>

    <script src="<c:url value="resources/js/external/jquery-3.1.1.min.js" />"></script>
    <script src="<c:url value="resources/js/external/bootstrap.min.js"/>"></script>
    <script src="<c:url value="resources/js/external/validator.js"/>"></script>
    <script src="<c:url value="resources/js/account.js"/>"></script>
    <script src="<c:url value="resources/js/search.js"/>"></script>
    <script src="<c:url value="resources/js/order.js"/>"></script>
    <script src="<c:url value="resources/js/book.js"/>"></script>
    <script src="<c:url value="resources/js/websocketHandler.js"/>"></script>
    <script src="<c:url value="resources/js/external/dateformat.js" />"></script>
</footer>


</body>
</html>