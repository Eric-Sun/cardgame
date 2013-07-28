package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.BarCO;
import com.h13.cardgame.cache.co.CaptainLevelCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptainLevelCache {
    private static String PREFIX = "cg:captainLevel:";

    @Resource(name = "captainLevelTemplate")
    private RedisTemplate<String, CaptainLevelCO> captainLevelTemplate;

    public void put(CaptainLevelCO captainLevel) {
        String key = PREFIX + captainLevel.getId();
        captainLevelTemplate.opsForValue().set(key, captainLevel);
    }


    public CaptainLevelCO get(long captainLevelId) {
        String key = PREFIX + captainLevelId;
        CaptainLevelCO captainLevel = captainLevelTemplate.opsForValue().get(key);
        return captainLevel;
    }
}
