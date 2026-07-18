/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bl;

import controller.util.MyDateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.util.GetConUtil;

/**
 *
 * @author ejazi
 */
public class ActivityMetricsBl {

    public String GetLast7DaysCount() throws ClassNotFoundException, SQLException {
        String strSQL = "";

        ArrayList<String> arrdate = MyDateUtil.GetLast7Days();

        for (int i = 0; i < arrdate.size(); i++) {
            strSQL = strSQL + "(select count(ID) as cont from APP_ACTIVITY_LOG where SubStr(message,0,5) = 'LOGIN' and SubStr(timedate,0,10)='" + arrdate.get(i) + "')\n";
            if (i != arrdate.size() - 1) {
                strSQL = strSQL + " UNION ALL \n";
            }
        }

        String strReturned = "";

        ResultSet rs = null;
        Connection con = GetConUtil.getCon();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(strSQL);
            rs = ps.executeQuery();

            while ((rs != null) && (rs.next())) {
                strReturned = rs.getString("cont") + "|" + strReturned;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.close();
        }
        return strReturned;
    }
}
