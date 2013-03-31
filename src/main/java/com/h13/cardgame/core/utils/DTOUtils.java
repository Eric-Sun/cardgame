package com.h13.cardgame.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.core.BaseDTO;
import com.h13.cardgame.core.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DTOUtils {
    private static Log LOG = LogFactory.getLog(DTOUtils.class);

    public static String getOriginalResponse(long uid, long cid) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.SUCCESS));
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

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

    public static String getFailureResponse(long uid, long cid, String errorCode) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, errorCode));
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

}
