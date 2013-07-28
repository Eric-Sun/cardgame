package com.h13.cardgame.jupiter.exceptions;

/**
 * 用户名已经存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class UserNameExistedException extends Exception {
    public static String CODE = "10019";

    public UserNameExistedException(String msg) {
        super(msg);
    }

    public UserNameExistedException(String msg, Throwable t) {
        super(msg, t);
    }
}
