package com.h13.cardgame.jupiter.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class MailExistedException extends Exception {
    public static String CODE = "1010102";

    public MailExistedException(String msg) {
        super(msg);
    }

    public MailExistedException(String msg, Throwable t) {
        super(msg, t);
    }
}
