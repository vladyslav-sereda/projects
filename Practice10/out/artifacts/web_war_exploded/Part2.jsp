<%--
  Created by IntelliJ IDEA.
  User: sered
  Date: 17.05.2017
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Part2</title>
</head>
<body>
<table border="1">
    <tr>
        <c:forEach var="i" begin="0" end="9">
            <td><c:if test="${i>0}">${i}</c:if></td>
        </c:forEach>
    </tr>
    <c:forEach var="i" begin="1" end="9">
        <tr>
            <td>${i}</td> <c:forEach var="j" begin="1" end="9"><td>${i*j}</td></c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
