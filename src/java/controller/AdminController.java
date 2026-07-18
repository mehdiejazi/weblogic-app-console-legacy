package controller;

import controller.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bl.LogBl;
import model.bl.PagesBl;
import model.bl.UserPageBl;
import model.bl.UsersBl;
import model.to.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    Logger logger = Logger.getLogger(AdminController.class);

    @RequestMapping("/admin/setparams.do")
    public ModelAndView setparams(HttpServletRequest req, HttpServletResponse res) throws Exception {
        
        ValidateCaptchaUtil vcu = new ValidateCaptchaUtil();
        if (vcu.ValidateCaptcha(req, res) == false) {
            res.sendRedirect("setparams.jsp?msg=errcaptcha");
            return null;
        }
        WebParamUtil wpu = new WebParamUtil();
        wpu.SetFileName(req.getSession().getServletContext().getRealPath("/") + "/WEB-INF/web-param.xml");

        ModelAndView mav = new ModelAndView("admin/setparams");
        EncryptDESUtil des = new EncryptDESUtil();
        Enumeration<String> enmParamNames0 = req.getParameterNames();
        Enumeration<String> enmParamNames1 = req.getParameterNames();
        while (enmParamNames0.hasMoreElements()) {
            String strParamName = enmParamNames0.nextElement();
            if (req.getParameter(strParamName).toString().equals("")) {
                res.sendRedirect("./setparams.jsp?msg=0");
                return null;
            }
        }

        FileUtil fu = new FileUtil();

        ArrayList<String> arr = fu.ReadFile(req.getSession().getServletContext().getRealPath("/") + "WEB-INF/web.xml");

        while (enmParamNames1.hasMoreElements()) {
            String strParamName = enmParamNames1.nextElement();
            for (int i = 0; i < arr.size(); i++) {
                if (strParamName.equals("appName")) {
                    wpu.SetappName(req.getParameter(strParamName));
                }
                if (strParamName.equals("wlAdminURL")) {
                    wpu.SetwlAdminURL(req.getParameter(strParamName));
                }
                if (strParamName.equals("sftpDeployHostIP")) {
                    wpu.SetsftpDeployHostIP(req.getParameter(strParamName));
                }
                if (strParamName.equals("sftpDeployWorkingDir")) {
                    wpu.SetsftpDeployWorkingDir(req.getParameter(strParamName));
                }
                if (strParamName.equals("sftpBackupHostIP")) {
                    wpu.SetsftpBackupHostIP(req.getParameter(strParamName));
                }
                if (strParamName.equals("sftpBackupWorkingDir")) {
                    wpu.SetsftpBackupWorkingDir(req.getParameter(strParamName));
                }
            }
        }

        fu.WriteFile(arr, req.getSession().getServletContext().getRealPath("/") + "WEB-INF/web.xml");

        res.sendRedirect("./setparams.jsp?msg=1");
        return null;
    }

    @RequestMapping("/admin/users.do")
    public ModelAndView adduser(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PropertyConfigurator.configure(req.getSession().getServletContext().getRealPath("/") + "/WEB-INF/log4j.properties");
        logger.setLevel(Level.DEBUG);
        MyLoggerUtil mlu = new MyLoggerUtil();

        ModelAndView mav = new ModelAndView("admin/users");
        UsersBl ubl = new UsersBl();
        List<UsersTo> UsersTos = ubl.GetAllUsers();
        PagesBl pbl = new PagesBl();
        List<PagesTo> PagesTos = pbl.GetAllPages();

        req.setAttribute("UsersTos", UsersTos);
        req.setAttribute("PagesTos", PagesTos);

        if (UsersTos == null) {
            logger.error("Failed to select all users.");
            mlu.doLog(req, "Failed to select all users.");
        }
        return mav;
    }

    @RequestMapping("/admin/addorupdateusers.do")
    public ModelAndView addorupdateusers(HttpServletRequest req, HttpServletResponse res) throws IOException, Exception {
        req.setCharacterEncoding("UTF-8");
        PropertyConfigurator.configure(req.getSession().getServletContext().getRealPath("/") + "/WEB-INF/log4j.properties");
        logger.setLevel(Level.DEBUG);
        MyLoggerUtil mlu = new MyLoggerUtil();

        UsersBl ubl = new UsersBl();
        String strId = req.getParameter("txtId");
        String strUsername = req.getParameter("txtUsername");
        String strPassword = req.getParameter("txtPassword");
        String strFullname = req.getParameter("txtFullname");

        //<server side validation>
        if (strUsername.equals("") || strPassword.equals("")) {
            String strURL = "./users.do?msg=fillitems";
            if (strUsername.equals("")) {
                strURL = strURL + "-user";
                logger.warn("Username is empty.");
            }
            if (strPassword.equals("")) {
                strURL = strURL + "-pass";
                logger.warn("Password is empty.");
            }
            res.sendRedirect(strURL);
            return null;
        }
        //</server side validation>
        boolean blnAdmin = (req.getParameter("chkAdmin") != null);
        boolean blnEmployee = (req.getParameter("chkEmployee") != null);

        Enumeration<String> lstParams = req.getParameterNames();

        EncryptMD5Util cu = new EncryptMD5Util();
        strPassword = cu.getMD5(strPassword);

        UsersTo usersTo = new UsersTo();
        if (strId.equals("-1000") == false) {
            usersTo.setId(Long.parseLong(strId));
        }
        usersTo.setUsername(strUsername);
        usersTo.setPassword(strPassword);
        usersTo.setFullname(strFullname);

        if (ubl.InsertOrUpdateUser(usersTo) == false) {
            logger.error("Failed to insert or update user.");
            mlu.doLog(req, "Failed to insert or update user.");
            res.sendRedirect("./users.do?msg=err");
            return null;
        }
        ubl.DeleteUserRoles(strUsername);
        if (blnAdmin) {
            UserRoleTo userRoleTo = new UserRoleTo();
            userRoleTo.setUsername(strUsername);
            userRoleTo.setRolename("admin");
            ubl.UpdateUserRoles(userRoleTo);
        }
        if (blnEmployee) {
            UserRoleTo userRoleTo = new UserRoleTo();
            userRoleTo.setUsername(strUsername);
            userRoleTo.setRolename("employee");
            ubl.UpdateUserRoles(userRoleTo);
        }

        if (strId.equals("-1000")) {
            logger.info("User successfully inserted.");
            mlu.doLog(req, "User successfully inserted.");
        } else {
            logger.info("User successfully updated.");
            mlu.doLog(req, "User successfully updated.");
        }
        res.sendRedirect("./users.do?msg=success");

        List<UserPageTo> lstUserPageTos = new ArrayList<UserPageTo>();

        while (lstParams.hasMoreElements()) {
            String element = lstParams.nextElement();
            if (element.contains("chkPage")) {
                UserPageTo upto = new UserPageTo();
                upto.setPageid(Long.parseLong(element.replaceAll("chkPage", "")));
                upto.setUserid(usersTo.getId());
                lstUserPageTos.add(upto);
            }
        }
        UserPageBl upbl = new UserPageBl();
        if (strId.equals("-1000") == false) {
            upbl.DeleteUserPagesByUserId(Long.parseLong(strId));
            
        }
        System.out.println("strId : " + strId);
        upbl.InsertUserPages(lstUserPageTos);

        return null;
    }

    @RequestMapping("/admin/edituser.do")
    public ModelAndView edituser(HttpServletRequest req, HttpServletResponse res) throws IOException, Exception {
        ModelAndView mav = new ModelAndView("admin/users");

        UsersBl ubl = new UsersBl();
        String strId = req.getParameter("id");
        UsersTo userTo = ubl.GetUserById(strId);

        UserPageBl upbl = new UserPageBl();
        List<String> UserPageTos = upbl.GetPageNamesByUserId(Long.parseLong(strId));

        PagesBl pbl = new PagesBl();
        List<PagesTo> PagesTos = pbl.GetAllPages();

        List<UsersTo> usersTos = ubl.GetAllUsers();
        req.setAttribute("UsersTos", usersTos);
        req.setAttribute("PagesTos", PagesTos);
        req.setAttribute("UsersTo", userTo);
        req.setAttribute("UserPageTos", UserPageTos);

        return mav;
    }

    @RequestMapping("/admin/deluser.do")
    public ModelAndView deluser(HttpServletRequest req, HttpServletResponse res) throws IOException, Exception {
        PropertyConfigurator.configure(req.getSession().getServletContext().getRealPath("/") + "/WEB-INF/log4j.properties");
        logger.setLevel(Level.DEBUG);
        MyLoggerUtil mlu = new MyLoggerUtil();

        ModelAndView mav = new ModelAndView("admin/users");
        UsersBl ubl = new UsersBl();
        String strId = req.getParameter("id");
        if (ubl.DelUserById(strId)) {
            logger.info("User successfully deleted. (id=" + strId + ")");
            mlu.doLog(req, "User successfully deleted. (id=" + strId + ")");

            res.sendRedirect("./users.do?msg=delete_success");
        } else {
            logger.info("Failed to delete user. (id=" + strId + ")");
            mlu.doLog(req, "Failed to delete user. (id=" + strId + ")");
            res.sendRedirect("./users.do?msg=delete_failed");
        }
        return null;
    }

    @RequestMapping("/admin/showlog.do")
    public ModelAndView showlog(HttpServletRequest req, HttpServletResponse res) throws IOException, Exception {
        String strId = req.getParameter("id");

        if (strId.equals("")) {
            strId = "-9999";
        }

        ModelAndView mav = new ModelAndView("admin/showlog");

        LogBl lbl = new LogBl();
        ArrayList<LogTo> LogTos = (ArrayList<LogTo>) lbl.GetLogesByUserID(Long.parseLong(strId));

        req.setAttribute("LogTos", LogTos);

        return mav;
    }
    
}
