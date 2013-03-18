package com.h13.cardgame.queue.utils;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public class QueueUtil {

    public static String getId() {
        return "job:" + System.currentTimeMillis();
    }

}
