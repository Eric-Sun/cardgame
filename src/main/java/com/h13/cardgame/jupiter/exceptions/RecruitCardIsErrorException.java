package com.h13.cardgame.jupiter.exceptions;

/**
 * 想要招募的兵种和装备可以招募的兵种不同
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class RecruitCardIsErrorException extends Exception {
    public static String CODE = "1010015";

    public RecruitCardIsErrorException(String msg) {
        super(msg);
    }

    public RecruitCardIsErrorException(String msg, Throwable t) {
        super(msg, t);
    }
}
