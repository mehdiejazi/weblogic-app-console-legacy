<%@page import="controller.util.WebParamUtil"%>
<%@page  pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en">
    <head>
        <title>مدیریت وب سرور</title>
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
            
            function SubmitOnClick()
            {
                $("#waitforit").html('<img src="../theme/images/loading.gif" alt="Loading" />');
                var actionvalue="";
                
                if ($("#rdoStart").is(":checked"))
                {
                    actionvalue="start";
                }
                else
                {
                    actionvalue="stop";
                }                    
                var data="rdoaction="+actionvalue+
                    "&txtCaptcha="+$("#form").attr("txtCaptcha").valueOf()+
                        "&txtWlUsername="+$("#form").attr("txtWlUsername")+
                        "&txtWlPassword="+$("#form").attr("txtWlPassword")+
                        "&txtHUsername="+$("#form").attr("txtHUsername")+
                        "&txtHPassword="+$("#form").attr("txtHPassword");
                alert(data);
                $.ajax({
                    url: 'weblogicactions.do',
                    type: 'GET',
                    data:data,
                    success: function (response) {
                        $("#tblContent").html("succss");
                        $("#waitforit").html('<input type="button" value="انجام عملیات"  class="art-mybutton" onclick="SubmitOnClick()"/>');
                    }
                });

            }

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
                                    <h2 class="art-postheader">مدیریت وب سرور</h2>
                                    <div class="art-postcontent">
                                        <!-- content -->
                                        <form id="form" method="post" action="weblogicactions.do">
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
                                                            <td> <input id="txtWlUsername" name="txtWlUsername" type="text" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>رمز عبور:</td>
                                                            <td><input id="txtWlPassword" name="txtWlPassword" type="password"  class="text-ltr"/></td>
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
                                                            <td> <input id="txtHUsername" name="txtHUsername" type="text" class="text-ltr"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>رمز عبور:</td>
                                                            <td><input id="txtHPassword" name="txtHPassword" type="password" class="text-ltr"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <h4>قدم نهایی</h4>
                                                <div style="height: auto!important;">
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
                                                                    <input id="txtCaptcha" name="txtCaptcha" type="text" class="text-ltr"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <input name="rdoaction" id="rdoStart" value="start" checked="checked" type="radio" />
                                                                    <label for="rdoStart">بالا آوردن سرویس</label>
                                                                </td>
                                                                <td>
                                                                    <input name="rdoaction" id="rdoStop" value="stop" type="radio" />
                                                                    <label for="rdoStop">پایین آوردن سرویس</label>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>

                                                    <div id="waitforit">
                                                        <!--<input type="button" value="انجام عملیات"  class="art-mybutton" onclick="SubmitOnClick()"/>-->
                                                        <input type="submit" value="انجام عملیات"  class="art-mybutton" />
                                                    </div>

                                                    <div id="message">

                                                    </div>
                                                    <br />
                                                </div>
                                            </div>
                                        </form>
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
