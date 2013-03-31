package com.h13.cardgame.scheduler.exception;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-26
 * Time: 下午2:45
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerFatalException extends Exception {
    public SchedulerFatalException(String msg) {
        super(msg);
    }

    public SchedulerFatalException(String msg, Throwable t) {
        super(msg, t);
    }
}
