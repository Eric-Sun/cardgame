package com.h13.cardgame.jupiter.exceptions;

/**
 * 这张卡不是你的
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class EquipmentIsNotEnoughException extends Exception {
    public static String CODE = "1010015";

    public EquipmentIsNotEnoughException(String msg) {
        super(msg);
    }

    public EquipmentIsNotEnoughException(String msg, Throwable t) {
        super(msg, t);
    }
}
