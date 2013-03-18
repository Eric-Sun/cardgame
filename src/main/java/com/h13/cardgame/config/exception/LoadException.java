package com.h13.cardgame.config.exception;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-11
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
public class LoadException extends Exception {

    public LoadException(String msg) {
        super(msg);
    }

    public LoadException(String msg, Throwable t) {
        super(msg, t);
    }

}
