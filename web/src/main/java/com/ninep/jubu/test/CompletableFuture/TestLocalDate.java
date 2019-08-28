package com.ninep.jubu.test.CompletableFuture;


import io.swagger.models.auth.In;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 * @author wangjunfeng
 * @version 1.0
 * java 8 date
 * @since 2019/3/24
 */
public class TestLocalDate {

    public static void main(String[] args) {
//        LocalDate now = LocalDate.now();
//        System.out.println(now);
//        System.out.println(now.getYear());
//        System.out.println(now.getDayOfMonth());
//        System.out.println(now.getDayOfYear());
//        System.out.println(now.getEra());
//        System.out.println(now.getLong(ChronoField.YEAR));
//        System.out.println(now.getLong(ChronoField.MONTH_OF_YEAR));
//        System.out.println(now.getLong(ChronoField.DAY_OF_MONTH));
        test4();
    }

    private static void test4() {
        LocalDateTime time = LocalDateTime.of(2000, 12, 19, 12, 03, 04);
        LocalDateTime now = LocalDateTime.now();
        Duration between = Duration.between(time, now);
        System.out.println(between.ofDays(10));

    }


    private static void test3() {
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(Instant.ofEpochSecond(2));
        System.out.println(Instant.ofEpochSecond(3,0));
    }


    public static void test2() {
        LocalTime time = LocalTime.now();
        System.out.println(time.getSecond());
        System.out.println(time.getMinute());
        System.out.println(time.getHour());

        System.out.println("...............");
//        System.out.println(LocalDate.parse("2014-02-30"));
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toLocalDate());
        System.out.println(now.toLocalTime());
    }

    public static void test1() {
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.APRIL, 18, 13, 45, 00);
        System.out.println(dt1);
        LocalTime now = LocalTime.now();
        LocalDate date = LocalDate.now();
        LocalDateTime dt2 = LocalDateTime.of(date, now);
        System.out.println(dt2);

        System.out.println("-----------");
        System.out.println(now.atDate(date));
        System.out.println("============");
        System.out.println(date.atTime(now));

    }

    public static void test() {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println("///////////////");
        System.out.println(LocalDate.parse("2019-02-20"));
        System.out.println(LocalTime.parse("21:30:09"));
    }

}