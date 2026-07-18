package controller.util;

import com.jcraft.jsch.JSchException;
import java.io.IOException;
import java.util.ArrayList;

public class test {

    public static void main(String[] args) throws JSchException, IOException, Exception {
        String strSQL = "";

        ArrayList<String> arrdate = MyDateUtil.GetLast7Days();

        System.out.println(arrdate.size());
        for (int i = 0; i < arrdate.size(); i++) {
            strSQL = strSQL + "(select count(ID) as cont from APP_ACTIVITY_LOG where SubStr(message,0,5) = 'LOGIN' and SubStr(timedate,0,10)='" + arrdate.get(i) + "')\n";
            if (i != arrdate.size() - 1) {
                strSQL = strSQL + " UNION ALL \n";
            }
        }

        System.out.println(strSQL);
    }
}
