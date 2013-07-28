package com.h13.cardgame.jupiter.exceptions;

/**
 * 这张卡已经上阵
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class UserDontHaveThisCityException extends Exception {
    public static String CODE = "10024";

    public UserDontHaveThisCityException(String msg) {
        super(msg);
    }

    public UserDontHaveThisCityException(String msg, Throwable t) {
        super(msg, t);
    }
}
