package com.h13.cardgame.jupiter.exceptions;

/**
 * 这个任务组不存在
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class TaskGroupIsNotExistsException extends Exception {
    public static String CODE = "1010015";

    public TaskGroupIsNotExistsException(String msg) {
        super(msg);
    }

    public TaskGroupIsNotExistsException(String msg, Throwable t) {
        super(msg, t);
    }
}
