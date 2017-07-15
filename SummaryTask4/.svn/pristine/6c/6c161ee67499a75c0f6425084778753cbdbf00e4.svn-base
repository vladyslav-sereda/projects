<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<html>
<head>
    <title><fmt:message key='librarian.title'/></title>
</head>
<body>
<template:main>
    <jsp:body>
        <div class="container">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs nav-justified justify-content-center" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active"
                       data-toggle="tab" href="#newOrders" role="tab">
                        <h3 class="display-4"><fmt:message key='librarian.new_orders'/></h3>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link "
                       data-toggle="tab" href="#confirmedOrders" role="tab">
                        <h3 class="display-4"><fmt:message key='librarian.delivered_orders'/></h3>
                    </a>
                </li>
            </ul>
            <div class="row">
                <!-- Tab panes -->
                <div class="tab-content">

                    <div class="tab-pane active" id="newOrders" role="tabpanel">
                        <div class="container">
                            <table class="table table-striped" id="newOrdersTable">
                                <thead>
                                <tr>
                                    <th>
                                        <fmt:message key='order.id'/>
                                    </th>
                                    <th>
                                        <fmt:message key='books.id'/>
                                    </th>
                                    <th>
                                        <fmt:message key='user.name'/>
                                    </th>
                                    <th>
                                        <fmt:message key='user.email'/>
                                    </th>
                                    <th>
                                        <fmt:message key='books.deadline'/>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sessionScope.newOrders}" var="newOrder">
                                    <c:forEach items="${sessionScope.newOrdersReaders}" var="newOrdersReader">
                                        <c:if test="${newOrder.userId == newOrdersReader.id}">
                                            <tr id="nOrder${newOrder.id}">
                                                <td>${newOrder.id}</td>
                                                <td>${newOrder.bookId}</td>
                                                <td>${newOrdersReader.name}</td>
                                                <td>${newOrdersReader.email}</td>
                                                <td><my:date>${newOrder.deadline}</my:date></td>
                                                <td></td>
                                                <td>
                                                    <a class="btn btn-success"
                                                       onclick="confirmOrder(${newOrder.id},${newOrder.bookId},'${newOrdersReader.name}',
                                                               '${newOrdersReader.email}',${newOrder.deadline})">
                                                        <fmt:message key='modal.confirm'/>
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

                    <div class="tab-pane" id="confirmedOrders" role="tabpanel">
                        <table class="table table-striped" id="confirmedOrdersTable">
                            <thead>
                            <tr>
                                <th>
                                    <fmt:message key='order.id'/>
                                </th>
                                <th>
                                    <fmt:message key='books.id'/>
                                </th>
                                <th>
                                    <fmt:message key='user.name'/>
                                </th>
                                <th>
                                    <fmt:message key='user.email'/>
                                </th>
                                <th>
                                    <fmt:message key='books.deadline'/>
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.confirmedOrders}" var="confirmedOrder">
                                <c:forEach items="${sessionScope.confirmedReaders}" var="confirmedReader">
                                    <c:if test="${confirmedOrder.userId == confirmedReader.id}">
                                        <tr id="cOrder${confirmedOrder.id}">
                                            <td>${confirmedOrder.id}</td>
                                            <td>${confirmedOrder.bookId}</td>
                                            <td>${confirmedReader.name}</td>
                                            <td>${confirmedReader.email}</td>
                                            <td><my:date>${confirmedOrder.deadline}</my:date></td>
                                            <td>
                                                <a class="btn btn-success"
                                                   onclick="closeOrder(${confirmedOrder.id})">
                                                    <fmt:message key='modal.close'/>
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
    </jsp:body>
</template:main>
</body>
</html>
