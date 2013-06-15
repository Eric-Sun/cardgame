package com.h13.cardgame.jupiter.exceptions;

/**
 * 这个用户不存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class SilverNotEnoughException extends Exception {
    public static String CODE = "1010011";

    public SilverNotEnoughException(String msg) {
        super(msg);
    }

    public SilverNotEnoughException(String msg, Throwable t) {
        super(msg, t);
    }
}
