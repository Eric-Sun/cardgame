package com.h13.cardgame.core.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class RandomRewardException extends Exception {
    public static String CODE = "1001";

    public RandomRewardException(String msg) {
        super(msg);
    }

    public RandomRewardException(String msg, Throwable t) {
        super(msg, t);
    }
}
