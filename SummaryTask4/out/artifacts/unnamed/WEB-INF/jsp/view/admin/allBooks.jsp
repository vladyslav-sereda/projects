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
                    <c:if test="${sessionScope.user.role == 'ADMIN'}">
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#addBook" role="tab">
                                <h1 class="display-4"><fmt:message key='admin.add_book'/></h1>
                            </a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link active"
                           data-toggle="tab" href="#books" role="tab">
                            <h1 class="display-4"><fmt:message key='books.title'/></h1>
                        </a>
                    </li>
                </ul>
            </div>

            <div class="row">
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane" id="addBook" role="tabpanel">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-3">
                                </div>
                                <div class="col-md-6">
                                    <br><br>

                                    <form data-toggle="validator" name="addBook">
                                        <div class="form-group ">
                                            <label for="InputBookName"><fmt:message key='books.name'/></label>
                                            <input type="text" class="form-control" id="InputBookName"
                                                   placeholder="<fmt:message key='modal.enter_book_name'/>"
                                                   pattern="^[0-9a-zA-Zа-яА-ЯїЇіІєЄ][0-9a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,50}$"
                                                   data-error="<fmt:message key='modal.name_error'/>"
                                                   name="InputBookName" required>
                                            <div class="help-block with-errors error" id="book-name-error"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="InputBookAuthor">
                                                <fmt:message key='books.author'/>
                                            </label>
                                            <input type="text" class="form-control" id="InputBookAuthor"
                                                   placeholder="<fmt:message key='modal.enter_author'/>"
                                                   pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$"
                                                   name="InputBookAuthor"
                                                   data-error="<fmt:message key='modal.author_err'/>"
                                                   required>
                                            <div class="help-block with-errors error" id="author-error"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="InputBookPublisher"><fmt:message key='books.publisher'/></label>
                                            <input type="text" class="form-control" id="InputBookPublisher"
                                                   placeholder="<fmt:message key='modal.enter_publisher'/>" name="InputBookPublisher"
                                                   data-error="<fmt:message key='modal.publisher_err'/>"
                                                   pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,50}$"
                                                   required>
                                            <div class="help-block with-errors error" id="publisher-error"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="InputBookPublicationDate">
                                                <fmt:message key='books.date'/>
                                            </label>
                                            <input type="date" class="form-control" id="InputBookPublicationDate"
                                                   name="InputBookPublicationDate"
                                                   data-error="<fmt:message key='modal.date_err'/>"
                                                   required>
                                            <div class="help-block with-errors error" id="publicationDate-error"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="InputBookAvailable"><fmt:message key='books.available'/></label>
                                            <input type="number" class="form-control" id="InputBookAvailable"
                                                   pattern="^[0-9]{1,20}$" name="InputBookAvailable"
                                                   placeholder="<fmt:message key='modal.enter_available'/>"
                                                   data-error="<fmt:message key='modal.available_err'/>" required>
                                            <div class="help-block with-errors error" id="available-error"></div>
                                        </div>
                                        <div class="help-block with-errors error" id="addBook-error"></div>
                                        <div class="modal-footer">
                                            <button class="btn btn-primary" id="confirm-addBook"
                                                    type="button" onclick="validateBookForm()">
                                                <fmt:message key='modal.confirm'/>
                                            </button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane active" id="books"
                         role="tabpanel">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                   <fmt:message key='books.id'/>
                                </th>
                                <th>
                                    <a href="Controller?command=showAllBooks&sort=name">
                                        <fmt:message key='books.name'/>
                                    </a>
                                </th>
                                <th>
                                    <a href="Controller?command=showAllBooks&sort=author">
                                        <fmt:message key='books.author'/>
                                    </a>
                                </th>
                                <th>
                                    <a href="Controller?command=showAllBooks&sort=publisher">
                                        <fmt:message key='books.publisher'/>
                                    </a>
                                </th>
                                <th>
                                    <a href="Controller?command=showAllBooks&sort=publicationDate">
                                        <fmt:message key='books.date'/>
                                    </a>
                                </th>
                                <th><fmt:message key='books.available'/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.allBooks}" var="book">
                                <tr id="book${book.id}">
                                    <td>${book.id}</td>
                                    <td>${book.name}</td>
                                    <td>${book.author}</td>
                                    <td>${book.publisher}</td>
                                    <td>${book.publicationDate}</td>
                                    <td>${book.available}</td>
                                    <c:if test="${sessionScope.user.role == 'ADMIN'}">
                                        <td>
                                            <a class="btn btn-primary" type="button"
                                               href="Controller?command=showBookEditForm&bookId=${book.id}">
                                                <fmt:message key='modal.edit'/>
                                            </a>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>

</template:main>
</body>
</html>