package com.gitee.drinkjava2.frog.logger;

import java.text.SimpleDateFormat;

/**
 * @Description: 日志格式化类
 * @Author: Luancx
 * @Date: 2021/12/06
 * @Version: 1.0.0
 */
public final class MessageFormatter {
    public static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    private static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    /**
     * 格式胡日志
     *
     * @param message 元数据
     * @param params  参数
     * @return 格式化后数据
     */
    public static String messageFormat(final String message, final Object[] params) {
        if (message == null) {
            return null;
        }
        if (params == null || params.length == 0) {
            return message;
        }

        return arrayFormat(message, params);
    }

    /**
     * 替换日志中的占位符 {}
     *
     * @param message 元数据
     * @param args    参数
     * @return 格式化后数据
     */
    private static String arrayFormat(final String message, final Object[] args) {
        StringBuilder sb = new StringBuilder();

        int s;
        int i = 0;
        for (Object o : args) {
            s = message.indexOf(DELIM_STR, i);
            if (s == -1) {
                return i == 0 ? message : sb.append(message, i, message.length()).toString();
            } else {
                sb.append(message, i, s);
                sb.append(o);
                i = s + 2;
            }
        }
        return sb.toString();
    }

    public static Throwable getThrowableCandidate(final Object[] argArray) {
        if (argArray == null || argArray.length == 0) {
            return null;
        }
        final Object lastEntry = argArray[argArray.length - 1];
        if (lastEntry instanceof Throwable) {
            return (Throwable) lastEntry;
        }
        return null;
    }

    public static Object[] trimmedCopy(final Object[] argArray) {
        final int trimmedLen = argArray.length - 1;
        Object[] trimmed = new Object[trimmedLen];

        if (trimmedLen > 0) {
            System.arraycopy(argArray, 0, trimmed, 0, trimmedLen);
        }
        return trimmed;
    }
}
