
package module.common.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @describe:  日期工具类
 * @date: 2020/1/12
 * @author: Mr Bai
 */
public class DateUtils {

    public static final String FORMAT_1 = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_2 = "yyyy-MM-dd";

    public static final String FORMAT_3 = "MM-dd HH:mm";
    public static final String FORMAT_4 = "yyyy_MM_dd_HH_mm_ss";


    /**
     * 取得当月月份(1,2,3...10,11,12)
     *
     * @return int
     * @version1.0
     */
    public static int getCurMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 取得当月的上个月(1,2,3...10,11,12),若当月是1月上月则是12月
     *
     * @return int
     * @version1.0
     */
    public static int getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据日期（yyyy-MM-dd)取得日期是该年的第几周
     *
     * @return String
     * @version1.0
     */
    public static String getWeekByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return String.valueOf(cal.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * 取得当月的上个月所在的年份
     *
     * @return int
     * @version1.0
     */
    public static int getYearByMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 取得时间所在年份（4位数字表示）
     *
     * @return String
     * @version1.0
     */

    public static String getCurrentYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    /**
     * @param date
     * @param hours
     * @param minutes
     * @param seconds
     * @param milliSeconds
     * @return java.util.Date
     */
    public static Date setHoursAndMinutes(Date date, int hours, int minutes, int seconds,
                                          int milliSeconds) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, hours);
            cal.set(Calendar.MINUTE, minutes);
            cal.set(Calendar.SECOND, seconds);
            cal.set(Calendar.MILLISECOND, milliSeconds);
            return cal.getTime();
        }
        return date;
    }


    /**
     * 当前时间的的前几个月开始时间和结束时间。 months 取值 从1到12
     *
     * @throws Exception
     */
    public static String[] getDivMonthDate(int months) throws Exception {
        try {
            String[] retStr = new String[2];
            Calendar cal = Calendar.getInstance();
            int curMonth = cal.get(Calendar.MONTH) + 1;
            int curYear = cal.get(Calendar.YEAR);
            if ((curMonth - months) <= 0) {
                curYear -= 1;
            }
            int divMonth = (curMonth - months + 12) % 12;
            if (divMonth == 0) {
                divMonth = 12;
            }
            retStr[0] = parseFullDate(curYear + "-" + divMonth + "-" + 1);
            retStr[1] = parseFullDate(curYear + "-" + divMonth + "-"
                    + getLastDay(String.valueOf(curYear), String.valueOf(divMonth)));
            return retStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取得当前年份（4位数字表示）
     *
     * @return
     */

    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 取得当前是全年的第几周
     *
     * @return int
     * @version1.0
     */
    public static int getCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置一周从星期一开始
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据年份和某周取得某周的最后一天（星期日），若该周是该年的最后一周，返回yyyy-12-31
     *
     * @param yearStr 年份 格式是yyyy
     * @param weekStr 第几周
     * @return String 格式:yyyy-MM-dd
     * @version1.0
     */
    public static String getLastDayByWeek(String yearStr, String weekStr) {
        int year = Integer.parseInt(yearStr);
        int week = Integer.parseInt(weekStr);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置一周从星期一开始
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // 假设时间是这周的星期天
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month == 1 && week > 6) {
            return parseFullDate(year + "-12-31");
        }
        return parseFullDate(year + "-" + month + "-" + day);
    }

    /**
     * 取得当月的最后一天
     */
    public static String getLastDayInCurMonth() {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 取得某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDay(String year, String month) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        cal.set(Calendar.DATE, 1);
        return String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 取得某年2月的最后一天
     */
    public static String getLastDayForFeb(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        return String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 根据年份和某周取得某周的最后一天（星期日），若该周是该年的第一周，返回yyyy-1-1
     *
     * @param yearStr 年份 格式是yyyy
     * @param weekStr 第几周
     * @return String 格式:yyyy-MM-dd
     * @version1.0
     */
    public static String getFirstDayByWeek(String yearStr, String weekStr) {
        int year = Integer.parseInt(yearStr);
        int week = Integer.parseInt(weekStr);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置一周从星期一开始
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 假设这周的星期一
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month != 1 && week == 1) {
            return parseFullDate(year + "-1-1");
        }
        return parseFullDate(year + "-" + month + "-" + day);
    }

    public static String parseFullDate(String str) {
        String[] strArr = str.split("-");
        if (strArr[1].length() == 1)
            strArr[1] = "0" + strArr[1];
        if (strArr[2].length() == 1)
            strArr[2] = "0" + strArr[2];
        return strArr[0] + "-" + strArr[1] + "-" + strArr[2];
    }

    /**
     * 根据日期（yyyy-MM-dd)取得日期是该年的第几周
     *
     * @param year  yyyy
     * @param month mm
     * @param day   dd
     * @return String
     * @version1.0
     */
    public static String getWeekByDate(String year, String month, String day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        cal.set(Calendar.DATE, Integer.parseInt(day));
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return String.valueOf(cal.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * 将日期转换成MM月dd日
     *
     * @param date yyyy-MM-dd
     * @return String
     * @version1.0
     */
    public static String toChinaDate(String date) {
        String[] dateArr = date.split("-");
        return dateArr[0] + "年" + dateArr[1] + "月" + dateArr[2] + "日";
    }

    /**
     * 将yyyy-mm-dd去掉yyyy-
     *
     * @param str
     * @return
     */
    public static String YMDToMD(String str) {
        if (str == null) {
            return null;
        }
        //String[] strs=str.split("-");
        //return strs[1]+"-"+strs[2];
        return str.substring(5);
    }

    /**
     * 将日期转换成 yyyy年M月 格式
     *
     * @param date Date类
     * @return String
     * @version1.0
     */
    public static String dateToString(Date date,String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }



    /**
     * 取得当前年月 返回的格式是yyyy-MM
     *
     * @return String
     */
    public static String getCurYearMonth() {
        return getCurrentYear() + "-" + getCurMonth();
    }



    /**
     * 返回strDate2和strDate1相差的天数，若strDate1 比strDate2 大则返回负数,相等返回0
     *
     * @param strDate1 yyyy-MM-dd
     * @param strDate2 yyyy-MM-dd
     * @return
     * @throws Exception
     */
    public static int compareDate(Date strDate1, Date strDate2) throws Exception {
        Date date1 = strDate1;
        Date date2 = strDate2;
        long rel = date2.getTime() - date1.getTime();
        return (int) (rel / (1000 * 60 * 60 * 24));
    }


    public static long compareHour(Date strTime1, Date strTime2) throws Exception {
        long rel = strTime2.getTime() - strTime1.getTime();
        return rel / (1000 * 60 * 60);
    }


    /**
     * @describe:解析字符串日期
     * @date: 2019/9/28
     * @author: Mr Bai
     */
    public static Date parseYYMMDDHHMMSS(String time) {
        Date date = null;
        try {
            SimpleDateFormat YyMDHmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = YyMDHmsFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @describe:解析字符串日期
     * @date: 2019/9/28
     * @author: Mr Bai
     */
    public static Date parseYYMMDD(String time) {
        Date date = new Date();
        try {
            if(!TextUtils.isEmpty(time)){
                SimpleDateFormat YyMDHmsFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = YyMDHmsFormat.parse(time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
