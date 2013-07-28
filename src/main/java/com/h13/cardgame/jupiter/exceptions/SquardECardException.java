package com.h13.cardgame.jupiter.exceptions;

/**
 * 小队已经绑定了装备卡
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class SquardECardException extends Exception {
    public static String CODE = "10014";

    public SquardECardException(String msg) {
        super(msg);
    }

    public SquardECardException(String msg, Throwable t) {
        super(msg, t);
    }
}
