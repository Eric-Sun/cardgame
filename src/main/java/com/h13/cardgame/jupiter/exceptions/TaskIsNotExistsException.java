package com.h13.cardgame.jupiter.exceptions;

/**
 * 这个任务不存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class TaskIsNotExistsException extends Exception {
    public static String CODE = "10017";

    public TaskIsNotExistsException(String msg) {
        super(msg);
    }

    public TaskIsNotExistsException(String msg, Throwable t) {
        super(msg, t);
    }
}
