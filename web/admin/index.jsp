<%@page  pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en">
    <head>
        <title> منوی مدیریت </title>
        <link rel="stylesheet" href="../theme/style.css" type="text/css" media="screen" />
        <!--[if IE 6]><link rel="stylesheet" href="../style.ie6.css" type="text/css" media="screen" /><![endif]-->
        <!--[if IE 7]><link rel="stylesheet" href="../style.ie7.css" type="text/css" media="screen" /><![endif]-->
        <script type="text/javascript" src="../theme/jquery.js"></script>
        <script type="text/javascript" src="../theme/script.js"></script>
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
                                <h2 class="art-postheader"> منوی مدیریت  </h2>
                                <div class="art-postcontent">
                                    <!-- content -->
                                         <%
                                            if (request.getParameter("msg") != null) {
                                                if (request.getParameter("msg").equals("1")) {
                                                    out.write("<div class=\"art-error\">");
                                                    out.write("شما اجازه دسترسی به این صفحه را ندارید!!");
                                                    out.write("</div>");
                                                } 
                                            }
                                        %>
                                    <!-- /content -->
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
