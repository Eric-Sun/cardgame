package com.h13.cardgame.jupiter.exceptions;

/**
 * 这张卡不存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class CityCardNotExistsException extends Exception {
    public static String CODE = "10003";

    public CityCardNotExistsException(String msg) {
        super(msg);
    }

    public CityCardNotExistsException(String msg, Throwable t) {
        super(msg, t);
    }
}
