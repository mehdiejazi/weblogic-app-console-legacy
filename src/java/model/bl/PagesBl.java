/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bl;

import java.util.List;
import model.to.PagesTo;
import model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ejazi
 */
public class PagesBl {

    public Session session;
    public Transaction transaction;

    public List<PagesTo> GetAllPages() {
        try {
            session = HibernateUtil.getSession();
            List<PagesTo> pagesTos = session.createQuery("from PagesTo order by pagename").list();
            session.close();
            return pagesTos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long GetPageIdByPageName(String Pagename) {
        try {
            session = HibernateUtil.getSession();
            PagesTo pagesTo = (PagesTo) session.createQuery("from PagesTo order where pagename=:page").setParameter("page", Pagename).list().get(0);
            session.close();
            return pagesTo.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -9999;
        }
    }

    public PagesTo GetPagesToByPageName(long Pageid) {
        try {
            session = HibernateUtil.getSession();
            PagesTo pagesTo = (PagesTo) session.createQuery("from PagesTo order where id=:id").setParameter("id", Pageid).list().get(0);
            session.close();
            return pagesTo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
