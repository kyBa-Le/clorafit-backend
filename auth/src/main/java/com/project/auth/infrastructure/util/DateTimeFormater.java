package com.project.auth.infrastructure.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormater {
    public final static String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(FORMAT);

    public static String SimpleDateFormatConverter(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
