package com.h13.cardgame.scheduler.exception;

/**
 * 这个job需要重新跑
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 */
public class JobShouldBeRerunException extends Exception {
    public static String CODE = "1010015";

    public JobShouldBeRerunException(String msg) {
        super(msg);
    }

    public JobShouldBeRerunException(String msg, Throwable t) {
        super(msg, t);
    }
}
