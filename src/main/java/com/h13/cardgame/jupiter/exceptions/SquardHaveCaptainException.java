package com.h13.cardgame.jupiter.exceptions;

/**
 * 小队城市卡的等级已经满了
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class SquardHaveCaptainException extends Exception {
    public static String CODE = "1010015";

    public SquardHaveCaptainException(String msg) {
        super(msg);
    }

    public SquardHaveCaptainException(String msg, Throwable t) {
        super(msg, t);
    }
}
