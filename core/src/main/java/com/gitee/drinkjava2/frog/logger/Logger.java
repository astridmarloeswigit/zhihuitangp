package com.gitee.drinkjava2.frog.logger;

/**
 * @Description: 日志工厂类
 * @Author: Luancx
 * @Date: 2021/12/02
 * @Version: 1.0.0
 */
public final class Logger {
    public static void debug(String msg) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.DEBUG, msg, null));
    }

    public static void debug(String msg, Object... argArray) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.DEBUG, msg, argArray));
    }

    public static void info(String msg) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.INFO, msg, null));
    }

    public static void info(String msg, Object... argArray) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.INFO, msg, argArray));
    }

    public static void warn(String msg) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.WARN, msg, null));
    }

    public static void warn(String msg, Object... argArray) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.WARN, msg, argArray));
    }

    public static void error(String msg) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.ERROR, msg, null));
    }

    public static void error(String msg, Object... argArray) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.ERROR, msg, argArray));
    }

    public static void error(Object argArray) {
        LoggerCenter.add(getLoggingEvent(LevelEnum.ERROR, "", new Object[]{argArray}));
    }

    private static LoggingEvent getLoggingEvent(LevelEnum level, String message, Object[] argumentArray) {
        return new LoggingEvent(level, message, argumentArray);
    }
}
