<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<html>
<head>
    <title><fmt:message key='books.title'/></title>
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
                           data-toggle="tab" href="#books" role="tab">
                            <h1 class="display-4"><fmt:message key='books.title'/></h1>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link "
                           data-toggle="tab" href="#searchTab" role="tab">
                            <h1 class="display-4"><fmt:message key='books.search'/></h1>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="row">
                <!-- Tab panes -->
                <div class="tab-content">

                    <div class="tab-pane active" id="books"
                         role="tabpanel">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <a href="Controller?command=showAvailableBooks&sort=name">
                                        <fmt:message key='books.name'/>
                                    </a>
                                </th>
                                <th>
                                    <a href="Controller?command=showAvailableBooks&sort=author">
                                        <fmt:message key='books.author'/>
                                    </a>
                                </th>
                                <th>
                                    <a href="Controller?command=showAvailableBooks&sort=publisher">
                                        <fmt:message key='books.publisher'/>
                                    </a>
                                </th>
                                <th>
                                    <a href="Controller?command=showAvailableBooks&sort=publicationDate">
                                        <fmt:message key='books.date'/>
                                    </a>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.allBooks}" var="book">
                                <tr id="book${book.id}">
                                    <td>${book.name}</td>
                                    <td>${book.author}</td>
                                    <td>${book.publisher}</td>
                                    <td>${book.publicationDate}</td>

                                    <c:if test="${sessionScope.user.role == 'READER'}">
                                    <td>
                                        <button class="btn btn-primary" type="button"
                                                onclick="openOrderForm(${book.id},'${book.name}')">
                                            <fmt:message key='books.order'/>
                                        </button>
                                    </td>
                                    </c:if>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="tab-pane" id="searchTab" role="tabpanel">
                        <br/>
                        <c:choose>
                            <c:when test="${empty sessionScope.user }">
                                <h4><fmt:message key='books.search_singIn'/></h4>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="col-sm-3">
                                    </div>
                                    <div class="input-group col-sm-6">
                                        <input type="text" class="form-control" id="search">
                                        <div class="input-group-btn dropdown">
                                            <button type="button" class="btn btn-secondary dropdown-toggle"
                                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <fmt:message key='books.search_by'/>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li class="text-center">
                                                    <button type="reset" class="btn-link" onclick="searchBookByName()">
                                                        <fmt:message key='books.name'/>
                                                    </button>
                                                </li>
                                                <li role="separator" class="divider"></li>
                                                <li class="text-center">
                                                    <button type="reset" class="btn-link"
                                                            onclick="searchBookByAuthor()">
                                                        <fmt:message key='books.author'/>
                                                    </button>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <br/>
                                <table class="table table-striped" id="foundBooks">
                                    <thead>
                                    <tr>
                                        <th>
                                            <fmt:message key='books.name'/>
                                        </th>
                                        <th>
                                            <fmt:message key='books.author'/>
                                        </th>
                                        <th>
                                            <fmt:message key='books.publisher'/>
                                        </th>
                                        <th>
                                            <fmt:message key='books.date'/>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>

            </div>

            <div class="modal fade" id="orderModal" tabindex="-1" role="dialog" aria-labelledby="orderModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="orderModalLabel"><fmt:message key='books.make_order'/></h4>
                        </div>
                        <div class="modal-body">
                            <form id="order-form">
                                <input type="hidden" id="user-id" name="userId" value="${sessionScope.user.id}">
                                <input type="hidden" id="book-id" name="bookId" value="">

                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="checkbox" id="readingRoomCheck"
                                               value="true"
                                               onclick="readingRoom()">
                                        <fmt:message key='books.room'/>
                                    </label>
                                </div>

                                <div class="form-group">
                                    <label for="orderDeadline"><fmt:message key='books.deadline'/></label>
                                    <input type="date" class="form-control" id="orderDeadline"
                                           placeholder="deadline" required>
                                </div>
                                <div class="help-block with-errors error" id="order-form-error"></div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" onclick="closeOrderModal()">
                                        <fmt:message key='modal.close'/>
                                    </button>
                                    <button type="button" class="btn btn-primary" id="submit-order"
                                            onclick="makeOrder()">
                                        <fmt:message key='modal.confirm'/>
                                    </button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>


            <div class="modal fade" id="notAvailableModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><fmt:message key='error.title'/></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p><fmt:message key='books.not_avail_book'/></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>

</template:main>
</body>
</html>
