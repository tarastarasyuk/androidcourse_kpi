package com.tarastarasiuk.androidcourse.util;

import static com.tarastarasiuk.androidcourse.constant.AppConstant.DEFAULT_DATE_FORMAT;
import static java.util.Objects.requireNonNull;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateFormatterUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public static String formatDate(Date date) {
        requireNonNull(date);
        return formatter.format(date);
    }

    public static String formatDate(Date date, String format) {
        requireNonNull(date);
        requireNonNull(format);
        return new SimpleDateFormat(format).format(date);
    }
}
