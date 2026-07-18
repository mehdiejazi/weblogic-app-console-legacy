package controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateCaptchaUtil {

    public boolean ValidateCaptcha(HttpServletRequest req, HttpServletResponse res) {
        String strCaptcha = "asduawty783qw3904";
        try {
            if (req.getParameter("txtCaptcha") != null) {
                strCaptcha = req.getParameter("txtCaptcha");
            }
        } catch (Exception ex) {
        }
        try {
            if (strCaptcha.toLowerCase().equals(req.getSession().getAttribute("captcha").toString().toLowerCase())) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();;
        }
        return false;
    }
    
    public boolean ValidateCaptcha(String strCapCode, HttpServletRequest req, HttpServletResponse res) {
        String strCaptcha = strCapCode;
        try {
            if (strCaptcha.toLowerCase().equals(req.getSession().getAttribute("captcha").toString().toLowerCase())) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();;
        }
        return false;
    }
}
