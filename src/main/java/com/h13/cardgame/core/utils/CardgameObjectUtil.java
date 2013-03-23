package com.h13.cardgame.core.utils;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.CaptainTaskCO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午6:33
 * To change this template use File | Settings | File Templates.
 */
public class CardgameObjectUtil {

    public static CaptainCO newCaptain(long uid, String name) {
        CaptainCO cap = new CaptainCO();
        cap.setName(name);
        cap.setUserId(uid);
        cap.setExp(0);
        cap.setGold(0);
        cap.setId(uid);
        cap.setLevel(1);
        cap.setSilver(0);
        cap.setTaskInfo(new CaptainTaskCO());
        return cap;
    }

}
