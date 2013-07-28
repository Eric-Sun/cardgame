package com.h13.cardgame.jupiter.exceptions;

/**
 * 这个用户不存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class UserNotExistsException extends Exception {
    public static String CODE = "10021";

    public UserNotExistsException(String msg) {
        super(msg);
    }

    public UserNotExistsException(String msg, Throwable t) {
        super(msg, t);
    }
}
