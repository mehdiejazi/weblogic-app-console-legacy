<%@page import="controller.util.WebParamUtil"%>
<%@page  pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en">
    <head>
        <title> بروررسانی نرم افزار</title>
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
                                    <h2 class="art-postheader"> بروررسانی نرم افزار  </h2>
                                    <div class="art-postcontent">
                                        <!-- content -->
                                        <form method="post" action="deploy.do" enctype="multipart/form-data">
                                            <div id="accordion">
                                                <h4>قدم اول</h4>
                                                <div style="height: auto!important;">
                                                    <table>
                                                        <tr>
                                                            <td>
                                                                Weblogic 
                                                            </td>
                                                            <td>
                                                                <img src="../theme/images/oracle-logo.jpg" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>نام کاربری:</td>
                                                            <td> <input name="txtWlUsername" type="text" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>رمز عبور:</td>
                                                            <td><input name="txtWlPassword" type="password" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>دلیل بروزرسانی</td>
                                                            <td><input name="txtReason" type="password" class="text-rtl"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <h4>قدم دوم</h4>
                                                <div style="height: auto!important;">
                                                    <table>
                                                        <tr>
                                                            <td colspan="2">
                                                                Host            
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>نام کاربری:</td>
                                                            <td> <input name="txtHUsername" type="text" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>رمز عبور:</td>
                                                            <td><input name="txtHPassword" type="password" class="text-ltr"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <%
                                                    WebParamUtil wpu = new WebParamUtil();
                                                    wpu.SetFileName(request.getSession().getServletContext().getRealPath("/") + "/WEB-INF/web-param.xml");
                                                    String sftpDeployHostIP = wpu.GetsftpDeployHostIP();
                                                    String sftpBackupHostIP = wpu.GetsftpBackupHostIP();
                                                    if (sftpDeployHostIP.equals(sftpBackupHostIP) == false) {%>
                                                <h4>قدم سوم</h4>
                                                <div style="height: auto!important;">
                                                    <table>
                                                        <tr>
                                                            <td colspan="2">
                                                                سرور پشتیبان گیری
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>نام کاربری:</td>
                                                            <td> <input name="txtBkUsername" type="text" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>رمز عبور:</td>
                                                            <td><input name="txtBkPassword" type="password" class="text-ltr"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <%}%>
                                                <h4>قدم نهایی</h4>
                                                <div style="height: auto!important;">
                                                    <div>
                                                        <input type="hidden" name="method" value="upload" class="text-ltr"/>
                                                    </div>
                                                    <div>
                                                        <input type="file" id="warfile" name="myfile" class="text-ltr"/>
                                                    </div>
                                                    <div>
                                                        <table>
                                                            <tr>
                                                                <td>
                                                                    تصویر امنیتی
                                                                    <br/>
                                                                    <a href="deploy.jsp">
                                                                        <span>
                                                                            <img class="art-captcha" src="../captcha-image.jpg" alt="captcha" />
                                                                        </span>
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <input name="txtCaptcha" type="" class="text-ltr-captcha"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2">
                                                                    <div class="art-info">
                                                                        تصویر امنیتی به حروف کوچک و بزرگ حساس نمی باشد
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                    <div>
                                                        <input type="submit" value="انجام بروزرسانی"  class="art-mybutton" />
                                                    </div>
                                                </div>
                                            </div>
                                            <%
                                                if (request.getParameter("msg") != null) {
                                                    if (request.getParameter("msg").equals("1")) {
                                                        out.write("<div class=\"art-info\">");
                                                        out.write("عملیات با موفقیت انجام شد");
                                                        out.write("</div>");
                                                    } else if (request.getParameter("msg").equals("2")) {
                                                        out.write("<div class=\"art-error\">");
                                                        out.write("خطا در انتقال فایل به سرور");
                                                        out.write("</div>");
                                                    } else if (request.getParameter("msg").equals("3")) {
                                                        out.write("<div class=\"art-error\">");
                                                        out.write("خطا در ایجاد فایل پشتیبان");
                                                        out.write("</div>");
                                                    } else if (request.getParameter("msg").equals("4")) {
                                                        out.write("<div class=\"art-error\">");
                                                        out.write("خطا در بروزرسانی نرم افزار");
                                                        out.write("</div>");
                                                    } else if (request.getParameter("msg").equals("5")) {
                                                        out.write("<div class=\"art-error\">");
                                                        out.write("خطا در آپلود");
                                                        out.write("</div>");
                                                    } else if (request.getParameter("msg").equals("6")) {
                                                        out.write("<div class=\"art-error\">");
                                                        out.write("عملیات با مشکل مواجه شد");
                                                        out.write("</div>");
                                                    } else if (request.getParameter("msg").equals("7")) {
                                                        out.write("<div class=\"art-validation\">");
                                                        out.write("لطفا اطلاعات فرم را کامل وارد کنید");
                                                        out.write("</div>");
                                                    } else if (request.getParameter("msg").equals("errcaptcha")) {
                                                        out.print("<div class=\"art-error\">");
                                                        out.print("تصویر امنیتی را صحیح وارد کنید");
                                                        out.print("</div>");
                                                    }
                                                }
                                            %>
                                        </form>
                                        <!-- /content -->
                                    </div>
                                    <div class="cleared"></div>
                                </div>
                            </div>
                            <div class="cleared"></div>
                        </div>
                        ;                    </div>
                </div>
            </div>
            <div class="art-footer">
                <jsp:include page="../theme/footer.jsp"/>
            </div>
        </div>
    </body>
</html>




