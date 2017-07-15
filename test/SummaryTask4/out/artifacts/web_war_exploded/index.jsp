<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html>
<title><fmt:message key='title.library'/></title>
<body>
<template:main>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="jumbotron">
                    <h1 class="display-3"><fmt:message key='main.hello'/></h1>
                    <p class="lead"><fmt:message key='main.info'/></p>
                    <hr class="my-4">
                    <p class="lead">
                        <c:if test="${!(sessionScope.user.role == 'LIBRARIAN' || sessionScope.user.role == 'ADMIN')}">
                            <a class="btn btn-primary btn-lg" href="Controller?command=showAvailableBooks"
                               role="button"><fmt:message key='main.show_books'/>
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'LIBRARIAN' || sessionScope.user.role == 'ADMIN'}">
                            <a class="btn btn-primary btn-lg" href="Controller?command=showAllBooks" role="button">
                                Show books
                            </a>
                        </c:if>

                    </p>
                </div>
            </div>
        </div>
    </jsp:body>
</template:main>
</body>
</html>