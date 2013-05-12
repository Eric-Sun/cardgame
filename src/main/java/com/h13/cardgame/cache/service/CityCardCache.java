package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CityCardCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * captain卡牌缓存的配置
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CityCardCache {

    private static String PREFIX = "cg:captainCard:";

    @Resource(name = "captainCardCOTemplate")
    private RedisTemplate<String, CityCardCO> captainCardCOTemplate;


    public void put(CityCardCO card) {
        String key = PREFIX + card.getId();
        captainCardCOTemplate.opsForValue().set(key, card);
    }

    public CityCardCO get(long cid) {
        String key = PREFIX + cid;
        CityCardCO card = captainCardCOTemplate.opsForValue().get(key);
        return card;
    }


}
