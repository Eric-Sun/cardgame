package com.h13.cardgame.jupiter.exceptions;

/**
 * 服务器端不可预见性的错误
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class ServerErrorException extends Exception {
    public static String CODE = "1000001";

    public ServerErrorException(String msg) {
        super(msg);
    }

    public ServerErrorException(String msg, Throwable t) {
        super(msg, t);
    }
}
