<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<template:main>

    <jsp:body>
        <%-- set the locale --%>
        <fmt:setLocale value="${param.lang}" scope="session"/>

        <%-- load the bundle (by locale) --%>
        <fmt:setBundle basename="resources"/>

        <%-- set current locale to session --%>
        <c:set var="currentLocale" value="${param.lang}" scope="session"/>

    </jsp:body>
</template:main>
</body>
</html>