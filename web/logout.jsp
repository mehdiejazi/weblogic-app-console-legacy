<%@page import="controller.util.MyLoggerUtil"%>
<%
    MyLoggerUtil mlu = new MyLoggerUtil();
    mlu.doLog(request, "User logout... (Username:" + request.getRemoteUser() + ")");

    request.logout();
    
    System.out.println(request.getRemoteUser());
    
    if (request.getQueryString() != null) {
        response.sendRedirect("login.jsp?" + request.getQueryString());
    } else {
        response.sendRedirect("login.jsp");
    }

%>