package model.bl;

import java.util.List;
import model.to.UserRoleTo;
import model.to.UsersTo;
import model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsersBl {

    public Session session;
    public Transaction transaction;

    public List<UsersTo> GetAllUsers() {
        try {
            UserPageBl upbl = new UserPageBl();

            session = HibernateUtil.getSession();
            List<UsersTo> UsersTos = session.createQuery("from UsersTo order by username").list();
            session.close();
            for (UsersTo tu : UsersTos) {
                tu.setUserRoleTos(GetUserRoles(tu.getUsername()));
                tu.setLstPages(upbl.GetPageNamesByUserId(tu.getId()));
            }
            return UsersTos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserRoleTo> GetUserRoles(String Username) {
        try {
            session = HibernateUtil.getSession();
            List<UserRoleTo> UsersTos = session.createQuery("from UserRoleTo where Appname=:app and Username=:user").setParameter("user", Username).setParameter("app", "app-console").list();
            session.close();
            return UsersTos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersTo GetUserByUsername(String Username) {
        try {
            UserPageBl upbl = new UserPageBl();
            session = HibernateUtil.getSession();
            UsersTo usersTo = (UsersTo) session.createQuery("from UsersTo where Username=:user").setParameter("user", Username).list().get(0);
            session.close();
            List<UserRoleTo> userRoleTo = GetUserRoles(usersTo.getUsername());
            usersTo.setUserRoleTos(userRoleTo);
            usersTo.setLstPages(upbl.GetPageNamesByUserId(usersTo.getId()));
            return usersTo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersTo GetUserById(String strId) {
        try {
            UserPageBl upbl = new UserPageBl();
            long lngId = Long.parseLong(strId);
            session = HibernateUtil.getSession();
            UsersTo usersTo = (UsersTo) session.createQuery("from UsersTo where id=:id").setParameter("id", lngId).list().get(0);
            session.close();
            List<UserRoleTo> userRoleTo = GetUserRoles(usersTo.getUsername());
            usersTo.setUserRoleTos(userRoleTo);
            usersTo.setLstPages(upbl.GetPageNamesByUserId(usersTo.getId()));
            return usersTo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean DelUserById(String strId) {
        try {
            long lngId = Long.parseLong(strId);
            UsersTo usersTo = GetUserById(strId);
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.delete(usersTo);
            transaction.commit();
            session.close();
            DeleteUserRoles(usersTo.getUsername());
            UserPageBl upbl = new UserPageBl();
            upbl.DeleteUserPagesByUserId(lngId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean InsertOrUpdateUser(UsersTo userTo) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(userTo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateUserRoles(UserRoleTo userRoleTo) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(userRoleTo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteUserRoles(String userName) {
        try {
            List<UserRoleTo> urtos = GetUserRoles(userName);
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            for (UserRoleTo urto : urtos) {
                session.delete(urto);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFilter(String userName, String pageName) {
        session = HibernateUtil.getSession();
        UsersTo usersTo = (UsersTo) session.createQuery("from UsersTo where Username=:user").setParameter("user", userName).list().get(0);

        PagesBl pbi = new PagesBl();
        long lngPageID = pbi.GetPageIdByPageName(pageName);

        int intCount = session.createQuery("from UserPageTo where Userid=:uid and Pageid=:pid").setParameter("uid", usersTo.getId()).setParameter("pid", lngPageID).list().size();
        session.close();

        return (intCount == 0);
    }
}
