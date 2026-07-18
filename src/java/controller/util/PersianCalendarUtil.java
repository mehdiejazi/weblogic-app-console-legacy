package controller.util;

import java.util.Date;
import java.util.Locale;

public class PersianCalendarUtil {

    public String strWeekDay = "";
    public String strMonth = "";
    private int day;
    private int month;
    private int year;

    public PersianCalendarUtil() {
        Date MiladiDate = new Date();
        calcPersianCalendar(MiladiDate);
    }

    public PersianCalendarUtil(Date MiladiDate) {
        calcPersianCalendar(MiladiDate);
    }

    private void calcPersianCalendar(Date MiladiDate) {
        int ld;

        int miladiYear = MiladiDate.getYear() + 1900;
        int miladiMonth = MiladiDate.getMonth() + 1;
        int miladiDate = MiladiDate.getDate();
        int WeekDay = MiladiDate.getDay();

        int[] buf1 = new int[12];
        int[] buf2 = new int[12];

        buf1[0] = 0;
        buf1[1] = 31;
        buf1[2] = 59;
        buf1[3] = 90;
        buf1[4] = 120;
        buf1[5] = 151;
        buf1[6] = 181;
        buf1[7] = 212;
        buf1[8] = 243;
        buf1[9] = 273;
        buf1[10] = 304;
        buf1[11] = 334;

        buf2[0] = 0;
        buf2[1] = 31;
        buf2[2] = 60;
        buf2[3] = 91;
        buf2[4] = 121;
        buf2[5] = 152;
        buf2[6] = 182;
        buf2[7] = 213;
        buf2[8] = 244;
        buf2[9] = 274;
        buf2[10] = 305;
        buf2[11] = 335;

        if ((miladiYear % 4) != 0) {
            setDay(buf1[miladiMonth - 1] + miladiDate);

            if (getDate() > 79) {
                setDay(getDate() - 79);
                if (getDate() <= 186) {
                    switch (getDate() % 31) {
                        case 0:
                            setMonth(getDate() / 31);
                            setDay(31);
                            break;
                        default:
                            setMonth((getDate() / 31) + 1);
                            setDay(getDate() % 31);
                            break;
                    }
                    setYear(miladiYear - 621);
                } else {
                    setDay(getDate() - 186);

                    switch (getDate() % 30) {
                        case 0:
                            setMonth((getDate() / 30) + 6);
                            setDay(30);
                            break;
                        default:
                            setMonth((getDate() / 30) + 7);
                            setDay(getDate() % 30);
                            break;
                    }
                    setYear(miladiYear - 621);
                }
            } else {
                if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                    ld = 11;
                } else {
                    ld = 10;
                }
                setDay(getDate() + ld);

                switch (getDate() % 30) {
                    case 0:
                        setMonth((getDate() / 30) + 9);
                        setDay(30);
                        break;
                    default:
                        setMonth((getDate() / 30) + 10);
                        setDay(getDate() % 30);
                        break;
                }
                setYear(miladiYear - 622);
            }
        } else {
            setDay(buf2[miladiMonth - 1] + miladiDate);

            if (miladiYear >= 1996) {
                ld = 79;
            } else {
                ld = 80;
            }
            if (getDate() > ld) {
                setDay(getDate() - ld);

                if (getDate() <= 186) {
                    switch (getDate() % 31) {
                        case 0:
                            setMonth(getDate() / 31);
                            setDay(31);
                            break;
                        default:
                            setMonth((getDate() / 31) + 1);
                            setDay(getDate() % 31);
                            break;
                    }
                    setYear(miladiYear - 621);
                } else {
                    setDay(getDate() - 186);

                    switch (getDate() % 30) {
                        case 0:
                            setMonth((getDate() / 30) + 6);
                            setDay(30);
                            break;
                        default:
                            setMonth((getDate() / 30) + 7);
                            setDay(getDate() % 30);
                            break;
                    }
                    setYear(miladiYear - 621);
                }
            } else {
                setDay(getDate() + 10);

                switch (getDate() % 30) {
                    case 0:
                        setMonth((getDate() / 30) + 9);
                        setDay(30);
                        break;
                    default:
                        setMonth((getDate() / 30) + 10);
                        setDay(getDate() % 30);
                        break;
                }
                setYear(miladiYear - 622);
            }

        }

        switch (getMonth()) {
            case 1:
                strMonth = "فروردين";
                break;
            case 2:
                strMonth = "ارديبهشت";
                break;
            case 3:
                strMonth = "خرداد";
                break;
            case 4:
                strMonth = "تير";
                break;
            case 5:
                strMonth = "مرداد";
                break;
            case 6:
                strMonth = "شهريور";
                break;
            case 7:
                strMonth = "مهر";
                break;
            case 8:
                strMonth = "آبان";
                break;
            case 9:
                strMonth = "آذر";
                break;
            case 10:
                strMonth = "دي";
                break;
            case 11:
                strMonth = "بهمن";
                break;
            case 12:
                strMonth = "اسفند";
                break;
        }

        switch (WeekDay) {

            case 0:
                strWeekDay = "يکشنبه";
                break;
            case 1:
                strWeekDay = "دوشنبه";
                break;
            case 2:
                strWeekDay = "سه شنبه";
                break;
            case 3:
                strWeekDay = "چهارشنبه";
                break;
            case 4:
                strWeekDay = "پنج شنبه";
                break;
            case 5:
                strWeekDay = "جمعه";
                break;
            case 6:
                strWeekDay = "شنبه";
                break;
        }

    }

    public static String getCurrentShamsidate() {
        Locale loc = new Locale("en_US");
        PersianCalendarUtil sc = new PersianCalendarUtil();
        return String.valueOf(sc.getYear()) + "/" + String.format(loc, "%02d", sc.getMonth()) + "/" + String.format(loc, "%02d", sc.getDate());
    }

    public int getDate() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    private void setDay(int day) {
        this.day = day;
    }

    private void setMonth(int month) {
        this.month = month;
    }

    private void setYear(int year) {
        this.year = year;
    }
}