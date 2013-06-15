package com.h13.cardgame.jupiter.exceptions;

/**
 * 这个用户不存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class TaskCompletedTooManyException extends Exception {
    public static String CODE = "1010008";

    public TaskCompletedTooManyException(String msg) {
        super(msg);
    }

    public TaskCompletedTooManyException(String msg, Throwable t) {
        super(msg, t);
    }
}
