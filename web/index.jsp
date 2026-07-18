<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%--<a href="employee/index.jsp" >Employee Index</a>
        <br />
        <a href="admin/index.jsp" >Admin Index</a>--%>
        <%
            response.sendRedirect("login.jsp");
        %>
    </body>
</html>

