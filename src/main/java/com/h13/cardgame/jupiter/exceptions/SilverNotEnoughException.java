package com.h13.cardgame.jupiter.exceptions;

/**
 * 银币不够了
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class SilverNotEnoughException extends Exception {
    public static String CODE = "10012";

    public SilverNotEnoughException(String msg) {
        super(msg);
    }

    public SilverNotEnoughException(String msg, Throwable t) {
        super(msg, t);
    }
}
