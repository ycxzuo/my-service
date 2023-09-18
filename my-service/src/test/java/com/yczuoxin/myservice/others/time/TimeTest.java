package com.yczuoxin.myservice.others.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.TimeZone;

class TimeTest {

    @Test
    void testBasicTimeAPI() {
        LocalTime time = LocalTime.of(14, 36, 45);
        LocalDate date = LocalDate.of(2023, Month.SEPTEMBER, 27);
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        LocalTime localTime = localDateTime.toLocalTime();
        LocalDate localDate = localDateTime.toLocalDate();
        int year = localDate.get(ChronoField.YEAR);
        int month = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day = localDate.get(ChronoField.DAY_OF_MONTH);
        Assertions.assertEquals(time, localTime);
        Assertions.assertEquals(date, localDate);
    }

    @Test
    void testInstantAPI() {
        // 从 1970-01-01T00:00:00Z 开始计算时间
        Instant instant = Instant.ofEpochSecond(3);
        System.out.println(instant);
        Instant instant1 = Instant.ofEpochSecond(1, -1000_000_001);
        System.out.println(instant1);
        Instant now = Instant.now();
        System.out.println(now);
        // Instant的设计初衷是为了便于机器使用。它包含的是由秒及纳秒所构成的数字。所以，它无法处理那些我们非常容易理解的时间单位。
        Assertions.assertThrowsExactly(UnsupportedTemporalTypeException.class, () -> now.get(ChronoField.YEAR));
    }

    @Test
    void testDurationAPI() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 12, 12, 0, 0);
        Duration localDateTimeDuration = Duration.between(localDateTime, LocalDateTime.now());
        System.out.println(localDateTimeDuration);
        Instant instant = Instant.ofEpochSecond(3);
        Duration instantDuration = Duration.between(instant, Instant.now());
        System.out.println(instantDuration);
        // 两个类型不一样不能使用 between 方法
        Assertions.assertThrowsExactly(DateTimeException.class, () -> Duration.between(instant, localDateTime));
    }

    @Test
    void testPeriodAPI() {
        LocalDate localDate = LocalDate.of(2023, Month.SEPTEMBER, 12);
        LocalDate localDate2 = LocalDate.of(2023, Month.SEPTEMBER, 13);
        Assertions.assertEquals(Period.between(localDate, localDate2), Period.ofDays(1));
    }

    @Test
    void testOperateAPI() {
        LocalDate localDate = LocalDate.of(2023, Month.SEPTEMBER, 12);
        LocalDate localDate2 = localDate.withDayOfMonth(13);
        Assertions.assertEquals(localDate2, LocalDate.of(2023, Month.SEPTEMBER, 13));

        localDate2 = localDate.plus(Period.ofDays(2));
        Assertions.assertEquals(localDate2, LocalDate.of(2023, Month.SEPTEMBER, 14));
    }

    @Test
    void testTemporalAdjusterAPI() {
        TemporalAdjuster dateAdjuster = TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dayOfWeek = temporal.getDayOfWeek();
            int addDays = 1;
            if (dayOfWeek == DayOfWeek.FRIDAY) addDays = 3;
            if (dayOfWeek == DayOfWeek.SATURDAY) addDays = 2;
            return temporal.plusDays(addDays);
        });
        LocalDate localDate = LocalDate.of(2023, Month.SEPTEMBER, 15);
        LocalDate nextDay = localDate.with(dateAdjuster);
        Assertions.assertEquals(nextDay, LocalDate.of(2023, Month.SEPTEMBER, 18));
    }

    /**
     * 和老的 java.text.DateFormat 相比较，所有的 DateTimeFormatter 实例都是线程安全的。
     *
     * @see DateFormat
     */
    @Test
    void testDateTimeFormatter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        DateTimeFormatter formatter = builder
                .appendText(ChronoField.DAY_OF_WEEK)
                .appendLiteral(". ")
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(" ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseSensitive()
                .toFormatter();
        LocalDate date = LocalDate.of(2014, 3, 18);

        String dateStr = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(date.format(dateTimeFormatter));
        System.out.println(date.format(formatter));
        LocalDate formatterDate = LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
        Assertions.assertEquals(date, formatterDate);
    }

    /**
     * 新的 java.time.ZoneId 类是老版 java.util.TimeZone 的替代品。
     *
     * @see TimeZone
     */
    @Test
    void testZoneId() {
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZoneId defaultZoneId = ZoneId.systemDefault();
        ZoneId timeZoneId = TimeZone.getDefault().toZoneId();
        Assertions.assertEquals(zoneId, defaultZoneId);
        Assertions.assertEquals(zoneId, timeZoneId);

        LocalDate localDate = LocalDate.of(2023, Month.SEPTEMBER, 12);
        ZonedDateTime zdt1 = localDate.atStartOfDay(defaultZoneId);
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 12, 12, 0, 0);
        ZonedDateTime zdt2 = localDateTime.atZone(defaultZoneId);

        Instant instantFromDateTime = localDateTime.toInstant(ZoneOffset.of("+08:00"));
        Assertions.assertEquals(Instant.parse("2023-09-12T04:00:00Z"), instantFromDateTime);

        Instant instant = Instant.now();
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, defaultZoneId);
        Assertions.assertEquals(timeFromInstant, LocalDateTime.now());
    }

    @Test
    void testZoneOffset() {
        ZoneOffset zoneOffset = ZoneOffset.of("+08:00");
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 12, 12, 0, 0);
        OffsetDateTime offsetDateTime = localDateTime.atOffset(zoneOffset);
        Assertions.assertEquals(offsetDateTime, OffsetDateTime.of(localDateTime, zoneOffset));
    }

}
