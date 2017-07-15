<%--
  Created by IntelliJ IDEA.
  User: sered
  Date: 17.05.2017
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Part3</title>
</head>
<body>

<form action="Part3Servlet" method="post" >
    <label>
        <input type="text" name="name">
        <input type="submit" name="submit">
        <input type="submit" name="remove" value="remove" >
    </label>
</form>


<c:if test="${not empty sessionScope.names}">
    <c:forEach items="${sessionScope.names}" var="name">
        ${name}<br>
    </c:forEach>
</c:if>


</body>
</html>
