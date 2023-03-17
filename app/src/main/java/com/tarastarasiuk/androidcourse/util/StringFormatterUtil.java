package com.tarastarasiuk.androidcourse.util;

import static com.tarastarasiuk.androidcourse.constant.AppConstant.DEFAULT_RESULT_MAX_SIZE;
import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class StringFormatterUtil {

    public static String restrictTextLength(String text) {
        requireNonNull(text);
        return restrictTextLength(text, DEFAULT_RESULT_MAX_SIZE);
    }

    public static String restrictTextLength(String text, int max) {
        Objects.requireNonNull(text);
        return text.length() > max ? text.substring(0, max) : text;
    }
}
