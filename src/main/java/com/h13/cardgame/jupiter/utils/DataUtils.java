package com.h13.cardgame.jupiter.utils;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-23
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class DataUtils {
    public static long getSquardLongData(Map data, String key) {
        return new Long(((Map<String, String>) data).get(key).toString());
    }

    public static int getSquardIntData(Map data, String key) {
        return new Integer(((Map<String, String>) data).get(key).toString());
    }

    public static String getSquardStringData(Map data, String key) {
        return ((Map<String, String>) data).get(key).toString();
    }

    public static void putSquardLongData(Map data, String key, Object value) {
        ((Map<String, String>) data).put(key, value.toString());
    }

    public static void putSquardStringData(Map data, String key, Object value) {
        ((Map<String, String>) data).put(key, value.toString());
    }
}
