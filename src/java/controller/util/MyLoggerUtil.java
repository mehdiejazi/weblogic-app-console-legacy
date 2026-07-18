package controller.util;

import javax.servlet.http.HttpServletRequest;
import model.bl.LogBl;
import model.to.LogTo;

public class MyLoggerUtil {

    public void doLog(HttpServletRequest req, String event) {
        LogTo logTo = new LogTo();
        String strUser = req.getRemoteUser();
        String strIP = req.getRemoteHost();
        try {

            strUser = req.getRemoteUser();
            strIP = req.getRemoteHost();
        } catch (Exception ex) {
        }
        logTo.setIP(strIP);
        logTo.setUsername(strUser);
        logTo.setEvent(event);

        logTo.setGDate(MyDateUtil.getDate());
        logTo.setPDate(MyDateUtil.getPersianDate());
        logTo.setTime(MyDateUtil.getTime());

        LogBl lbl = new LogBl();
        lbl.InsertUserPage(logTo);
    }

    public void doLog_(String event) {
        LogTo logTo = new LogTo();
        String strUser = "sample-user";
        String strIP = "localhost";

        logTo.setIP(strIP);
        logTo.setUsername(strUser);
        logTo.setEvent(event);

        logTo.setGDate(MyDateUtil.getDate());
        logTo.setPDate(MyDateUtil.getPersianDate());
        logTo.setTime(MyDateUtil.getTime());

        LogBl lbl = new LogBl();
        lbl.InsertUserPage(logTo);
    }
}
