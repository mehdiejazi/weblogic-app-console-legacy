package controller;

import controller.util.MyLoggerUtil;
import controller.util.ValidateCaptchaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Auth extends HttpServlet {

    Logger logger = Logger.getLogger(Auth.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        MyLoggerUtil mlu = new MyLoggerUtil();
        PropertyConfigurator.configure(req.getSession().getServletContext().getRealPath("/") + "/WEB-INF/log4j.properties");
        logger.setLevel(Level.DEBUG);

        ValidateCaptchaUtil vcu = new ValidateCaptchaUtil();
        if (vcu.ValidateCaptcha(req, res) == false) {
            res.sendRedirect("login.jsp?login=errcaptcha");
            return;
        }

        String strUser = "";
        try {
            strUser = req.getParameter("txtUsername");
            String strPassword = req.getParameter("txtPassword");
            if (req.getRemoteUser() == null) {
                req.login(strUser, strPassword);
            }
            GenericPrincipal gp = (GenericPrincipal) req.getUserPrincipal();
            String strFirstRole = gp.getRoles()[0];

            logger.info("User authenticated. (Username:" + strUser + ")");
            mlu.doLog(req, "User authenticated. (Username:" + strUser + ")");

            res.sendRedirect(strFirstRole + "/index.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();

            logger.warn("User authentication failed. (Username:" + strUser + ")");
            mlu.doLog(req, "User authentication failed. (Username:" + strUser + ")");

            res.sendRedirect("./loginerr.jsp");
        }
    }
}
