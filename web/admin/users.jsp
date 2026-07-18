<%@page import="view.util.UserTableDecorUtil"%>
<%@page import="model.to.UserPageTo"%>
<%@page import="model.to.PagesTo"%>
<%@page pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.to.UsersTo"%>
<%@page import="model.to.UserRoleTo"%>
<%@taglib uri="http://displaytablib.com/tld/ui" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en">
    <head>
        <title>مدیریت کاربران</title>
        <link rel="stylesheet" href="../theme/style.css" type="text/css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" href="../style.ie6.css" type="text/css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" href="../style.ie7.css" type="text/css" media="screen" /><![endif]-->
        <script type="text/javascript" src="../theme/jquery.js"></script>
        <script type="text/javascript" src="../theme/script.js"></script>
        <link href="../theme/css/custom-theme/jquery-ui-1.10.4.custom.css" rel="stylesheet"/>
        <script src="../theme/js/jquery-ui-1.10.4.custom.js"></script>
        <link rel="Shortcut Icon" href="../theme/images/Logo.ico"/>            
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <div id="art-main">
            <div class="art-header">
                <jsp:include page="../theme/header.jsp"/>
            </div>
            <div class="art-nav">
                <jsp:include page="../theme/navigation.jsp"/>
            </div>
            <div class="art-sheet">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1">
                            <jsp:include page="../theme/sidebar.jsp"/>
                        </div>
                        <div class="art-post-body">
                            <div class="art-post-inner art-article">
                                <div class="art-post-body-cadr">
                                    <h4 class="art-postheader"> مدیریت کاربران</h4>
                                    <div class="art-postcontent">
                                        <!-- content -->
                                        <%
                                            UsersTo usersTo = new UsersTo();
                                            usersTo.setId(-1000);
                                            usersTo.setPassword("");
                                            usersTo.setUsername("");
                                            usersTo.setFullname("");
                                            String strChkAdmin = "";
                                            String strChkEmployee = "";
                                            if (request.getAttribute("UsersTo") != null) {
                                                usersTo = (UsersTo) request.getAttribute("UsersTo");
                                                for (UserRoleTo userRoleTo : usersTo.getUserRoleTos()) {
                                                    if (userRoleTo.getRolename().equals("admin")) {
                                                        strChkAdmin = "checked=\"checked\"";
                                                    }
                                                    if (userRoleTo.getRolename().equals("employee")) {
                                                        strChkEmployee = "checked=\"checked\"";
                                                    }
                                                }
                                            }
                                        %>
                                        <form method="post" action="./addorupdateusers.do">
                                            <div id="accordion">
                                                <h4>اطلاعات کاربری</h4>
                                                <div>
                                                    <table>
                                                        <tr>
                                                            <td>نام کاربری:<input name="txtId" type="hidden" value="<%=usersTo.getId()%>" /></td>
                                                            <td> <input name="txtUsername" type="text" value="<%=usersTo.getUsername()%>" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>رمز عبور:</td>
                                                            <td><input name="txtPassword" type="password" value="" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>نام و نام خانوادکی:</td>
                                                            <td><input name="txtFullname" type="text" value="<%=usersTo.getFullname()%>" class="text-rtl"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <h4>نقش ها</h4>
                                                <div>
                                                    <table>
                                                        <tr>
                                                            <td colspan="2"> 
                                                                <input name="chkAdmin" type="checkbox" <%=strChkAdmin%>/>
                                                                <label for="chkAdmin">دسترسی مدیریتی</label><br/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2"> 
                                                                <input name="chkEmployee" type="checkbox" <%=strChkEmployee%>/>
                                                                <label for="chkAdmin">دسترسی کاربری</label>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <h4>صفحه ها</h4>
                                                <div>
                                                    <table>
                                                        <%
                                                            ArrayList<UserPageTo> UserPageTos = new ArrayList<UserPageTo>();
                                                            if (request.getAttribute("UserPageTos") != null) {
                                                                UserPageTos = (ArrayList<UserPageTo>) request.getAttribute("UserPageTos");
                                                            }

                                                            if (request.getAttribute("PagesTos") != null) {
                                                                ArrayList<PagesTo> PagesTos = (ArrayList<PagesTo>) request.getAttribute("PagesTos");
                                                                for (PagesTo pto : PagesTos) {
                                                                    String strCheck = "";
                                                                    if (UserPageTos.contains(pto.getPagename())) {
                                                                        strCheck = "checked=\"checked\"";
                                                                    }
                                                                    out.println("<tr>");
                                                                    out.println("<td colspan=\"2\">");
                                                                    out.print("<input name=\"chkPage" + pto.getId() + "\" type=\"checkbox\" " + strCheck + " />");
                                                                    out.print("<label for=\"chkPage" + pto.getId() + "\">" + pto.getPagetitle() + " </label>");
                                                                    out.println("</td>");
                                                                    out.println("</tr>");
                                                                }
                                                            }
                                                        %>
                                                    </table>
                                                </div>
                                            </div>
                                            <table>
                                                <tr>
                                                    <td colspan="2"> 
                                                        <input type="submit" value="ثبت"  class="art-mybutton"/>
                                                        <input type="reset" value="درج جدید"  class="art-mybutton"/>
                                                        <br />
                                                        <div id="lblMessage">
                                                            <%
                                                                if (request.getParameter("msg") != null) {
                                                                    String strMsg =
                                                                            String.valueOf(request.getParameter("msg"));
                                                                    String[] arrMsgs = strMsg.split("-");
                                                                    for (String item : arrMsgs) {
                                                                        if (item.equals("fillitems") == false) {
                                                                            if (item.equals("user")) {
                                                                                out.print("<div class=\"art-validation\">");
                                                                                out.print("تطفا نام کاربری را وارد کنید" + "<br />");
                                                                                out.print("</div>");
                                                                            } else if (item.equals("pass")) {
                                                                                out.print("<div class=\"art-validation\">");
                                                                                out.print("لطفا رمزعبور را وارد کنید" + "<br />");
                                                                                out.print("</div>");
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            %>  
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>  
                                        <display:table decorator="view.util.UserTableDecorUtil" class="art-table-content" name="${UsersTos}" pagesize="5"  sort="list" requestURI="users.do" >  
                                            <display:column property="index" title="ردیف"  headerClass="art-table-content-header" class="art-table-content"/>
                                            <display:column property="fullname" title="نام و نام خانوادگی"  headerClass="art-table-content-header" class="art-table-content"/>
                                            <display:column property="username" title= "نام کاربر" headerClass="art-table-content-header" class="art-table-content"/>
                                            <display:column property="userroletostags" title="نقش ها" headerClass="art-table-content-header" class="art-table-content"/>
                                            <display:column property="edit" title="ویرایش"  headerClass="art-table-content-header" class="art-table-content"/>
                                            <display:column property="delete" title="حذف"  headerClass="art-table-content-header" class="art-table-content"/>
                                            <display:column property="log" title="لاگ"  headerClass="art-table-content-header" class="art-table-content"/>
                                        </display:table>

                                        <!-- /content -->
                                    </div>
                                </div>
                                <div class="cleared"></div>
                            </div>
                            <div class="cleared"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="art-footer">
                <jsp:include page="../theme/footer.jsp"/>
            </div>
        </div>
    </body>
</html>
