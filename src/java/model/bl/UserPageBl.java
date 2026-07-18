/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bl;

import java.util.ArrayList;
import java.util.List;
import model.to.UserPageTo;
import model.to.UserRoleTo;
import model.to.UsersTo;
import model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ejazi
 */
public class UserPageBl {

    public Session session;
    public Transaction transaction;

    public boolean InsertUserPage(UserPageTo userPageTo) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(userPageTo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean InsertUserPages(List<UserPageTo> userPageTos) {
        try {
            for (UserPageTo upto : userPageTos) {
                InsertUserPage(upto);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<String> GetPageNamesByUserId(long lngUserId) {
        PagesBl pbl = new PagesBl();
        try {
            session = HibernateUtil.getSession();
            List<UserPageTo> userPagesTos = session.createQuery("from UserPageTo where userid = :uid").setParameter("uid", lngUserId).list();
            session.close();
            ArrayList<String> lstReturned = new ArrayList<String>();
            for (UserPageTo up : userPagesTos) {

                lstReturned.add(pbl.GetPagesToByPageName(up.getPageid()).getPagename());
            }
            return lstReturned;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
        ///return null;
    }

    public List<String> GetPageNamesByUserName(String strUserName) {
        UsersBl ubl = new UsersBl();
        UsersTo usersTo = ubl.GetUserByUsername(strUserName);

        
        return usersTo.getLstPages();
    }

    public List<UserPageTo> GetUserPagesByUserId(long lngUserId) {
        PagesBl pbl = new PagesBl();
        try {
            session = HibernateUtil.getSession();
            List<UserPageTo> userPagesTos = session.createQuery("from UserPageTo where userid = :uid").setParameter("uid", lngUserId).list();
            session.close();
            return userPagesTos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        ///return null;
    }

    public boolean DeleteUserPagesByUserId(long userId) {

        UsersBl ubl = new UsersBl();
        try {
            List<UserPageTo> lstuserpages = GetUserPagesByUserId(userId);
            //List<UserRoleTo> urtos = GetUserRoles(userName);
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            for (UserPageTo upto : lstuserpages) {
                session.delete(upto);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
