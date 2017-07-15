<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<html>
<head>
    <title><fmt:message key='reader.title'/></title>
</head>
<body>
<template:main>
    <jsp:body>
        <div class="container">
            <div class="row">

                <!-- Nav tabs -->
                <ul class="nav nav-tabs nav-justified justify-content-center" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active"
                           data-toggle="tab" href="#myOrders" role="tab">
                            <h3 class="display-4"><fmt:message key='reader.order'/></h3>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link "
                           data-toggle="tab" href="#editAccount" role="tab">
                            <h3 class="display-4"><fmt:message key='reader.edit'/></h3>
                        </a>
                    </li>

                </ul>
            </div>


            <div class="row">
                <!-- Tab panes -->
                <div class="tab-content">

                    <div class="tab-pane active" id="myOrders" role="tabpanel">

                        <div id="accordion" role="tablist" aria-multiselectable="true">

                            <div class="card">
                                <div class="card-header" role="tab" id="confirmedHeader">
                                    <h5 class="mb-0">
                                        <a data-toggle="collapse" class="collapsed btn btn-secondary btn-lg"
                                           data-parent="#accordion" href="#confirmed"
                                           aria-expanded="false" aria-controls="confirmed">
                                            <fmt:message key='reader.confirmed'/>
                                        </a>
                                    </h5>
                                </div>

                                <div id="confirmed" class="collapse" role="tabpanel"
                                     aria-labelledby="confirmedHeader">
                                    <div class="card-block">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>
                                                    <fmt:message key='order.id'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='books.name'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='books.author'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='books.deadline'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='order.penalty'/>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.orders}" var="order">
                                                <c:forEach items="${sessionScope.books}" var="book">
                                                    <c:if test="${order.status == 'CONFIRMED' && order.bookId == book.id}">
                                                        <tr id="order${order.id}">
                                                            <td>${order.id}</td>
                                                            <td>${book.name}</td>
                                                            <td>${book.author}</td>
                                                            <td><my:date>${order.deadline}</my:date></td>
                                                            <td class="error">${order.penalty}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <c:if test="${sessionScope.user.banned == false}">
                            <div class="card">
                                <div class="card-header" role="tab" id="pendingHeader">
                                    <h5 class="mb-0">
                                        <a class="collapsed btn btn-secondary btn-lg" data-toggle="collapse"
                                           data-parent="#accordion"
                                           href="#pendingOrders" aria-expanded="false" aria-controls="pendingOrders">
                                            <fmt:message key='reader.delivery'/>
                                        </a>
                                    </h5>
                                </div>

                                <div id="pendingOrders" class="collapse" role="tabpanel"
                                     aria-labelledby="pendingHeader">
                                    <div class="card-block">

                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>
                                                    <fmt:message key='order.id'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='books.name'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='books.author'/>
                                                </th>
                                                <th>
                                                    <fmt:message key='books.deadline'/>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${sessionScope.orders}" var="order">
                                                <c:forEach items="${sessionScope.books}" var="book">
                                                    <c:if test="${order.status == 'ORDERED' && order.bookId == book.id}">
                                                        <tr id="order${order.id}">
                                                            <td>${order.id}</td>
                                                            <td>${book.name}</td>
                                                            <td>${book.author}</td>
                                                            <td><my:date>${order.deadline}</my:date></td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                            </c:if>
                        </div>

                    </div>

                    <div class="tab-pane" id="editAccount" role="tabpanel">
                        <br/>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div id="accountInfo" class="col-md-4">
                                    <br><br>
                                    <table align="center" width="100%">
                                        <tr>
                                            <td>
                                                <b><fmt:message key='user.name'/>:</b>
                                            </td>
                                            <td>
                                                <span id="name">${sessionScope.user.name}</span>
                                            </td>
                                            <td>
                                                <a class="btn btn-primary " data-target="#change-name-modal"
                                                   data-toggle="modal">
                                                    <fmt:message key='modal.edit'/>
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <b><fmt:message key='user.email'/>: </b>
                                            </td>
                                            <td>
                                                <span id="email">${sessionScope.user.email}</span>
                                            </td>
                                            <td>
                                                <a class="btn btn-primary " data-target="#change-email-modal"
                                                   data-toggle="modal">
                                                    <fmt:message key='modal.edit'/>
                                                </a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><b><fmt:message key='modal.password'/></b></td>
                                            <td></td>
                                            <td>
                                                <a class="btn btn-primary " data-target="#change-pass-modal"
                                                   data-toggle="modal">
                                                    <fmt:message key='modal.edit'/>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="change-name-modal" class="modal fade" tabindex="-1" role="dialog"
                                     aria-labelledby="editNameModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h4 class="modal-title" id="editNameModalLabel">
                                                    <fmt:message key='modal.change_name'/>
                                                </h4>
                                            </div>
                                            <div class="modal-body">
                                                <form id="change-name-form" name="change-name-form"
                                                      data-toggle="validator">
                                                    <div class="form-group">
                                                        <label for="change-name"><fmt:message key='modal.new_name'/>:
                                                        </label>
                                                        <input id="change-name" name="inputName" type="text"
                                                               class="form-control"
                                                               pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$"
                                                               data-error="<fmt:message key='modal.name_error'/>"
                                                               placeholder="<fmt:message key='modal.new_name'/>"
                                                               required>
                                                        <div class="help-block with-errors "></div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="change-name-password"><fmt:message
                                                                key='modal.password'/>:</label>
                                                        <input id="change-name-password" name="password"
                                                               type="password"
                                                               placeholder="<fmt:message key='modal.password'/>"
                                                               data-error="<fmt:message key='modal.pass_error'/>"
                                                               class="form-control" pattern=".{6,25}" required>
                                                    </div>
                                                    <div class="help-block with-errors error"
                                                         id="change-name-form-error">
                                                    </div>

                                                    <div class="modal-footer">
                                                        <button type="reset" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                            <fmt:message key='modal.close'/>
                                                        </button>
                                                        <button type="button" class="btn btn-primary" id="changeName"
                                                                onclick="changeUserName()">
                                                            <fmt:message key='modal.confirm'/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div id="change-email-modal" class="modal fade" tabindex="-1" role="dialog"
                                     aria-labelledby="editEmailModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h4 class="modal-title" id="editEmailModalLabel">
                                                    <fmt:message key='modal.change_email'/>
                                                </h4>
                                            </div>
                                            <div class="modal-body">
                                                <form id="change-email-form" name="change-email-form"
                                                      data-toggle="validator">
                                                    <div class="form-group">
                                                        <label for="change-name"><fmt:message
                                                                key='modal.new_email'/>:</label>
                                                        <input id="change-email" name="inputEmail" type="email"
                                                               class="form-control"
                                                               data-error="<fmt:message key='modal.email_error'/>"
                                                               placeholder="<fmt:message key='modal.new_email'/>"
                                                               required>
                                                        <div class="help-block with-errors "></div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="change-email-password"><fmt:message
                                                                key='modal.password'/>:</label>
                                                        <input id="change-email-password" name="password"
                                                               type="password"
                                                               placeholder="<fmt:message key='modal.password'/>"
                                                               data-error="<fmt:message key='modal.pass_error'/>"
                                                               class="form-control" pattern=".{6,25}" required>
                                                    </div>
                                                    <div class="help-block with-errors error"
                                                         id="change-email-form-error">
                                                    </div>

                                                    <div class="modal-footer">
                                                        <button type="reset" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                            <fmt:message key='modal.close'/>
                                                        </button>
                                                        <button type="button" class="btn btn-primary" id="changeEmail"
                                                                onclick="changeUserEmail()">
                                                            <fmt:message key='modal.confirm'/>
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div id="change-pass-modal" class="modal fade" tabindex="-1" role="dialog"
                                     aria-labelledby="editPassLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h4 class="modal-title" id="editPassModalLabel">
                                                    <fmt:message key='modal.change_pass'/>
                                                </h4>
                                            </div>
                                            <div class="modal-body">
                                                <form id="change-pass-form" name="change-pass-form"
                                                      data-toggle="validator">
                                                    <div class="form-group">
                                                        <label for="old-pass"><fmt:message
                                                                key='modal.old_pass'/>:</label>
                                                        <input id="old-pass" name="password" type="password"
                                                               data-error="<fmt:message key='modal.pass_error'/>"
                                                               class="form-control"
                                                               placeholder="<fmt:message key='modal.old_pass'/>"
                                                               pattern=".{6,25}" required>
                                                        <div class="help-block with-errors error"
                                                             id="change-oldPass-error">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="new-pass"><fmt:message
                                                                key='modal.new_pass'/>:</label>
                                                        <input id="new-pass" name="password" type="password"
                                                               data-error="<fmt:message key='modal.pass_error'/>"
                                                               class="form-control"
                                                               placeholder="<fmt:message key='modal.new_pass'/>"
                                                               pattern=".{6,25}" required>
                                                        <input id="conf-new-pass" name="password" type="password"
                                                               data-error="<fmt:message key='modal.pass_error'/>"
                                                               placeholder="<fmt:message key='modal.confirm'/>
                                                               <fmt:message key='modal.new_pass'/>" pattern=".{6,25}"
                                                               class="form-control" required>
                                                        <div class="help-block with-errors error"
                                                             id="change-newPass-error">
                                                        </div>
                                                    </div>
                                                    <div class="help-block with-errors error" id="change-pass-error">
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="reset" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                            <fmt:message key='modal.close'/>
                                                        </button>
                                                        <button type="button" class="btn btn-primary" id="changePass"
                                                                onclick="changeUserPass()">
                                                            <fmt:message key='modal.confirm'/>
                                                        </button>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>
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
