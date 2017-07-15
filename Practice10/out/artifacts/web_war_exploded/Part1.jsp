<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Part1</title>
</head>
<body>
<table border="1">
    <%int i;%>
    <%int j;%>
    <tr>
        <% for (i = 0; i < 10; i++) {%>
        <td><%if (i != 0) {%> <%=i%> <%}%></td>
        <%
            }
            ;
        %>
    </tr>
    <%for (i = 1; i < 10; i++) {%>
    <tr>
        <td> <%=i%> </td>
        <%for (j = 1; j < 10; j++) {%>
        <td> <%=i*j%> </td>
        <%};%>
    </tr>
    <%};%>
</table>
</body>
</html>