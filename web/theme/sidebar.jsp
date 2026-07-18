<%@page import="model.to.PagesTo"%>
<%@page import="model.bl.PagesBl"%>
<%@page import="java.util.List"%>
<%@page import="model.to.UserPageTo"%>
<%@page import="model.bl.UserPageBl"%>
<%@page import="model.to.UsersTo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bl.LogBl"%>
<%@page import="model.bl.UsersBl"%>
<%@page  pageEncoding="UTF-8" %>
<div class="art-vmenublock">
    <div class="art-vmenublock-body">
        <div class="art-vmenublockcontent">
            <div class="art-vmenublockcontent-body">
                <div>
                    <ul class="art-vmenu">
                        <%
                            String strMenu = "<li><a href=\"####\" class=\"active\"><span class=\"l\"> </span><span class=\"r\"> </span><span class=\"t\">@@@@</span></a></li>";

                            UsersBl usersBl = new UsersBl();
                            UsersTo usersTo = new UsersTo();
                            PagesBl pbl = new PagesBl();
                            usersTo = usersBl.GetUserByUsername(request.getRemoteUser());
                            UserPageBl userPageBl = new UserPageBl();
                            List<UserPageTo> lstUserPageTos = userPageBl.GetUserPagesByUserId(usersTo.getId());
                            
                            if (request.getRequestURI().contains("admin")) {
                                for (UserPageTo upto : lstUserPageTos) {
                                    PagesTo pto = pbl.GetPagesToByPageName(upto.getPageid());
                                    try {
                                        if (pto.getPagename().contains("admin")) {
                                            String strPageName = pto.getPagename();
                                            strPageName = pto.getPagename().replace("admin/", "./");
                                            strPageName = strPageName.replace("users.jsp", "users.do");
                                            out.println(strMenu.replaceAll("####", strPageName).replaceAll("@@@@", pto.getPagetitle()));
                                            //System.out.println(strMenu.replaceAll("####", strPageName).replaceAll("@@@@", pto.getPagetitle()));
                                        }
                                    } catch (Exception ex) {
                                    }
                                }
                            }
                            if (request.getRequestURI().contains("employee")) {
                                for (UserPageTo upto : lstUserPageTos) {
                                    PagesTo pto = pbl.GetPagesToByPageName(upto.getPageid());
                                    try {
                                        if (pto.getPagename().contains("employee")) {
                                            String strPageName = pto.getPagename();
                                            strPageName = pto.getPagename().replace("employee/", "./");
                                            strPageName = strPageName.replace("userbackup.jsp", "userbackup.do");
                                            out.println(strMenu.replaceAll("####", strPageName).replaceAll("@@@@", pto.getPagetitle()));
                                            //System.out.println(strMenu.replaceAll("####", strPageName).replaceAll("@@@@", pto.getPagetitle()));
                                        }
                                    } catch (Exception ex) {
                                    }
                                }
                            }%>                    
                    </ul>
                </div>
                <div class="cleared"></div>
            </div>
        </div>
        <div class="cleared"></div>
    </div>
</div>
<div class="cleared"></div>
<div id="art-user-log">
    <table>
        <tr><td>آخرین فعالیت کاربر:</td></tr>
        <tr><td style="direction: ltr; text-align: left;"><%=(new LogBl()).GetLastLogByUserName(request.getRemoteUser()).getEvent()%></td></tr>
        <tr><td>آخرین صفحه کاربر:</td></tr>
        <tr>
            <td>
                <%                    ArrayList<String> Pages;


                    try {
                        Pages = (ArrayList<String>) request.getSession().getAttribute("Pages");
                        //System.out.println(Pages.get(1));
                    } catch (Exception ex) {

                        Pages = new ArrayList<String>();
                        Pages.add("");
                        Pages.add("");
                    }
                    int lngOneIndexToEnd = Pages.size() - 2;
                    if (lngOneIndexToEnd
                            < 0) {
                        lngOneIndexToEnd = 0;
                    }

                    out.println(
                            "<a href=\"" + Pages.get(lngOneIndexToEnd) + "\">" + Pages.get(lngOneIndexToEnd) + "</a>");
                %>
            </td>
        </tr>
    </table>

</div>