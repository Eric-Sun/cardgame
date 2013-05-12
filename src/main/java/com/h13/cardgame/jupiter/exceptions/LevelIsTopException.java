package com.h13.cardgame.jupiter.exceptions;

/**
 * 已经满级了
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class LevelIsTopException extends Exception {
    public static String CODE = "1001";

    public LevelIsTopException(String msg) {
        super(msg);
    }

    public LevelIsTopException(String msg, Throwable t) {
        super(msg, t);
    }
}
