package com.h13.cardgame.jupiter.exceptions;

/**
 * 装备仓库已经满了
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class EquipmentStorageIsFullException extends Exception {
    public static String CODE = "1010015";

    public EquipmentStorageIsFullException(String msg) {
        super(msg);
    }

    public EquipmentStorageIsFullException(String msg, Throwable t) {
        super(msg, t);
    }
}
