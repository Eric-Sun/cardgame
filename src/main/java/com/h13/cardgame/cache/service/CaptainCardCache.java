package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CaptainCardCO;
import com.h13.cardgame.cache.co.CardCO;
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
public class CaptainCardCache {

    private static String PREFIX = "cg:captainCard:";

    @Resource(name = "captainCardCOTemplate")
    private RedisTemplate<String, CaptainCardCO> captainCardCOTemplate;


    public void put(CaptainCardCO card) {
        String key = PREFIX + card.getId();
        captainCardCOTemplate.opsForValue().set(key, card);
    }

    public CaptainCardCO get(long cid) {
        String key = PREFIX + cid;
        CaptainCardCO card = captainCardCOTemplate.opsForValue().get(key);
        return card;
    }


}
