package com.h13.cardgame.jupiter.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class SquardCardNotEnoughSlotException extends Exception {
    public static String CODE = "1010012";

    public SquardCardNotEnoughSlotException(String msg) {
        super(msg);
    }

    public SquardCardNotEnoughSlotException(String msg, Throwable t) {
        super(msg, t);
    }
}
