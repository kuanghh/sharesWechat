package com.khh.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 * 日期解析类
 */
public class DateUtil {

    public static String dateToString(Date date) throws Exception{
        return dateToString(date,"yyyy-MM-dd  HH:mm:ss");
    }

    public static String dateToString(Date date,String pattern) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}
