package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CityLevelCO;
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
public class LevelCache {

    private static String PREFIX = "cg:system:level:";

    @Resource(name = "redisStringLevelCOTemplate")
    private RedisTemplate<String, CityLevelCO> levelCOTemplate;
    public void put(CityLevelCO level) {
        String key = PREFIX + level.getLevel();
        levelCOTemplate.opsForValue().set(key, level);
    }

    public CityLevelCO get(int level) {
        String key = PREFIX + level;
        CityLevelCO cityLevelCO = levelCOTemplate.opsForValue().get(key);
        return cityLevelCO;
    }


}
