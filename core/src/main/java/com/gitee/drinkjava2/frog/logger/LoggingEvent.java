package com.gitee.drinkjava2.frog.logger;

import sun.misc.JavaLangAccess;
import sun.misc.SharedSecrets;

import java.io.Serializable;

/**
 * @Description: 日志实体类
 * @Author: Luancx
 * @Date: 2021/12/03
 * @Version: 1.0.0
 */
public final class LoggingEvent implements Serializable {
    /**
     * 类名
     */
    private final String className;
    /**
     * 方法名
     */
    private final String methodName;
    /**
     * 行数
     */
    private final int lineNumber;
    /**
     * 日志等级
     */
    private final LevelEnum level;
    /**
     * 时间
     */
    private final long timeStamp;
    /**
     * 原始消息
     */
    private final String message;
    /**
     * 格式化后数据
     */
    private String formattedMessage;
    /**
     * 消息参数
     */
    private final Object[] argumentArray;
    /**
     * 异常信息
     */
    private Throwable throwable;

    public LoggingEvent(LevelEnum level, String message, Object[] argumentArray) {
        JavaLangAccess access = SharedSecrets.getJavaLangAccess();
        Throwable throwable = new Throwable();
        StackTraceElement stack = access.getStackTraceElement(throwable, 3);

        this.className = stack.getClassName();
        this.methodName = stack.getMethodName();
        this.lineNumber = stack.getLineNumber();
        this.level = level;
        this.message = message;

        //异常数据
        Throwable throwableCandidate = MessageFormatter.getThrowableCandidate(argumentArray);
        Object[] args = argumentArray;
        if (throwableCandidate != null) {
            //剔除异常参数
            args = MessageFormatter.trimmedCopy(argumentArray);
            this.throwable = throwableCandidate;
        }
        this.argumentArray = args;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getFormattedMessage() {
        if (formattedMessage != null) {
            return formattedMessage;
        }
        if (argumentArray != null) {
            formattedMessage = MessageFormatter.messageFormat(message, argumentArray);
        } else {
            formattedMessage = message;
        }
        return formattedMessage;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
