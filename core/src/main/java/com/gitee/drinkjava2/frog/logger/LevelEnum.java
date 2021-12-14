package com.gitee.drinkjava2.frog.logger;

/**
 * @Description: 日志等级枚举
 * @Author: Luancx
 * @Date: 2021/12/06
 * @Version: 1.0.0
 */
public enum LevelEnum {
    /**
     * 日志等级
     */
    DEBUG(1, "DEBUG"),
    INFO(2, "INFO"),
    WARN(3, "WARN"),
    ERROR(4, "ERROR");

    private final int levelInt;
    private final String levelStr;

    public static int getEnumInt(String levelStr) {
        LevelEnum[] levelEnums = values();
        for (LevelEnum level : levelEnums) {
            if (level.levelStr.equals(levelStr)) {
                return level.getLevelInt();
            }
        }
        throw new IllegalStateException("Level " + levelStr + " is unknown.");
    }


    LevelEnum(int levelInt, String levelStr) {
        this.levelInt = levelInt;
        this.levelStr = levelStr;
    }

    public int getLevelInt() {
        return levelInt;
    }

    public String getLevelStr() {
        return levelStr;
    }

}
