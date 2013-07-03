package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.queue.CacheUpdateMessage;
import com.h13.cardgame.queue.CacheUpdateQueue;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static String PREFIX = "cg:cityCard:";

    @Resource(name = "captainCardCOTemplate")
    private RedisTemplate<String, CityCardCO> captainCardCOTemplate;

    @Autowired
    CacheUpdateQueue cacheUpdateQueue;

    public void put(CityCardCO card) {
        String key = PREFIX + card.getId();
        captainCardCOTemplate.opsForValue().set(key, card);
    }

    public CityCardCO get(long cid) {
        String key = PREFIX + cid;
        CityCardCO card = captainCardCOTemplate.opsForValue().get(key);
        return card;
    }

    public void putToQueue(CityCardCO obj) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(obj));
        msg.setType(Configuration.CACHE.QUEUE_CITYCARD_KEY);
        cacheUpdateQueue.push(msg);
    }
}
