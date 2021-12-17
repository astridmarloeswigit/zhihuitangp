package com.gitee.drinkjava2.frog.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Description: 日志输出类
 * @Author: Luancx
 * @Date: 2021/12/06
 * @Version: 1.0.0
 */
public class PrintLogService extends Thread {
    private static final OutputStream OUTPUT_STREAM = System.out;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String BLANK = " ";
    private static final String TAB = "\tat";

    @Override
    public void run() {
        while (true) {
            try {
                LoggingEvent event = LoggerCenter.LOG_LIST.take();
                if (LoggerCenter.CONSOLE) {
                    outPutConsole(event);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void outPutConsole(LoggingEvent event) {
        try {
            OUTPUT_STREAM.write(recursiveAppend(event));
            OUTPUT_STREAM.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 日志和异常信息组装
     */
    private byte[] recursiveAppend(LoggingEvent event) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append(MessageFormatter.FORMAT.format(event.getTimeStamp())).append(BLANK);
        sb.append("[").append(event.getLevel().getLevelStr()).append("][").append(event.getClassName()).append(BLANK);
        sb.append(event.getLineNumber()).append("][").append(event.getMethodName()).append("] ");
        sb.append("- ").append(event.getFormattedMessage()).append(LINE_SEPARATOR);

        appendThrowableInfo(event, sb);
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private void appendThrowableInfo(LoggingEvent event, StringBuilder sb) {
        Throwable throwable = event.getThrowable();
        if (null != throwable) {
            sb.append(throwable).append(LINE_SEPARATOR);
            StackTraceElement[] stack = throwable.getStackTrace();
            for (StackTraceElement element : stack) {
                sb.append(TAB).append(element).append(LINE_SEPARATOR);
            }
        }
    }
}
