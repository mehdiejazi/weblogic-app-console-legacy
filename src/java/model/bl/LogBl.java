package model.bl;

import java.util.ArrayList;
import java.util.List;
import model.to.LogTo;
import model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LogBl {

    public Session session;
    public Transaction transaction;

    public boolean InsertUserPage(LogTo logTo) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(logTo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LogTo> GetLogesByUserID(long userId) {
        UsersBl ubl = new UsersBl();

        String strUserName = "~|~";
        try {
            strUserName = ubl.GetUserById(String.valueOf(userId)).getUsername();
        } catch (Exception ex) {
        }

        List<LogTo> LogTos = new ArrayList<LogTo>();

        try {
            session = HibernateUtil.getSession();
            LogTos = session.createQuery("from LogTo where username = :uname order by Pdate,Time").setParameter("uname", strUserName).list();
            session.close();

            return LogTos;
        } catch (Exception e) {
            e.printStackTrace();
            return LogTos;
        }
    }

    public LogTo GetLastLogByUserName(String username) {
        LogTo retLogTo = new LogTo();
        ArrayList<LogTo> retLogTos = new ArrayList<LogTo>();
       try {
            session = HibernateUtil.getSession();
            retLogTos = (ArrayList<LogTo>) session.createQuery("from LogTo where username = :uname order by LogID").setParameter("uname", username).list();
            retLogTo = retLogTos.get(retLogTos.size()-1);
            session.close();

            return retLogTo;
        } catch (Exception e) {
            e.printStackTrace();
            return retLogTo;
        }

    }
}
