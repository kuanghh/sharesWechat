package com.khh.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 * 日期解析类
 */
public class DateUtil {

    public static String DATE_PATTERN_DAY = "yyyy-MM-dd";
    public static String DATE_PATTERN_SECOND = "yyyy-MM-dd  HH:mm:ss";

    public static String dateToString(Date date) throws Exception{
        return dateToString(date,DATE_PATTERN_SECOND);
    }

    public static String dateToString(Date date,String pattern) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static Date stringToDate(String dateStr) throws Exception{
        return stringToDate(dateStr,DATE_PATTERN_DAY);
    }

    public static Date stringToDate(String dateStr,String pattern) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(dateStr);
    }

    public static String getToday(String pattern) throws Exception{
        return dateToString(new Date(), pattern);

    }

    public static String getYesterday(String pattern) throws Exception{
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = dateToString(cal.getTime(), pattern);
        System.out.println(yesterday);

        return yesterday;
    }

    /**
     * 判断字符串是不是正确的日期类型,默认格式是,yyyy-MM-dd
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static boolean isDate(String dateStr,String pattern) {

        if(pattern == null || pattern.trim().length() == 0){
            pattern = DATE_PATTERN_DAY;
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(true);

        try {
            format.parse(dateStr);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isDate(String dateStr) {

        return isDate(dateStr, null);
    }
}
