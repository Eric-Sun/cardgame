package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.TroopCO;
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


    public void put(TroopCO squard) {
        String key = PREFIX + squard.getCityId();
        squardCOTemplate.opsForValue().set(key, squard);
    }

    public TroopCO get(long cid) {
        String key = PREFIX + cid;
        TroopCO squard = squardCOTemplate.opsForValue().get(key);
        return squard;
    }


}
