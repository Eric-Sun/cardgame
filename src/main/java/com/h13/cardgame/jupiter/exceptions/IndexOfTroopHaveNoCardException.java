package com.h13.cardgame.jupiter.exceptions;

/**
 * 队伍的某个位置是没有卡的，不能remove
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class IndexOfTroopHaveNoCardException extends Exception {
    public static String CODE = "10008";

    public IndexOfTroopHaveNoCardException(String msg) {
        super(msg);
    }

    public IndexOfTroopHaveNoCardException(String msg, Throwable t) {
        super(msg, t);
    }
}
