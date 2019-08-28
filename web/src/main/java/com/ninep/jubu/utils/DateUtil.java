package com.ninep.jubu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil{
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DefaultShortFormat = "yyyy-MM-dd";
    public static final String DefaultLongFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String DefaultMinteFormat = "yyyy-MM-dd HH:mm";

    /**
     * date -> string
     * @param date 日期
     * @return string
     */
    public static String Date2String(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DefaultShortFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * date -> int
     * @param date 日期
     * @return int
     */
    public static Integer Date2IntDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(format.format(date));
    }

    /**
     * 秒转日期
     * @param seconds 秒
     * @return  string
     */
    public static String secondsToString(Integer seconds) {
        return Date2String(fromUnixTime(seconds));
    }

    /**
     * date -> string  指定格式转换
     * @param date 日期
     * @param formatString 指定的格式
     * @return string
     */
    public static String Date2String(Date date, String formatString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            return format.format(date);
        } catch (IllegalArgumentException e) {
            logger.warn("format string Illegal: {},{}", formatString, e);
            return "";
        }
    }

    /**
     * 返回当前时间秒数
     * @return int
     */
    public static int unixTime() {
        return (int) System.currentTimeMillis()/1000;
    }

    /**
     * int秒  -> date
     * @param seconds 秒
     * @return 日期
     */
    public static Date fromUnixTime(Integer seconds) {
        return new Date(seconds * 1000L);
    }

    public static Date string2Date(String fixedDate) {
        LocalDate fixLocalDate = LocalDate.parse(fixedDate);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = fixLocalDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
}