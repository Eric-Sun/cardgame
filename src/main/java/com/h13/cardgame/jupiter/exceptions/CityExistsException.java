package com.h13.cardgame.jupiter.exceptions;

/**
 * 这个用户不存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class CityExistsException extends Exception {
    public static String CODE = "1001";

    public CityExistsException(String msg) {
        super(msg);
    }

    public CityExistsException(String msg, Throwable t) {
        super(msg, t);
    }
}
