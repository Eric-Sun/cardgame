package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.CaptainTaskCO;
import com.h13.cardgame.cache.co.LevelCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptainCache {

    private static String PREFIX = "captain:";

    @Resource(name = "redisStringCaptainCOTemplate")
    private RedisTemplate<String, CaptainCO> captainCOTemplate;


    public void put(CaptainCO captain) {
        String key = PREFIX + captain.getId();
        captainCOTemplate.opsForValue().set(key, captain);
    }

    public CaptainCO get(long cid) {
        String key = PREFIX + cid;
        CaptainCO captain = captainCOTemplate.opsForValue().get(key);
        return captain;
    }


}
