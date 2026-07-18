<%@page import="model.to.LogTo"%>
<%@page import="java.util.ArrayList"%>
<%@page  pageEncoding="UTF-8" %>
<%@taglib uri="http://displaytablib.com/tld/ui" prefix="display"%>
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
                                <h2 class="art-postheader">نمایش لاگ کاربر  </h2>
                                <div class="art-postcontent">
                                    <!-- content -->
                                    <display:table  class="art-table-content" name="${LogTos}" pagesize="15"  sort="list" requestURI="showlog.do" >  
                                        <display:column property="IP" title="شناسه سیستم" headerClass="art-table-content-header"  class="art-table-content"/>  
                                        <display:column property="username" title= "نام کاربر" headerClass="art-table-content-header" class="art-table-content"/>
                                        <display:column property="event" title="رویداد"  headerClass="art-table-content-header" class="art-table-content-ltr"/>     
                                        <display:column property="GDate" title="تاریخ میلادی" sortable="true" headerClass="sortable art-table-content-header" class="art-table-content"/> 
                                        <display:column property="PDate" title="تاریخ شمسی" sortable="true" headerClass="sortable art-table-content-header" class="art-table-content"/> 
                                        <display:column property="time" title="ساعت" sortable="true" headerClass="sortable art-table-content-header" class="art-table-content"/>  
                                    </display:table>
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

