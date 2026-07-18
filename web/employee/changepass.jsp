<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="controller.util.WebParamUtil"%>
<%@page  pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en">
    <head>
        <title>تغییر رمزعبور</title>
        <link rel="stylesheet" href="../theme/style.css" type="text/css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" href="../style.ie6.css" type="text/css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" href="../style.ie7.css" type="text/css" media="screen" /><![endif]-->
        <script type="text/javascript" src="../theme/jquery.js"></script>
        <script type="text/javascript" src="../theme/script.js"></script>
        <link href="../theme/css/custom-theme/jquery-ui-1.10.4.custom.css" rel="stylesheet"/>
        <script src="../theme/js/jquery-ui-1.10.4.custom.js"></script>
        <link rel="Shortcut Icon" href="../theme/images/Logo.ico"/>        

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
                                    <h2 class="art-postheader">
                                        تغییر رمزعبور
                                    </h2>
                                    <div class="art-postcontent">
                                        <!-- content -->
                                        <div id="art-title">
                                            تغییر رمزعبور
                                        </div>
                                        <div id ="art-content">
                                            <form method="post" action="changepass.do">
                                                <table>
                                                    <tr>
                                                        <td>
                                                            رمز عبور فعلی:
                                                        </td>
                                                        <td>
                                                            <input type="password" name="txtOldPass" class="text-ltr"/>
                                                        </td>
                                                    </tr>                                                    
                                                    <tr>
                                                        <td>
                                                            رمز عبور:
                                                        </td>
                                                        <td>
                                                            <input type="password" name="txtPass" class="text-ltr"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            تکرار رمزعبور:
                                                        </td>
                                                        <td>
                                                            <input type="password" name="txtPassRepeat" class="text-ltr"/>                                                            
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <input type="submit" value="ارسال" class ="art-mybutton"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </form>
                                        </div>

                                        <%
                                            if (request.getParameter("msg") != null) {
                                                out.println("<br />");
                                                if (request.getParameter("msg").equals("0")) {
                                                    out.write("<div class=\"art-validation\">");
                                                    out.write("لطفا اطلاعات فرم را کامل وارد کنید");
                                                    out.write("</div>");
                                                } else if (request.getParameter("msg").equals("1")) {
                                                    out.write("<div class=\"art-validation\">");
                                                    out.write("رمز عبور و تکرار رمز عبور مساوی نمی باشد");
                                                    out.write("</div>");
                                                } else if (request.getParameter("msg").equals("2")) {
                                                    out.write("<div class=\"art-info\">");
                                                    out.write("تغییر رمزعبور به درستی انجام شد");
                                                    out.write("</div>");
                                                } else if (request.getParameter("msg").equals("3")) {
                                                    out.write("<div class=\"art-error\">");
                                                    out.write("عملیات با مشکل مواجه شد");
                                                    out.write("</div>");
                                                } else if (request.getParameter("msg").equals("4")) {
                                                    out.write("<div class=\"art-error\">");
                                                    out.write("رمزعبور قبلی را صحیح وارد نمایید");
                                                    out.write("</div>");
                                                }
                                            }
                                        %>

                                        <!-- /content -->
                                    </div>
                                    <div class="cleared"></div>
                                </div>
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