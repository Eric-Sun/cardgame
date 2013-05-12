package com.h13.cardgame.jupiter.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-2-22
 * Time: 下午11:56
 * To change this template use File | Settings | File Templates.
 */
public class LogWriter {
    private static Log LOG = LogFactory.getLog(LogWriter.class);


    public static String REQEUST = "request";
    public static String RESPONSE = "response";
    public static String PASSPORT_LOGIN = "passport|login";
    public static String PASSPORT_REGIETER = "passport|register";
    public static String CITY = "city";


    public static void info(String catalog, String info) {
        LOG.info(catalog + "|" + info);
    }

    public static void debug(String catalog, String info) {
        LOG.debug(catalog + "|" + info);
    }

    public static void warn(String catalog, Throwable t) {
        LOG.warn(catalog + "|" + t.getMessage());
    }

    public static void error(String catalog, Throwable t) {
        LOG.error(catalog + "|", t);
    }
}
