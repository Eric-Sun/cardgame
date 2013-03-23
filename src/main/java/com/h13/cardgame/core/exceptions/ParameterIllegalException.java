package com.h13.cardgame.core.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class ParameterIllegalException extends Exception {
    public static String CODE = "1001";

    public ParameterIllegalException(String msg) {
        super(msg);
    }

    public ParameterIllegalException(String msg, Throwable t) {
        super(msg, t);
    }
}
