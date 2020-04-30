package com.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天

    /**
     * To format.
     *
     * @param date   the date
     * @param format the format
     * @return the date
     */
    public static Date toFormat(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * To format string.
     *
     * @param date   the date
     * @param format the format
     * @return the string
     */
    public static String toFormatString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * To format.
     *
     * @param date       the date
     * @param timeFormat the time format
     * @param time       the time
     * @return the date
     */
    public static Date toFormat(Date date, String timeFormat, String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        SimpleDateFormat newformatter = new SimpleDateFormat("yyyy-MM-dd " + timeFormat);
        try {
            return newformatter.parse(dateString + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * To max day.
     *
     * @param date the date
     * @return the date
     */
    public static Date toMaxDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        SimpleDateFormat newformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return newformatter.parse(dateString + " " + "23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * To day.
     *
     * @param date the date
     * @return the date
     */
    public static Date toDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) throws Exception {
        return parseDate(strDate, null);
    }

    /**
     * parseDate
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseDate(String strDate, String pattern) throws Exception {
        Date date = null;
        try {
            if (pattern == null) {
                pattern = "yyyy-MM-dd";
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(strDate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return date;
    }

    /**
     * format date
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) throws Exception {
        return formatDate(date, null);
    }

    /**
     * format date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) throws Exception {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = "yyyy-MM-dd";
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return strDate;
    }

    /**
     * @param strDate
     * @return
     */
    public static Date parseDateTime(String strDate) throws Exception {
        return parseDateTime(strDate, null);
    }

    /**
     * parseDate
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date parseDateTime(String strDate, String pattern) throws Exception {
        Date date = null;
        try {
            if (pattern == null) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            date = format.parse(strDate);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return date;
    }

    /**
     * format date
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) throws Exception {
        return formatDateTime(date, null);
    }

    /**
     * format date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDateTime(Date date, String pattern) throws Exception {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return strDate;
    }

    /**
     * 取得年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        return month + 1;
    }


    /**
     * 取得当天日期是周几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.DAY_OF_WEEK);
        return week_of_year - 1;
    }

    /**
     * 取得一年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }


    //输入月份求该月由哪些周组成 (重要)
    public static void printfWeeks(String date) throws Exception {
        // String date = "2013-09";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date1 = dateFormat.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("days:" + days);
        int count = 0;
        for (int i = 1; i <= days; i++) {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = dateFormat1.parse(date + "-" + i);
            calendar.clear();
            calendar.setTime(date2);
            int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
            if (k == 1) {// 若当天是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                if (i - 6 <= 1) {
                    System.out.println("本周开始日期:" + date + "-" + 1);
                } else {
                    System.out.println("本周开始日期:" + date + "-" + (i - 6));
                }
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
            if (k != 1 && i == days) {// 若是本月最好一天，且不是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                System.out.println("本周开始日期:" + date + "-" + (i - k + 2));
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
        }
    }

    public static void printfWeeks2(String date,String startDate,String endDate) throws Exception {
        // String date = "2013-09";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date1 = dateFormat.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("days:" + days);
        int count = 0;

        for (int i = 1; i <= days; i++) {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = dateFormat1.parse(date + "-" + i);
            calendar.clear();
            calendar.setTime(date2);
            int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
            if (k == 1) {// 若当天是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                if (i - 6 <= 1) {
                    System.out.println("本周开始日期:" + date + "-" + 1);
                } else {
                    System.out.println("本周开始日期:" + date + "-" + (i - 6));
                }
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
            if (k != 1 && i == days) {// 若是本月最好一天，且不是周日
                count++;
                System.out.println("-----------------------------------");
                System.out.println("第" + count + "周");
                System.out.println("本周开始日期:" + date + "-" + (i - k + 2));
                System.out.println("本周结束日期:" + date + "-" + i);
                System.out.println("-----------------------------------");
            }
        }
    }


    /**
     * getWeekBeginAndEndDate
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getWeekBeginAndEndDate(Date date, String pattern) throws Exception {
        Date monday = getMondayOfWeek(date);
        Date sunday = getSundayOfWeek(date);
        return formatDate(monday, pattern) + " - " + formatDate(sunday, pattern);
    }

    /**
     * 根据日期取得对应周周一日期
     *
     * @param date
     * @return
     */
    public static Date getMondayOfWeek(Date date) {
        Calendar monday = Calendar.getInstance();
        monday.setTime(date);
        monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return monday.getTime();
    }

    /**
     * 根据日期取得对应周周日日期
     *
     * @param date
     * @return
     */
    public static Date getSundayOfWeek(Date date) {
        Calendar sunday = Calendar.getInstance();
        sunday.setTime(date);
        sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return sunday.getTime();
    }

    /**
     * 取得月的剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDayOfMonth(Date date) {
        int dayOfMonth = getDayOfMonth(date);
        int day = getPassDayOfMonth(date);
        return dayOfMonth - day;
    }

    /**
     * 取得月已经过的天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得年天数
     *
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, DateUtil.getYear(new Date()));
        return c.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 取得月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    /**
     * 取得季度第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfQuarter(Date date) {
        return getFirstDateOfMonth(getQuarterDate(date)[0]);
    }

    /**
     * 取得季度最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateOfQuarter(Date date) {
        return getLastDateOfMonth(getQuarterDate(date)[2]);
    }

    /**
     * 取得季度天数
     *
     * @param date
     * @return
     */
    public static int getDayOfQuarter(Date date) {
        int day = 0;
        Date[] quarterDates = getQuarterDate(date);
        for (Date date2 : quarterDates) {
            day += getDayOfMonth(date2);
        }
        return day;
    }

    /**
     * 取得季度剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDayOfQuarter(Date date) {
        return getDayOfQuarter(date) - getPassDayOfQuarter(date);
    }

    /**
     * 取得季度已过天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfQuarter(Date date) {
        int day = 0;

        Date[] quarterDates = getQuarterDate(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);

        if (month == Calendar.JANUARY || month == Calendar.APRIL
                || month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
            day = getPassDayOfMonth(quarterDates[0]);
        } else if (month == Calendar.FEBRUARY || month == Calendar.MAY
                || month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
            day = getDayOfMonth(quarterDates[0])
                    + getPassDayOfMonth(quarterDates[1]);
        } else if (month == Calendar.MARCH || month == Calendar.JUNE
                || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
            day = getDayOfMonth(quarterDates[0]) + getDayOfMonth(quarterDates[1])
                    + getPassDayOfMonth(quarterDates[2]);
        }
        return day;
    }

    /**
     * 取得季度月
     *
     * @param date
     * @return
     */
    public static Date[] getQuarterDate(Date date) {
        Date[] quarter = new Date[3];

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int nQuarter = getQuarter(date);
        if (nQuarter == 1) {// 第一季度
            c.set(Calendar.MONTH, Calendar.JANUARY);
            quarter[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.FEBRUARY);
            quarter[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MARCH);
            quarter[2] = c.getTime();
        } else if (nQuarter == 2) {// 第二季度
            c.set(Calendar.MONTH, Calendar.APRIL);
            quarter[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.MAY);
            quarter[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.JUNE);
            quarter[2] = c.getTime();
        } else if (nQuarter == 3) {// 第三季度
            c.set(Calendar.MONTH, Calendar.JULY);
            quarter[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.AUGUST);
            quarter[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            quarter[2] = c.getTime();
        } else if (nQuarter == 4) {// 第四季度
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            quarter[0] = c.getTime();
            c.set(Calendar.MONTH, Calendar.NOVEMBER);
            quarter[1] = c.getTime();
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            quarter[2] = c.getTime();
        }
        return quarter;
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getQuarter(Date date) {

        int quarter = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                quarter = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                quarter = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                quarter = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                quarter = 4;
                break;
            default:
                break;
        }
        return quarter;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public static String getPastDate(Date date, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取过去几天的日期数组
     *
     * @param past
     * @return
     */
    public static List<String> getPastDateList(int past) {
        Calendar now = Calendar.getInstance();
        List<String> dates = new ArrayList<>();
        Long oneDay = 86400000L;//一天是这些毫秒。
        Long day = now.getTimeInMillis();//获取当前日期的毫秒数。
        for (int i = 0; i < past; i++) {//past为你需要的天数。
            day = day - oneDay;//每次循环都将day变为前一天
            Date date = new Date(day);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(date);//日期格式化
            dates.add(dateStr);
        }
        return dates;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public static String getFetureDate(Date date, int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来past天的日期数组
     *
     * @param past
     * @return
     */
    public static List<String> getFetureDateList(int past) {
        Calendar now = Calendar.getInstance();
        List<String> dates = new ArrayList<>();
        Long oneDay = 86400000L;//一天是这些毫秒。
        Long day = now.getTimeInMillis();//获取当前日期的毫秒数。
        for (int i = 0; i < past; i++) {//past为你需要的天数。
            day = day + oneDay;//每次循环都将day变为后一天
            Date date = new Date(day);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(date);//日期格式化
            dates.add(dateStr);
        }
        return dates;
    }

    /**
     * 计算日期相差天数.
     *
     * @return the int
     */
    public static int daysBetween(String startDate, String endDate) {
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try {   cal.setTime(parseDate(startDate, "yyyy-MM-dd"));

            time1 = cal.getTimeInMillis();
            cal.setTime(parseDate(endDate, "yyyy-MM-dd"));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int daysBetween(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;
        try {
            cal.setTime(parseDate(formatDate(startDate, "yyyy-MM-dd"), "yyyy-MM-dd"));
            time1 = cal.getTimeInMillis();
            cal.setTime(parseDate(formatDate(endDate, "yyyy-MM-dd"), "yyyy-MM-dd"));
            time2 = cal.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date   字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    public static Date timeStamp2() {
        long time = System.currentTimeMillis();
        Date date = new Date();
        date.setTime(time);
        return date;
    }

    //功能描述: 根据日期获取星期几
    public static String getWeekOfDate(Date date) {
        /**
         *
         * 功能描述: 根据日期获取星期几
         *
         * @auther: lkj
         * @date: 2018/4/3 下午1:46
         * @param: [date]
         * @return: java.lang.String
         *
         */
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /***
     * 根据时间获取下一天的时间
     * 例如： 输入 2019-2-18 则返回的时间会加1天
     * @return
     */
    public static Date getNextDayByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        Date tdate = date;// 取时间
        calendar.setTime(tdate);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往
        return calendar.getTime();
    }

    /**]
     * 1 表示往后退， -1表示往前推一天
     * @param date
     * @param day
     * @return
     */
    public static Date getNextDayByDate(Date date,int day) {
        Calendar calendar = Calendar.getInstance();
        Date tdate = date;// 取时间
        calendar.setTime(tdate);
        calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往
        return calendar.getTime();
    }

    /**
     * 时间戳转换为date格式
     *
     * @return
     */
    public static Date timeStamp(long time) {
        Date date = new Date();
        date.setTime(time);
        return date;
    }

    /**
     * format date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate2(Date date, String pattern) throws Exception {
        String strDate = null;
        try {
            if (pattern == null) {
                pattern = "yyyy-MM-dd";
            }
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            strDate = format.format(date);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return strDate;
    }

    /**
     * 获取本周、本月、本季度等的开始或结束时
     *
     * @param type 1=本周，2=本月，3=本季度,4=本年
     * @param date 如果为空，则查询当前时间
     * @return
     * eg:
     *  如果日期是 2019-06-04 如果返回本周，那么
     *      startDate = 2019-06-03
     *       endDate = 2019-06-9
     */
    public static Map<String, String> getStartAndEndDateByType(String type, Date date) throws Exception {

        //设置返回list
        Map<String, String> resultMap = new HashMap<>();

        Calendar cal = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }

        cal.setTime(date);

        //本日
        if("0".equals(type)){
            //开始时间
            resultMap.put("startDate", formatDate(cal.getTime()));

            //结束时间
            cal.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往
            resultMap.put("endDate", formatDate(cal.getTime()));
        }

        //本周
        if ("1".equals(type)) {
            //开始时间
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            resultMap.put("startDate", formatDate(cal.getTime()));

            //结束时间
            cal.setTime(cal.getTime());
            cal.add(Calendar.DAY_OF_WEEK, 7);
            resultMap.put("endDate", formatDate(cal.getTime()));
        }

        //本月
        if ("2".equals(type)) {
            //开始时间
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            resultMap.put("startDate", formatDate(cal.getTime()));

            //结束时间
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 24);
            resultMap.put("endDate", formatDate(cal.getTime()));
        }

        //本季
        if ("3".equals(type)) {

            //开始时间
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = null;
            try {
                if (currentMonth >= 1 && currentMonth <= 3)
                    cal.set(Calendar.MONTH, 0);
                else if (currentMonth >= 4 && currentMonth <= 6)
                    cal.set(Calendar.MONTH, 3);
                else if (currentMonth >= 7 && currentMonth <= 9)
                    cal.set(Calendar.MONTH, 4);
                else if (currentMonth >= 10 && currentMonth <= 12)
                    cal.set(Calendar.MONTH, 9);
                cal.set(Calendar.DATE, 1);
                now = longSdf.parse(shortSdf.format(cal.getTime()) + " 00:00:00");
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultMap.put("startDate", formatDate(cal.getTime()));

            //结束时间
            cal.setTime(cal.getTime());
            cal.add(Calendar.MONTH, 3);
            resultMap.put("endDate", formatDate(cal.getTime()));

        }

        //todo 这里我把结束时间给减少了一天，理类上来看感觉是没问题的 ?
        if("0".equals(type)||"1".equals(type)||"2".equals(type)||"3".equals(type))
        {
            resultMap.put("endDate",formatDate(getNextDayByDate(parseDate(resultMap.get("endDate")),-1)));
        }


        //本年
        if("4".equals(type)){
            //开始时间
            resultMap.put("startDate", new SimpleDateFormat("yyyy").format(new Date())+"-01-01");

            //结束时间
            cal.set(Calendar.MONTH,cal.getActualMaximum(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date currYearLast = cal.getTime();
            resultMap.put("endDate", new SimpleDateFormat("yyyy-MM-dd").format(currYearLast));
        }

        return resultMap;
    }


    public static int getWeek(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        //这个是关键哈.
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周的第一天
        calendar.setTime(date);
        //第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        //第几天，从周日开始
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    public static int getWeek(Date str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = str;
        Calendar calendar = Calendar.getInstance();
        //这个是关键哈.
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周的第一天
        calendar.setTime(date);
        //第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        //第几天，从周日开始
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }


    //根据时间和类型返回具体的时间
    public static String getDateInfo( Date date, String dateType) throws Exception {
        //根据 0=自定义(这里取的就是天) 1=本周，2=本月，3=本季，4=本年,返回具体的天数是什么
        int info = 0;
        String infoStr ="";
        switch (dateType) {
            case "0":
                //info = TimeDateUtils.getDay(date);
                //infoStr = info + "日";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                infoStr = sdf.format(date);
                break;
            case "1":
                //info = DateUtil.getWeek(date);
                //infoStr = info + "周";
                infoStr = TimeDateUtils.getYear(date) + "年" +
                        TimeDateUtils.getMonth(date) + "月" +
                        DateUtil.getWeek(date) +"周";
                break;
            case "2":
                //info = TimeDateUtils.getMonth(date);
                //infoStr = info + "月";
                infoStr = TimeDateUtils.getYear(date) + "年" +
                            TimeDateUtils.getMonth(date) + "月";
                break;
            case "3":
                //info = TimeDateUtils.getSeason(date);
                //infoStr = info + "季度";
                infoStr = TimeDateUtils.getYear(date) + "年" +
                          TimeDateUtils.getSeason(date) +"季度";
                break;
            case "4":
                info = TimeDateUtils.getYear(date);
                infoStr = info + "年";
                break;
        }
        return infoStr;
    }

}
