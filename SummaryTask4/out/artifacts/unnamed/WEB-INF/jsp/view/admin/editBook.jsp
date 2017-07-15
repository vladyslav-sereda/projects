<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<html>
<head>
    <title><fmt:message key='books.edit_title'/></title>
</head>
<body>
<template:main>

    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <a class="btn btn-primary btn-lg" href="Controller?command=showAllBooks" role="button">
                        <fmt:message key='admin.go_back'/>
                    </a>
                </div>
                <div class="col-md-6">
                    <br><br>
                    <table align="center" width="100%">
                        <tr>
                            <td>
                                <b><fmt:message key='books.name'/>: </b></td>
                            <td>
                                <span id="bookName">${sessionScope.book.name} </span>
                            </td>
                            <td>
                                <a class="btn btn-primary " data-target="#change-name-modal" data-toggle="modal">
                                    <fmt:message key='modal.edit'/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key='books.author'/>: </b>
                            </td>
                            <td>
                                <span id="bookAuthor">${sessionScope.book.author} </span>
                            </td>
                            <td>
                                <a class="btn btn-primary " data-target="#change-author-modal" data-toggle="modal">
                                    <fmt:message key='modal.edit'/>
                                </a>
                            <td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key='books.publisher'/>: </b>
                            </td>
                            <td>
                                <span id="bookPublisher">${sessionScope.book.publisher} </span>
                            </td>
                            <td>
                                <a class="btn btn-primary" data-target="#change-publisher-modal"
                                   data-toggle="modal">
                                    <fmt:message key='modal.edit'/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key='books.date'/>: </b>
                            </td>
                            <td>
                                <span id="bookPublicationDate">${sessionScope.book.publicationDate} </span></td>
                            <td>
                                <a class="btn btn-primary" data-target="#change-publicationDate-modal"
                                   data-toggle="modal">
                                    <fmt:message key='modal.edit'/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key='books.available'/>: </b>
                            </td>
                            <td>
                                <span id="bookAvailable">${sessionScope.book.available} </span>
                            </td>
                            <td>
                                <a class="btn btn-primary" data-target="#change-available-modal"
                                   data-toggle="modal">
                                    <fmt:message key='modal.edit'/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b><fmt:message key='admin.delete'/></b>
                            </td>
                            <td>
                            </td>
                            <td>
                                <a class="btn btn-danger" data-target="#delete-book-modal"
                                   data-toggle="modal">
                                    <fmt:message key='admin.delete'/>
                                </a>
                            </td>
                        </tr>

                    </table>

                </div>
            </div>
        </div>

        <div id="change-name-modal" class="modal fade" tabindex="-1" role="dialog"
             aria-labelledby="editBookNameModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="editBookNameModalLabel">
                            <fmt:message key='modal.change_book_name'/>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form id="change-name-form" name="change-name-form" data-toggle="validator">
                            <div class="form-group">
                                <label for="change-book-name"></label>
                                <input id="change-book-name" name="inputBookName" type="text" class="form-control"
                                       pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,50}$"
                                       data-error="<fmt:message key='modal.name_error'/>"
                                       placeholder="<fmt:message key='modal.new_book_name'/>" required>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                    <fmt:message key='modal.close'/>
                                </button>
                                <button type="button" class="btn btn-primary" id="changeName"
                                        onclick="changeBookName(${sessionScope.book.id})">
                                    <fmt:message key='modal.confirm'/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="change-author-modal" class="modal fade" tabindex="-1" role="dialog"
             aria-labelledby="editBookAuthorModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="editBookAuthorModalLabel">
                            <fmt:message key='modal.change_author'/>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form id="change-author-form" name="change-author-form" data-toggle="validator">
                            <div class="form-group">
                                <label for="change-author-name"></label>
                                <input id="change-author-name" name="inputBookAuthor" type="text" class="form-control"
                                       pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$"
                                       data-error="<fmt:message key='modal.author_err'/>"
                                       placeholder="<fmt:message key='modal.new_author'/>" required>
                                <div class="help-block with-errors "></div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                    <fmt:message key='modal.close'/>
                                </button>
                                <button type="button" class="btn btn-primary" id="changeAuthor"
                                        onclick="changeBookAuthor(${sessionScope.book.id})">
                                    <fmt:message key='modal.confirm'/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="change-publisher-modal" class="modal fade" tabindex="-1" role="dialog"
             aria-labelledby="editBookPublisherModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="editBookPublisherModalLabel">
                            <fmt:message key='modal.change_publisher'/>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form id="change-publisher-form" name="change-publisher-form" data-toggle="validator">
                            <div class="form-group">
                                <label for="change-publisher-name"></label>
                                <input id="change-publisher-name" name="inputBookPublisher" type="text"
                                       class="form-control"
                                       pattern="^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,50}$"
                                       data-error="<fmt:message key='modal.publisher_err'/>"
                                       placeholder="<fmt:message key='modal.new_publisher'/>" required>
                                <div class="help-block with-errors "></div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                    <fmt:message key='modal.close'/>
                                </button>
                                <button type="button" class="btn btn-primary" id="changePublisher"
                                        onclick="changeBookPublisher(${sessionScope.book.id})">
                                    <fmt:message key='modal.confirm'/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="change-publicationDate-modal" class="modal fade" tabindex="-1" role="dialog"
             aria-labelledby="editBookPublicationDateModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="editBookPublicationDateModalLabel">
                            <fmt:message key='modal.change_date'/>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form id="change-publicationDate-form" name="change-publicationDate-form"
                              data-toggle="validator">
                            <div class="form-group">
                                <label for="change-publicationDate"></label>
                                <input id="change-publicationDate" name="inputBookPublicationDate" type="date"
                                       data-error="<fmt:message key='modal.date_err'/>" class="form-control"
                                       placeholder="<fmt:message key='modal.new_date'/>" required>
                                <div class="help-block with-errors" id="edit-publicationDate-error"></div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                    <fmt:message key='modal.close'/>
                                </button>
                                <button type="button" class="btn btn-primary" id="changePublicationDate"
                                        onclick="validateBookPublicationDate(${sessionScope.book.id})">
                                    <fmt:message key='modal.confirm'/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="change-available-modal" class="modal fade" tabindex="-1" role="dialog"
             aria-labelledby="editBookAvailableModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="editBookAvailableModalLabel">
                            <fmt:message key='modal.change_available'/>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form id="change-available-form" name="change-available-form" data-toggle="validator">
                            <div class="form-group">
                                <label for="change-available"></label>
                                <input id="change-available" name="inputBookAvailable" type="number"
                                       class="form-control"
                                       pattern="^[0-9]{1,*}$"
                                       data-error="<fmt:message key='modal.available_err'/>"
                                       placeholder="<fmt:message key='modal.new available'/>" required>
                                <div class="help-block with-errors " id="edit-available-error"></div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                                    <fmt:message key='modal.close'/>
                                </button>
                                <button type="button" class="btn btn-primary" id="changeAvailable"
                                        onclick="validateBookAvailable(${sessionScope.book.id})">
                                    <fmt:message key='modal.confirm'/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="delete-book-modal" class="modal fade" tabindex="-1" role="dialog"
             aria-labelledby="deleteBookModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h3 class="modal-title" id="deleteBookModalLabel">
                            <fmt:message key='admin.sure'/>
                        </h3>
                    </div>
                    <div class="modal-body">
                        <p> <fmt:message key='admin.remove_book'/></p>
                        <div class="help-block with-errors error" id="delete_book-error"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-secondary" data-dismiss="modal">
                            <fmt:message key='modal.close'/>
                        </button>
                        <button type="button" class="btn btn-danger" id="deleteBook"
                                onclick="deleteBook(${sessionScope.book.id})">
                            <fmt:message key='admin.delete'/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>

</template:main>
</body>
</html>