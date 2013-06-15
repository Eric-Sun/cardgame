package com.h13.cardgame.jupiter.exceptions;

/**
 * 队伍的某个位置已经有卡了
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class IndexOfTroopHaveAnotherCardException extends Exception {
    public static String CODE = "1010013";

    public IndexOfTroopHaveAnotherCardException(String msg) {
        super(msg);
    }

    public IndexOfTroopHaveAnotherCardException(String msg, Throwable t) {
        super(msg, t);
    }
}
