<%@page import="model.to.UserRoleTo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bl.UsersBl"%>
<%@page  pageEncoding="UTF-8" %>
<%@page import="org.apache.catalina.realm.GenericPrincipal"%>
<div class="art-nav-l"></div>
<div class="art-nav-r"></div>
<div class="art-nav-wrapper">
    <div class="art-nav-inner">
        <ul class="art-menu">
            <%
                try {
                    String strUserName = request.getRemoteUser();
                    UsersBl urbl = new UsersBl();
                    ArrayList<UserRoleTo> UsersTos = (ArrayList<UserRoleTo>) urbl.GetUserRoles(strUserName);


                    for (UserRoleTo urto : UsersTos) {
                        out.println("<li>");
                        String strClass = " ";

                        if (request.getRequestURI().contains(urto.getRolename())) {
                            strClass = " class=\"active\" ";
                        }
                        out.println("<a href=\"" + request.getServletContext().getContextPath() + "/" + urto.getRolename() + "/index.jsp \"" + strClass + ">");
                        out.println("<span class=\"l\"> </span>");
                        out.println("<span class=\"r\"> </span>");
                        String strRoleTitle = "";
                        if (urto.getRolename().equals("admin")) {
                            strRoleTitle = "پنل مدیریت";
                        } else if (urto.getRolename().equals("employee")) {
                            strRoleTitle = "پنل کاربری";
                        }
                        out.println("<span class=\"t\">" + strRoleTitle + "</span>");
                        out.println("</a>");
                        out.println("</li>");
                    }
                } catch (Exception ex) {
                }
            %>
        </ul>
    </div>
</div>