<%@page import="model.bl.LogBl"%>
<%@page import="model.bl.UsersBl"%>
<%@page  pageEncoding="UTF-8" %>
<div class="art-header-center">
    <div>

    </div>
</div>
<div class="art-header-wrapper">
    <div class="art-header-inner">
        <div class="art-headerobject"></div>
        <div id="art-user-info">
            <div class="user-detail-div">
                <div class="user-detail">
                    <table>
                        <tr>
                            کاربر گرامی
                            <%
                                UsersBl ubl = new UsersBl();
                                out.print(ubl.GetUserByUsername(request.getRemoteUser()).getFullname());
                            %>
                        <br />
                        خوش آمدید.
                        </td>
                        </tr>
                        <tr>
                            <td>
                                آخرین IP:
                            </td>
                            <td>
                                <%=(new LogBl()).GetLastLogByUserName(request.getRemoteUser()).getIP()%>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <a href="../logout.jsp" class="user-detail-logout">
                                    <span>
                                        <img src="../theme/images/logout2.png" />
                                        خروج کابر
                                    </span>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="user-image">
                <img src="../theme/images/user.png" alt="user" class="art-sidebar-image" />
            </div>
        </div>
    </div>
</div>