<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<html>
<head>
    <title><fmt:message key='admin.title'/></title>
</head>
<body>
<template:main>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <a class="btn btn-lg btn secondary"
                       href="Controller?command=showAllBooks">
                        <h3 class="display-4"><fmt:message key='main.show_books'/></h3>
                    </a>
                </div>
                <div class="col-md-8">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs nav-justified justify-content-center" role="tablist">

                        <li class="nav-item">
                            <a class="nav-link active"
                               data-toggle="tab" href="#librarians" role="tab">
                                <h3 class="display-4"><fmt:message key='admin.librarians'/></h3>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link "
                               data-toggle="tab" href="#users" role="tab">
                                <h3 class="display-4"><fmt:message key='admin.users'/></h3>
                            </a>
                        </li>


                    </ul>
                </div>
            </div>

            <div class="row">
                <!-- Tab panes -->
                <div class="tab-content">

                    <div class="tab-pane active" id="librarians" role="tabpanel">

                        <div id="accordion" role="tablist" align="center" aria-multiselectable="true">

                            <div class="card">
                                <div class="card-header" role="tab" id="addLibHeader">
                                    <h5 class="mb-0">
                                        <a data-toggle="collapse" class="collapsed btn btn-secondary btn-lg"
                                           data-parent="#accordion" href="#addLib"
                                           aria-expanded="false" aria-controls="addLib">
                                            <fmt:message key='admin.add_librarian'/>
                                        </a>
                                    </h5>
                                </div>

                                <div id="addLib" class="collapse" role="tabpanel"
                                     aria-labelledby="addLibHeader">
                                    <div class="card-block">
                                        <div class="row">
                                            <div class="col-md-4"></div>
                                            <div align="center" class="col-md-4">
                                                <form data-toggle="validator" id="regLibrForm">
                                                    <div class="form-group ">
                                                        <label for="InputLibrName">
                                                            <fmt:message key='user.name'/></label>
                                                        <input type="text" class="form-control" id="InputLibrName"
                                                               placeholder="<fmt:message key='modal.enter_name'/>"
                                                               pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$"
                                                               data-error="<fmt:message key='modal.name_error'/>"
                                                               name="inputLibrName" required>
                                                        <div class="help-block with-errors error"
                                                             id="libr-name-error"></div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="reg-InputLibrEmail">
                                                            <fmt:message key='user.email'/></label>
                                                        <input type="email" class="form-control" id="reg-InputLibrEmail"
                                                               placeholder="<fmt:message key='modal.enter_email'/>"
                                                               name="inputLibrEmail"
                                                               data-error="<fmt:message key='modal.email_error'/>" required>
                                                        <div class="help-block with-errors error"
                                                             id="libr-email-error"></div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="reg-InputLibrPassword">
                                                            <fmt:message key='modal.password'/>
                                                        </label>
                                                        <input type="password" class="form-control"
                                                               id="reg-InputLibrPassword"
                                                               placeholder="<fmt:message key='modal.password'/>"
                                                               pattern=".{6,12}"
                                                               data-error="<fmt:message key='modal.pass_error'/>"
                                                               name="inputLibrPassword" required>
                                                        <input type="password" class="form-control"
                                                               id="InputConfirmLibrPassword"
                                                               placeholder="<fmt:message key='modal.confirm'/>
                                                               <fmt:message key='modal.password'/>"
                                                               name="inputLibrPasswordConfirm"
                                                               data-error="<fmt:message key='modal.pass_error'/>"
                                                               pattern=".{6,12}" required>
                                                        <div class="help-block with-errors error"
                                                             id="conf-libr-pass-error"></div>
                                                    </div>
                                                    <div class="help-block with-errors error"
                                                         id="reg-libr-form-error"></div>
                                                    <div>
                                                        <button type="reset" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                            <fmt:message key='modal.reser'/>
                                                        </button>
                                                        <button class="btn btn-primary" id="submit-lib-registration"
                                                                type="button" onclick="validateLibrRegForm()">
                                                            <fmt:message key='modal.confirm'/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="card">
                                <div class="card-header" role="tab" id="existLibHeader">
                                    <h5 class="mb-0">
                                        <a class="collapsed btn btn-secondary btn-lg" data-toggle="collapse"
                                           data-parent="#accordion"
                                           href="#existLib" aria-expanded="false" aria-controls="existLib">
                                            <fmt:message key='admin.librarians'/>
                                        </a>
                                    </h5>
                                </div>

                                <div id="existLib" class="collapse" role="tabpanel"
                                     aria-labelledby="existLibHeader">
                                    <div class="card-block">

                                        <table class="table table-striped" id="librariansTable">
                                            <thead>
                                            <tr>
                                                <th>
                                                    <fmt:message key='user.id'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='user.name'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='user.email'/>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.librarians}" var="librarian">
                                                <tr id="libr${librarian.id}">
                                                    <td>${librarian.id}</td>
                                                    <td>${librarian.name}</td>
                                                    <td>${librarian.email}</td>
                                                    <td>
                                                        <a class="btn btn-danger" data-target="#delete-libr-modal"
                                                           data-toggle="modal" onclick="librModal(${librarian.id})">
                                                            <fmt:message key='admin.delete'/>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>

                    <div id="delete-libr-modal" class="modal fade" tabindex="-1" role="dialog"
                         aria-labelledby="deleteLibrModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <h3 class="modal-title" id="deleteLibrModalLabel">
                                        <fmt:message key='admin.sure'/>
                                    </h3>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" id="librarain-id" name="librarainId" value="">
                                    <p> <fmt:message key='admin.removal_libr'/></p>
                                </div>

                                <div class="modal-footer">
                                    <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                        <fmt:message key='modal.close'/>
                                    </button>
                                    <button type="button" class="btn btn-danger" id="deleteLibr"
                                            onclick="deleteLibr()">
                                        <fmt:message key='admin.delete'/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="tab-pane" id="users" role="tabpanel">

                        <div id="accordionUser" role="tablist" align="center" aria-multiselectable="true">

                            <div class="card">
                                <div class="card-header" role="tab" id="bannedUsersHeader">
                                    <h5 class="mb-0">
                                        <a data-toggle="collapse" class="collapsed btn btn-secondary btn-lg"
                                           data-parent="#accordionUser" href="#bannedUsers"
                                           aria-expanded="false" aria-controls="bannedUsers">
                                            <fmt:message key='admin.banned_users'/>
                                        </a>
                                    </h5>
                                </div>

                                <div id="bannedUsers" class="collapse" role="tabpanel"
                                     aria-labelledby="bannedUsersHeader">
                                    <div class="card-block">
                                        <table class="table table-striped" id="bannedUsersTable">
                                            <thead>
                                            <tr>
                                                <th>
                                                    <fmt:message key='user.id'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='user.name'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='user.email'/>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.bannedUsers}" var="bannedUser">
                                                <tr id="bUser{bannedUser.id}">
                                                    <td>${bannedUser.id}</td>
                                                    <td>${bannedUser.name}</td>
                                                    <td>${bannedUser.email}</td>
                                                    <td>
                                                        <a class="btn btn-success"
                                                           onclick="unblockUser(${bannedUser.id})">
                                                            <fmt:message key='admin.unblock'/>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="card">
                                <div class="card-header" role="tab" id="violatedUserHeader">
                                    <h5 class="mb-0">
                                        <a class="collapsed btn btn-secondary btn-lg" data-toggle="collapse"
                                           data-parent="#accordionUser"
                                           href="#violatedUsers" aria-expanded="false" aria-controls="violatedUser">
                                            <fmt:message key='admin.voilated_users'/>
                                        </a>
                                    </h5>
                                </div>

                                <div id="violatedUsers" class="collapse" role="tabpanel"
                                     aria-labelledby="violatedUserHeader">
                                    <div class="card-block">

                                        <table class="table table-striped" id="violatedUsersTable">
                                            <thead>
                                            <tr>
                                                <th>
                                                    <fmt:message key='user.id'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='user.name'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='user.email'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='order.penalty'/>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.violatedUsers}" var="violatedUser">
                                                <c:forEach items="${sessionScope.overdueOrders}" var="overdueOrder">
                                                    <c:if test="${overdueOrder.userId == violatedUser.id}">
                                                        <tr id="vUser${violatedUser.id}">
                                                            <td>${violatedUser.id}</td>
                                                            <td>${violatedUser.name}</td>
                                                            <td>${violatedUser.email}</td>
                                                            <td class="error">${overdueOrder.penalty}</td>
                                                            <td>
                                                                <a class="btn btn-danger"
                                                                   onclick="banUser(${violatedUser.id},'${violatedUser.name}','${violatedUser.email}')">
                                                                    <fmt:message key='admin.ban'/>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</template:main>
</body>
</html>
