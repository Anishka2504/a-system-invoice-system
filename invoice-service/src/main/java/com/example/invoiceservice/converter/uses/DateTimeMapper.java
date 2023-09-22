package com.example.invoiceservice.converter.uses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.example.invoiceservice.constant.Constants.DATE_PATTERN;
import static com.example.invoiceservice.constant.Constants.DATE_TIME_PATTERN;

public class DateTimeMapper {
    //-------------date to String-------------------------------------------
    public static String date(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }

    public static String localDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static String localDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
    //-------------String to date-------------------------------------------

    public static Date date(String date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATE_TIME_PATTERN).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Incorrect date-time source string");
        }
    }

    public static LocalDate localDate(String date) {
        if (date == null) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));

    }

    public static LocalDateTime localDateTime(String date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }

}
