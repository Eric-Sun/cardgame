package com;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-10
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
public class T {

    public static void main(String[] args) {
        String s = "{_once_:\"000027_top_mysohuv2\",redirect_url:\"http://m.i.sohu.com/m/\"}";
        JSONObject json = JSON.parseObject(s);
        System.out.println(json.get("_once_"));
    }
}
