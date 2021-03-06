package com.h13.cardgame.jupiter.exceptions;

/**
 * 这张卡已经上阵
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class CityCardIsOnListException extends Exception {
    public static String CODE = "10002";

    public CityCardIsOnListException(String msg) {
        super(msg);
    }

    public CityCardIsOnListException(String msg, Throwable t) {
        super(msg, t);
    }
}
