<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="controller.util.WebParamUtil"%>
<%@page  pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en">
    <head>
        <title>ایجاد رمز عبور</title>
        <link rel="stylesheet" href="../theme/style.css" type="text/css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" href="../style.ie6.css" type="text/css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" href="../style.ie7.css" type="text/css" media="screen" /><![endif]-->
        <script type="text/javascript" src="../theme/jquery.js"></script>
        <script type="text/javascript" src="../theme/script.js"></script>
        <link href="../theme/css/custom-theme/jquery-ui-1.10.4.custom.css" rel="stylesheet"/>
        <script src="../theme/js/jquery-ui-1.10.4.custom.js"></script>
        <link rel="Shortcut Icon" href="../theme/images/Logo.ico"/>        
        <%
            String txtDecEncoded = "";
            String txtEncEncoded = "";

            String txtEncPassword = "";
            String txtDecPassword = "";
            if (request.getAttribute("enc") != null) {
                txtEncEncoded = request.getAttribute("enc").toString();
                txtEncPassword = request.getAttribute("txtEncPassword").toString();
            }
            if (request.getAttribute("dec") != null) {
                txtDecEncoded = request.getAttribute("dec").toString();
                txtDecPassword = request.getAttribute("txtDecPassword").toString();
            }
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
                            <div class="art-post-inner art-article">
                                <div class="art-post-body-cadr">
                                    <h2 class="art-postheader">ایجاد رمز عبور</h2>
                                    <div class="art-postcontent">
                                        <!-- content -->
                                        <div id="art-title">
                                            رمز گذاری
                                        </div>
                                        <div id ="art-content">
                                            <form method="post" action="passmaker.do">
                                                <table id="tblTableLtr">
                                                    <tr>
                                                        <td>Password:</td>
                                                        <td>
                                                            <input type="hidden" name ="action" value="enc"/>
                                                            <input type="text" name ="txtEncPassword" value="<%=txtEncPassword%>" class="text-ltr" />
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td>Encoded:</td>
                                                        <td>
                                                            <input type="text" name ="txtEncEncoded" value="<%=txtEncEncoded%>" disabled="disabled" class="text-ltr"/>
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
                                        <br />
                                        <div id="art-title"">
                                             رمز گشایی
                                    </div>
                                    <div id ="art-content">
                                        <form method="post" action="passmaker.do">
                                            <table id="tblTableLtr">
                                                <tr>
                                                    <td>Password:</td>
                                                    <td><input type="text" name ="txtDecPassword" value="<%=txtDecPassword%>" class="text-ltr"/> </td>
                                                </tr>
                                                <tr>
                                                    <td>Decoded:</td>
                                                    <input type="hidden" name ="action" value="dec"/>
                                                    <td><input type="text" name ="txtDecEncoded" value="<%=txtDecEncoded%>" disabled="disabled" class="text-ltr"/> </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <input type="submit" value="ارسال" class ="art-mybutton"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form>
                                    </div>

                                    <br />
                                    <%
                                        if (request.getParameter("msg") != null) {
                                            if (request.getParameter("msg").equals("1")) {
                                            }
                                            if (request.getParameter("msg").equals("2")) {
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




