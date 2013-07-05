package com.h13.cardgame.jupiter.exceptions;

/**
 * 能量不够
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class EnergyNotEnoughException extends Exception {
    public static String CODE = "1010009";

    public EnergyNotEnoughException(String msg) {
        super(msg);
    }

    public EnergyNotEnoughException(String msg, Throwable t) {
        super(msg, t);
    }
}
