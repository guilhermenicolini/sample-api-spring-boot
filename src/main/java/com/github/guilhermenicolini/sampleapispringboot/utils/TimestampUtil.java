package com.github.guilhermenicolini.sampleapispringboot.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimestampUtil {

    private TimestampUtil() {}

    public static Timestamp fromMillis(Long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return new Timestamp((cal.getTimeInMillis()));
    }
}
