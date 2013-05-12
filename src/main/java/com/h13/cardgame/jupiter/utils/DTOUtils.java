package com.h13.cardgame.jupiter.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.jupiter.BaseDTO;
import com.h13.cardgame.jupiter.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DTO的util
 */
public class DTOUtils {
    private static Log LOG = LogFactory.getLog(DTOUtils.class);

    /**
     * 返回的是一个类似于执行的请求，没有任何返回结果的可能性
     *
     * @param uid
     * @param cid
     * @return
     */
    public static String getOriginalResponse(long uid, long cid) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.SUCCESS));
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

    /**
     * 返回一个成功的操作，并且包含N个返回结果
     *
     * @param uid
     * @param cid
     * @param info
     * @return
     */
    public static String getSucessResponse(long uid, long cid, Object... info) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.SUCCESS));
        for (Object obj : info) {
            list.add(obj);
        }
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

    /**
     * 返回一个操作失败的结果
     * @param uid
     * @param cid
     * @param errorCode
     * @return
     */
    public static String getFailureResponse(long uid, long cid, String errorCode) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.FAILURE, errorCode));
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

}
