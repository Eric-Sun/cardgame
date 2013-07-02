package com.h13.cardgame.jupiter.exceptions;

/**
 * 这张卡已经上阵
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class SquardStorageIsFullException extends Exception {
    public static String CODE = "1010015";

    public SquardStorageIsFullException(String msg) {
        super(msg);
    }

    public SquardStorageIsFullException(String msg, Throwable t) {
        super(msg, t);
    }
}
