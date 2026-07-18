package model.bl;

import java.util.ArrayList;
import java.util.List;
import model.to.UserBackupTo;
import model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserBackupBl {

    public Session session;
    public Transaction transaction;

    public List<UserBackupTo> GetUserBackups() {

        List<UserBackupTo> UserBackupTos = new ArrayList<UserBackupTo>();

        try {
        session = HibernateUtil.getSession();
        UserBackupTos = session.createQuery("from UserBackupTo order by Pdate,Time").list();
        session.close();

        return UserBackupTos;
        } catch (Exception e) {
            e.printStackTrace();
            return UserBackupTos;
        }
    }
    
    public boolean InsertUserBackups(UserBackupTo ubto)
    {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(ubto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
