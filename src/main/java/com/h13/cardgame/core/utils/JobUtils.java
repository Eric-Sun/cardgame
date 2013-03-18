package com.h13.cardgame.core.utils;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-7
 * Time: 下午6:42
 * To change this template use File | Settings | File Templates.
 */
public class JobUtils {


    public static String getJobId(long cid) {
        return cid + System.currentTimeMillis() + "";
    }
}
