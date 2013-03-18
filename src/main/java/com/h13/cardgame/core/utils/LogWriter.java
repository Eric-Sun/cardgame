package com.h13.cardgame.core.utils;

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


    public static String LOGIN = "login";
    public static String LOAD = "load";
    public static String INFO = "info";
    public static String TASK = "task";
    public static String REQEUST = "request";
    public static String RESPONSE = "response";

    public static void info(String catalog, String info) {
        LOG.info(catalog + "|" + info);
    }

    public static void debug(String catalog, String info) {
        LOG.debug(catalog + "|" + info);
    }

    public static void error(String catalog, String info, Throwable t) {
        LOG.error(catalog + "|" + info, t);
    }
}
