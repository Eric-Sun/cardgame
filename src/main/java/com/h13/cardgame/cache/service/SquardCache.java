package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.SquardCO;
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
public class SquardCache {

    private static String PREFIX = "cg:squard:";

    @Resource(name = "squardCOTemplate")
    private RedisTemplate<String, SquardCO> squardCOTemplate;


    public void put(SquardCO squard) {
        String key = PREFIX + squard.getCaptainId();
        squardCOTemplate.opsForValue().set(key, squard);
    }

    public SquardCO get(long cid) {
        String key = PREFIX + cid;
        SquardCO squard = squardCOTemplate.opsForValue().get(key);
        return squard;
    }


}
