package controller.filter;

import controller.util.MyDateUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bl.LogBl;
import model.bl.UsersBl;
import model.to.LogTo;

/**
 *
 * @author ejazi
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String strCurrentPath = req.getRequestURI().replaceAll(req.getContextPath() + "/", "");
        String strCurrentUser = req.getRemoteUser();
        //String strCurrentPackage = strCurrentPath.split("/")[0];

        if (strCurrentPath.contains("logout")
                || strCurrentPath.contains("login")) {
            chain.doFilter(request, response);
            return;
        }

        if (strCurrentUser != null) {
            LogBl lbl = new LogBl();
            LogTo lto = lbl.GetLastLogByUserName(strCurrentUser);

            String strRealSessionTimeOut = req.getServletContext().getInitParameter("realSessionTimeout");

            GregorianCalendar clLast = MyDateUtil.AddMinute(lto.getGDate(), lto.getTime(), Integer.parseInt(strRealSessionTimeOut));
            GregorianCalendar clNow = new GregorianCalendar();
            
            if (req.getRemoteUser().contains("monitor")==false) {
                if (clLast.compareTo(clNow) == -1) {
                    res.sendRedirect(request.getServletContext().getContextPath() + "/logout.jsp?login=SesstionTimeout");
                    return;
                }
            }

            if (strCurrentPath.equals("admin/users.do")) {
                strCurrentPath = "admin/users.jsp";
            } else if (strCurrentPath.equals("employee/userbackup.do")) {
                strCurrentPath = "employee/userbackup.jsp";
            }
        }
        //</TODO: for pages that with *.do!>

        ArrayList<String> Pages;
        try {
            Pages = (ArrayList<String>) req.getSession().getAttribute("Pages");
            if (strCurrentPath.contains(".jsp")) {
                Pages.add(req.getRequestURI());
            }
        } catch (Exception ex) {
            Pages = new ArrayList<String>();
        }

        req.getSession().setAttribute("Pages", Pages);

        if ((strCurrentPath.contains(".jsp") == false)
                || (strCurrentPath.contains("admin") == false)
                && (strCurrentPath.contains("employee") == false)
                || (strCurrentPath.contains("index") == true)) {
            chain.doFilter(request, response);
            return;
        }

        model.bl.UsersBl ubl = new UsersBl();
        if (ubl.isFilter(strCurrentUser, strCurrentPath)) {
            res.sendRedirect("index.jsp?msg=1");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
