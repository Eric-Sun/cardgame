package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 卡牌缓存的配置
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CardCache {

    private static String PREFIX = "cg:card:";

    @Resource(name = "redisStringCardCOTemplate")
    private RedisTemplate<String, CardCO> cardCOTemplate;

    public void put(CardCO card) {
        String key = PREFIX + card.getId();
        cardCOTemplate.opsForValue().set(key, card);
    }


    public CardCO get(long cid) {
        String key = PREFIX + cid;
        CardCO card = cardCOTemplate.opsForValue().get(key);
        return card;
    }


}
