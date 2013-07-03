package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.co.TroopCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.queue.CacheUpdateMessage;
import com.h13.cardgame.queue.CacheUpdateQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * captain緩存
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TroopCache {

    private static String PREFIX = "cg:troop:";

    @Resource(name = "troopCOTemplate")
    private RedisTemplate<String, TroopCO> squardCOTemplate;

    @Autowired
    CacheUpdateQueue cacheUpdateQueue;

    public void put(TroopCO squard) {
        String key = PREFIX + squard.getCityId();
        squardCOTemplate.opsForValue().set(key, squard);
    }

    public TroopCO get(long cid) {
        String key = PREFIX + cid;
        TroopCO squard = squardCOTemplate.opsForValue().get(key);
        return squard;
    }

    public void putToQueue(TroopCO obj) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(obj));
        msg.setType(Configuration.CACHE.QUEUE_TROOP_KEY);
        cacheUpdateQueue.push(msg);
    }


}
