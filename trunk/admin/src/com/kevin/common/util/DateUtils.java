/**
 * DateUtil.java
 */
package com.kevin.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 
 * 工程名称: vsp
 * 包名: com.kevin.common.util 
 * 类型名: DateUtils 
 * 描述: 对日期进行处理的工具类
 * 
 * 例子:
 * 
 * @author <a href="mailto:yaniu_jiang@stardream.tv">蒋亚牛</a>
 * @version 1.0
 * @see
 * @since 2011-11-16 15:29:01
 */
public class DateUtils {
    /** 
     * Data format：yyyy-MM-dd HH:mm:ss 
     */ 
    public static final String DATE_FARMAT_LOCATION = "yyyy-MM-dd HH:mm:ss"; 

    /** 
     * Data format：yyyyMMddHHmmss 
     */ 
    public static final String DATE_FORMAT_STR = "yyyyMMddHHmmss"; 
    
    static Logger logger = Logger.getLogger(DateUtils.class.getName());

    public static String formatDate(Date d, String style) {
        if (style == null || "".equals(style)) {
            style = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(style);
        return format.format(d);
    }
    public static String formatDate(Date d) {
        return formatDate(d, null);
    }

    public static String formatNow(String style) {
        return formatDate(new Date(), style);
    }

    public static String formatNow() {
        return formatDate(new Date());
    }

    public static Date parseDate(String date, String style) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        try {
            Date d = format.parse(date);
            return d;
        } catch (ParseException e) {
            logger.info(date + ":日期格式错误或不匹配:" + style);
            return null;
        }
    }

    public static long convertDateToLong(Date d) {
        return d.getTime();
    }

    public static Date convertLongToDate(long l) {
        return new Date(l);
    }

    public static long convertStringToLong(String d, String style) {
        Date date = parseDate(d, style);
        if (date != null) {
            return date.getTime();
        } else {
            return -1;
        }
    }

    public static String convertLongToString(long l, String style) {
        return formatDate(new Date(l), style);
    }

    public static boolean isLeapYear(int year) {
        if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    public static boolean isLeapYear(Date d) {
        Calendar date = Calendar.getInstance();
        date.setTime(d);
        return isLeapYear(date.get(Calendar.YEAR));
    }

    public static int getDayOfNumFeb(Date d) {
        if (isLeapYear(d)) {
            return 29;
        }
        return 28;
    }

    public static String formatHHMMSS(long time, String unit) {
        if (unit == null || unit.trim().length() == 0) {
            unit = "s";// 默认为秒
        } else {
            unit = unit.toLowerCase();// 转为小写
        }
        StringBuilder result = new StringBuilder();
        int h = 0, m = 0, s = 0;
        // 转为秒
        if ("ms".equals(unit)) {
            time = time / 1000;
        }else if ("m".equals(unit)) {
            time = time * 60;
        } else if ("h".equals(unit)) {
            time = time * 60 * 60;
        } else if ("d".equals(unit)) {
            time = time * 60 * 60 * 24;
        }
        h = (int) (time / (60 * 60));// 获取小时
        time = time % (60 * 60);// 剩余的秒
        m = (int) (time / 60);// 获取分
        s = (int) (time % 60);// 获取秒
        if (h < 10) {
            result.append("0");
        }
        result.append(h).append(":");
        if (m < 10) {
            result.append("0");
        }
        result.append(m).append(":");
        if (s < 10) {
            result.append("0");
        }
        result.append(s);
        return result.toString();
    }
    
    public static String formatHHMM(long time, String unit) {
        if (unit == null || unit.trim().length() == 0) {
            unit = "s";// 默认为秒
        } else {
            unit = unit.toLowerCase();// 转为小写
        }
        StringBuilder result = new StringBuilder();
        int h = 0, m = 0;
        // 转为秒
        if ("ms".equals(unit)) {
            time = time / 1000;
        }else if ("m".equals(unit)) {
            time = time * 60;
        } else if ("h".equals(unit)) {
            time = time * 60 * 60;
        } else if ("d".equals(unit)) {
            time = time * 60 * 60 * 24;
        }
        h = (int) (time / (60 * 60));// 获取小时
        time = time % (60 * 60);// 剩余的秒
        m = (int) (time / 60);// 获取分
        
        if (h < 10) {
            result.append("0");
        }
        result.append(h).append(":");
        if (m < 10) {
            result.append("0");
        }
        result.append(m);
        return result.toString();
    }
    public static String formatHHMMSS(long time) {
        return formatHHMMSS(time, "s");
    }
    public static String formatHHMM(long time) {
        return formatHHMM(time, "s");
    }
    public static int getDateOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH)+1;
        int [] monthOf30 = {4,6,9,11};
        int [] monthOf31 = {1,3,5,7,8,10,12};
        for(int m:monthOf30){
            if(month == m){
                return 30;
            }
        }
        for(int m:monthOf31){
            if(month == m){
                return 31;
            }
        }
        int year = calendar.get(Calendar.YEAR);
        if(isLeapYear(year)){
            return 29;
        }else{
            return 28;
        }
    }
    public static long convertHHMMSSToLong(String time){
        if(time == null || time.length()!= 8){
            return 0;
        }
        //"00:24:30"
        String h = time.substring(0,2);
        String m = time.substring(3,5);
        String s = time.substring(6,8);
        return Long.valueOf(h)*60*60 + Long.valueOf(m)*60 + Long.valueOf(s);
    }
    public static long convertHHMMToLong(String time){
        if(time == null || time.length()!= 5){
            return 0;
        }
        //"00:24:30"
        String h = time.substring(0,2);
        String m = time.substring(3,5);
        return Long.valueOf(h)*60*60 + Long.valueOf(m)*60;
    }
    
    /**
     * 
     * @param date
     * @param dateFormat
     * @return
     */
    public static String formatDateToString(Date date, String dateFormat) 
    { 
        SimpleDateFormat format = new SimpleDateFormat(dateFormat); 
        return format.format(date); 
    } 
}