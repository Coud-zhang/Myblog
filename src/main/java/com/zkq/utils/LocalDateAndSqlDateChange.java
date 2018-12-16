package com.zkq.utils;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author zkq15
 */
@Component
public class LocalDateAndSqlDateChange {

    public static Date localDateToSqlDate(LocalDate localDate){
        return java.sql.Date.valueOf(localDate);
    }

    public static LocalDate sqlDateToLocalDate(Date date){
        return date.toLocalDate();
    }
}
