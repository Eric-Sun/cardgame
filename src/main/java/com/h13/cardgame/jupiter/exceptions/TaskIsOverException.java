package com.h13.cardgame.jupiter.exceptions;

/**
 * 任务已经全部完成了，没有任务做了
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class TaskIsOverException extends Exception {
    public static String CODE = "10018";

    public TaskIsOverException(String msg) {
        super(msg);
    }

    public TaskIsOverException(String msg, Throwable t) {
        super(msg, t);
    }
}
