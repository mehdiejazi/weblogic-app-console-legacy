/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class MyDateUtil {

    public static String getDirectoryName() {
        Calendar cl = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        return sdf.format(cl.getTime());
    }

    public static String getPersianDirectoryName() {
        String strReturn;

        Locale loc = new Locale("en_US");
        PersianCalendarUtil sc = new PersianCalendarUtil();

        Calendar cl = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        strReturn = String.valueOf(sc.getYear()) + String.format(loc, "%02d", sc.getMonth()) + String.format(loc, "%02d", sc.getDate());
        strReturn = strReturn.concat("-" + sdf.format(cl.getTime()));

        return strReturn;
    }

    public static String getPersianDate() {
        String strReturn;

        Locale loc = new Locale("en_US");
        PersianCalendarUtil pcu = new PersianCalendarUtil();

        Calendar cl = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

        strReturn = String.valueOf(pcu.getYear()) + "/" + String.format(loc, "%02d", pcu.getMonth()) + "/" + String.format(loc, "%02d", pcu.getDate());

        return strReturn;
    }

    public static String getDate() {
        Calendar cl = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(cl.getTime());
    }

    public static String getTime() {
        String strReturn = "";

        Calendar cl = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        strReturn = sdf.format(cl.getTime());
        return strReturn;
    }

    public static GregorianCalendar AddMinute(String _gdate, String _time, int minutes) {
        String[] date = _gdate.split("/");
        String[] time = _time.split(":");

        GregorianCalendar cl = new GregorianCalendar();

        int intY = Integer.parseInt(date[0]);
        int intM = Integer.parseInt(date[1]) - 1;
        int intD = Integer.parseInt(date[2]);

        int intHH = Integer.parseInt(time[0]);
        int intMM = Integer.parseInt(time[1]);
        int intSS = Integer.parseInt(time[2]);

        cl.set(intY, intM, intD, intHH, intMM, intSS);
        cl.add(GregorianCalendar.MINUTE, minutes);

        return cl;
    }

    public static String GetFarsiDayOfWeek(int day) {
        if (day == 1) {
            return "یک شنبه";
        } else if (day == 2) {
            return "دو شنبه";
        } else if (day == 3) {
            return "سه شنبه";
        } else if (day == 4) {
            return "چهار شنبه";
        } else if (day == 5) {
            return "پنج شنبه";
        } else if (day == 6) {
            return "جمعه";
        } else if (day == 7) {
            return "شنبه";
        }
        return "";
    }

    public static ArrayList<String> GetLast7Days() {
        ArrayList<String> lstReturned = new ArrayList<String>();

        for (int i = 0; i < 7; i++) {
            GregorianCalendar cl = new GregorianCalendar();
            cl.add(GregorianCalendar.DAY_OF_YEAR, i * -1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            lstReturned.add(sdf.format(cl.getTime()));
        }

        return lstReturned;
    }

    public static ArrayList<String> GetLast7FarsiDays() {
        ArrayList<String> lstReturned = new ArrayList<String>();

        for (int i = 0; i < 7; i++) {
            GregorianCalendar cl = new GregorianCalendar();
            cl.add(GregorianCalendar.DAY_OF_YEAR, i * -1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date dt = new Date(sdf.format(cl.getTime()));
            PersianCalendarUtil pcu = new PersianCalendarUtil(dt);
            Locale loc = new Locale("en_US");

            lstReturned.add(GetFarsiDayOfWeek(cl.get(GregorianCalendar.DAY_OF_WEEK)) + " " + String.valueOf(pcu.getYear()) + "/" + String.format(loc, "%02d", pcu.getMonth()) + "/" + String.format(loc, "%02d", pcu.getDate()));
        }

        return lstReturned;
    }
}
