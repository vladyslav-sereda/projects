<%--
  Created by IntelliJ IDEA.
  User: sered
  Date: 17.05.2017
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<form action="Part4Servlet" method="post">
    <input type="text" name="login" placeholder="login">
    <input type="text" name="pass" placeholder="password">
    <input type="submit" value="authenticate">
</form>

</body>
</html>
