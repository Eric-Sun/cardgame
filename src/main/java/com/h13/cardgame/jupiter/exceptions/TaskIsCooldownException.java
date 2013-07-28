package com.h13.cardgame.jupiter.exceptions;

/**
 * 当前想完成的任务正在冷却
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class TaskIsCooldownException extends Exception {
    public static String CODE = "10016";

    public TaskIsCooldownException(String msg) {
        super(msg);
    }

    public TaskIsCooldownException(String msg, Throwable t) {
        super(msg, t);
    }
}
