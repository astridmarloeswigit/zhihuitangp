package com.gitee.drinkjava2.frog.logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 日志处理中心
 * @Author: Luancx
 * @Date: 2021/12/03
 * @Version: 1.0.0
 */
public class LoggerCenter {
    //***************** 按需修改 *********************
    /**
     * 日志等级
     */
    private static final String LEV_EL = "info";
    /**
     * 控制台日志输出
     */
    protected static final boolean CONSOLE = true;
    /**
     * 文件日志输出
     */
    protected static final boolean FILE = false;
    /**
     * 日志文件路径
     */
    private static final String FILE_PATH = "";

    //***************** 请勿修改 *********************

    private static final int LEVEL_INT;
    /**
     * 日志消息队列
     */
    protected static final BlockingQueue<LoggingEvent> LOG_LIST = new ArrayBlockingQueue<>(1024);

    static {
        new PrintLogService().start();
        LEVEL_INT = LevelEnum.getEnumInt(LEV_EL.toUpperCase());
    }

    /**
     * 添加到待打印List中
     */
    public static void add(LoggingEvent loggingEvent) {
        if (loggingEvent.getLevel().getLevelInt() >= LEVEL_INT) {
            try {
                LOG_LIST.put(loggingEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
