package com.h13.cardgame.jupiter.exceptions;

/**
 * 用户名或者密码错误
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class UserNameOrPwdErrorException extends Exception {
    public static String CODE = "1010201";

    public UserNameOrPwdErrorException(String msg) {
        super(msg);
    }

    public UserNameOrPwdErrorException(String msg, Throwable t) {
        super(msg, t);
    }
}
