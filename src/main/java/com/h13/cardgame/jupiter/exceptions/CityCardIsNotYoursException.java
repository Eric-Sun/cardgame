package com.h13.cardgame.jupiter.exceptions;

/**
 * 这张卡不是你的
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class CityCardIsNotYoursException extends Exception {
    public static String CODE = "10001";

    public CityCardIsNotYoursException(String msg) {
        super(msg);
    }

    public CityCardIsNotYoursException(String msg, Throwable t) {
        super(msg, t);
    }
}
