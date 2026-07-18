<%@page import="controller.util.WebParamUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.util.EncryptDESUtil"%>
<!DOCTYPE html>
<html>
    <head>
        <title>مدیریت پارامتر</title>
        <link rel="stylesheet" href="../theme/style.css" type="text/css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" href="../style.ie6.css" type="text/css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" href="../style.ie7.css" type="text/css" media="screen" /><![endif]-->
        <script type="text/javascript" src="../theme/jquery.js"></script>
        <script type="text/javascript" src="../theme/script.js"></script>
        <link rel="Shortcut Icon" href="../theme/images/Logo.ico"/>        
        <%
            WebParamUtil wpu = new WebParamUtil();
            wpu.SetFileName(request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/web-param.xml");

            String strAppName = wpu.GetappName();
            String strWlAdminURL = wpu.GetwlAdminURL();
            String strSftpDeployHostIP = wpu.GetsftpDeployHostIP();
            String strSftpDeployWorkingDir = wpu.GetsftpDeployWorkingDir();
            String strSftpBackupHostIP = wpu.GetsftpBackupHostIP();
            String strSftpBackupWorkingDir = wpu.GetsftpBackupWorkingDir();
        %>        
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
                            <div class="art-post-body-cadr">
                                <div class="art-post-inner art-article">
                                    <div class="art-postcontent">
                                        <!-- content -->
                                        <div id="art-title">
                                            مدیریت پارامتر
                                        </div>
                                        <div id ="art-content">
                                            <form method="post" action= "./setparams.do" >
                                                <table id="tblTableLtr">
                                                    <tr>
                                                        <td>Application Name:</td>
                                                        <td><input type="text" name ="appName" value="<%=strAppName%>" class="text-ltr"/> </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Weblogic Admin URL:</td>
                                                        <td><input type="text" name ="wlAdminURL" value="<%=strWlAdminURL%>" class="text-ltr"/> </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Deploy Host IP:</td>
                                                        <td><input type="text" name ="sftpDeployHostIP" value="<%=strSftpDeployHostIP%>" class="text-ltr"/> </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Deploy Host Working Directory:</td>
                                                        <td><input type="text" name ="sftpDeployWorkingDir" value="<%=strSftpDeployWorkingDir%>" class="text-ltr"/> </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Backup Host IP:</td>
                                                        <td><input type="text" name ="sftpBackupHostIP" value="<%=strSftpBackupHostIP%>" class="text-ltr"/> </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Backup Host Working Directory:</td>
                                                        <td><input type="text" name ="sftpBackupWorkingDir" value="<%=strSftpBackupWorkingDir%>" class="text-ltr"/> </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <a href="setparams.jsp">
                                                                <span>
                                                                    <img class="art-captcha" src="../captcha-image.jpg" alt="captcha" />
                                                                </span>
                                                            </a>
                                                            <input name="txtCaptcha" type="text" class="text-ltr-captcha"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <input type="submit" value="دخیره پارامترها" class ="art-mybutton"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <%
                                                    if (request.getParameter("msg") != null) {
                                                        if (request.getParameter("msg").equals("0")) {
                                                            out.write("<div class=\"art-error\">");
                                                            out.write("لطفا اطلاعات فرم را کامل وارد نمایید");
                                                            out.write("</div>");
                                                        } else if (request.getParameter("msg").equals("1")) {
                                                            out.write("<div class=\"art-info\">");
                                                            out.write("تغییرات به درستی ذخیره گردید.");
                                                            out.write("</div>");
                                                        } else if (request.getParameter("msg").equals("errcaptcha")) {
                                                            out.print("<div class=\"art-error\">");
                                                            out.print("تصویر امنیتی را صحیح وارد کنید");
                                                            out.print("</div>");
                                                        }
                                                    }
                                                %>
                                            </form>
                                        </div>
                                        <br />
                                        <div class="art-info">
                                            تصویر امنیتی به حروف کوچک و بزرگ حساس نمی باشد
                                        </div>

                                        <!-- /content -->
                                    </div>
                                    <div class="cleared"></div>
                                </div>
                                <div class="cleared"></div>
                            </div>
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
