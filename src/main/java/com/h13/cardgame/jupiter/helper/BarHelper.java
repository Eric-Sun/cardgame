package com.h13.cardgame.jupiter.helper;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.BarCO;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.service.BarCache;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.exceptions.UserDontHaveThisCityException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.utils.RandomUtils;
import com.h13.cardgame.jupiter.vo.BarRandomGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BarHelper {

    @Autowired
    private CityHelper cityHelper;

    @Autowired
    private BarCache barCache;

    @Autowired
    private ConfigHelper configHelper;

    @Autowired
    private CardHelper cardHelper;

    /**
     * 刷新酒馆
     *
     * @param cityId
     */
    public BarCO flushBar(long uid, long cityId) throws UserNotExistsException, UserDontHaveThisCityException {
        CityCO city = cityHelper.get(uid, cityId);
        int barSize = city.getBarSize();
        BarCO bar = new BarCO();
        bar.setCityId(cityId);
        bar.setSize(barSize);
        bar.setList(doRandomCardList(barSize));
        barCache.put(bar);
        return bar;
    }

    /**
     * 从酒馆中招募队长
     *
     * @param uid
     * @param cityId
     * @param cardId
     * @throws UserNotExistsException
     * @throws UserDontHaveThisCityException
     */
    public void recruit(long uid, long cityId, long cardId) throws UserNotExistsException, UserDontHaveThisCityException {
        CityCO city = cityHelper.get(uid, cityId);
        CardCO card = cardHelper.get(cardId);
        cardHelper.addCaptainCard(city, card);
        // 从酒馆把这个队长删除掉
        BarCO bar = barCache.get(cityId);
        bar.getList().remove(cardId + "");
        barCache.put(bar);
    }


    public BarCO show(long uid, long cid) throws UserNotExistsException, UserDontHaveThisCityException {
        BarCO bar = barCache.get(cid);
        if (bar == null)
            bar = flushBar(uid, cid);
        return bar;
    }

    private List<String> doRandomCardList(int barSize) {
        String groupString = configHelper.get(Configuration.CONFIG.BAR_RANDOM_GROUP);
        BarRandomGroupVO barRandom = JSON.parseObject(groupString, BarRandomGroupVO.class);
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < barSize; i++) {
            long cardId = RandomUtils.randomCard(barRandom.getList(), barRandom.getWeightSum());
            list.add(cardId + "");
        }
        return list;
    }
}
