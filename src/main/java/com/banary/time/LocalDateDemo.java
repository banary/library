package com.banary.time;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class LocalDateDemo {

    public static void main(String[] args) {
        instantDemo();
    }

    public static void localDateDemo(){
        //创建日期，如果当月的日期不存在，抛出DateTimeException
        LocalDate localDate = LocalDate.of(2018, 2, 21);
        System.out.println("year:" + localDate.getYear());
        System.out.println("month:" + localDate.getMonthValue());
        System.out.println("day:" + localDate.getDayOfMonth());
        System.err.println("--------分割线--------");
        //获取当前日期
        localDate = LocalDate.now();
        System.out.println("year:" + localDate.getYear());
        System.out.println("month:" + localDate.getMonthValue());
        System.out.println("day:" + localDate.getDayOfMonth());
    }


    public static void localTimeDemo(){
        //创建日期，如果当月的日期不存在，抛出DateTimeException
        LocalTime localTime = LocalTime.of(12, 20, 30);
        System.out.println("hour:" + localTime.getHour());
        System.out.println("minute:" + localTime.getMinute());
        System.out.println("second:" + localTime.getSecond());
        System.out.println("nano:" + localTime.getNano());
        System.err.println("--------分割线--------");
        //获取当前日期
        localTime = LocalTime.now();
        System.out.println("hour:" + localTime.getHour());
        System.out.println("minute:" + localTime.getMinute());
        System.out.println("second:" + localTime.getSecond());
        System.out.println("nano:" + localTime.getNano());
    }

    public static void localDateTimeDemo(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("year:" + localDateTime.getYear());
        System.out.println("month:" + localDateTime.getMonthValue());
        System.out.println("day:" + localDateTime.getDayOfMonth());
        System.out.println("hour:" + localDateTime.getHour());
        System.out.println("minute:" + localDateTime.getMinute());
        System.out.println("second:" + localDateTime.getSecond());
        System.out.println("nano:" + localDateTime.getNano());
    }

    public static void instantDemo(){
        Instant instant = Instant.now();
        System.out.println(instant.getEpochSecond());
        System.out.println(Instant.ofEpochSecond(3).getEpochSecond());
        System.out.println(Instant.ofEpochSecond(3, 0).toString());
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000L).toString());
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000L).toString());
    }

    public static void durationDemo(){

    }

    public static void temporalAdjusterDemo(){
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println(localDate1);
        LocalDate localDate2 = localDate1.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(localDate2);
    }


}
