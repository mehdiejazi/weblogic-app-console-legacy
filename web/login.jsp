<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ورود به سامانه مدیریت استقرار و پایش برنامه</title>
        <link href="theme/login.css" rel="Stylesheet" />
        <script type="text/javascript" src="./theme/js/jquery.min.js"></script>
        <link rel="Shortcut Icon" href="./theme/images/Logo.ico"/>
        <script type="text/javascript">
            function reloadCaptcha() {
                $('#captcha-div').html('');
                $.ajax({
                    url: 'captchajx.jsp',
                    type: 'GET',
                    success: function(response) {
                        $('#captcha-div').html(response);
                    }
                });
            }
        </script>
    </head>
    <body>
        <div class="main">
            <img src="theme/images/user.png" alt="user" class="loginimage" />
            <div class="login">
                <div class="logintitle">
                    سامانه مدیریت استقرار و پایش برنامه
                </div>
                <form method="post" action="<%=request.getServletContext().getContextPath()%>/auth.login">
                    <table id="logintable">
                        <tr>
                            <td>نام کاربری:</td>
                            <td><input name="txtUsername" type="text" class="text" /></td>
                        </tr>
                        <tr>
                            <td>رمز عبور:</td>
                            <td><input name="txtPassword" type="password" class="text" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr /></td>
                        </tr>
                    </table>
                    <table id="captchatable">
                        <tr>
                            <td colspan="2">تصویر امنیتی</td>
                        </tr>
                        <tr>
                            <td>
                                <div id="captcha-div">
                                    <img class="art-captcha" src="captcha-image.jpg" alt="captcha" />
                                </div>
                            </td>
                            <td>
                                <input type="button" onclick="reloadCaptcha()" class="captcharefresh"/>
                                <input name="txtCaptcha" type="text" class="textcaptcha" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="ورود" class="loginbutton" />
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="Error">
                    <%
                        if (request.getParameter("login") != null) {
                            if (request.getParameter("login").equals("err")) {
                                out.print("<div class=\"art-error\">");
                                out.print("نام کاربری یا رمز عبور را صحیح وارد کنید");
                                out.print("</div>");
                            }
                            if (request.getParameter("login").equals("errcaptcha")) {
                                out.print("<div class=\"art-error\">");
                                out.print("تصویر امنیتی را صحیح وارد کنید");
                                out.print("</div>");
                            }
                            if (request.getParameter("login").equals("SesstionTimeout")) {
                                out.print("<div class=\"art-info\">");
                                out.print("نشست شما منقضی شده است");
                                out.print("</div>");
                            }
                        }
                    %>
                </div>
            </div>
            <div class="notes">
                <img src="theme/images/alert.png" />
                نکات امنیتی
                <ul>
                    <li>از رمز عبور خود به خوبی محافظت کنید.</li>
                    <li>رمز عبور را در بازه های زمانی مختلف تغییر دهید.</li>
                    <li>برای رمز عبور از ترکیب امن و غیرقابل حدس استفاده کنید.</li>
                    <li>پس از اتمام کار از سامانه خارج شوید.</li>
                </ul>
            </div>
        </div>
    </body>
</html>
