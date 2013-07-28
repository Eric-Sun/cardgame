package com.h13.cardgame.jupiter.exceptions;

/**
 * 小队卡仓库已经满了
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class SquardStorageIsFullException extends Exception {
    public static String CODE = "10015";

    public SquardStorageIsFullException(String msg) {
        super(msg);
    }

    public SquardStorageIsFullException(String msg, Throwable t) {
        super(msg, t);
    }
}
