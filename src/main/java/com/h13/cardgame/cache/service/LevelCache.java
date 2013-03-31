package com.h13.cardgame.cache.service;

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
public class LevelCache {

    private static String PREFIX = "cg:system:level:";

    @Resource(name = "redisStringLevelCOTemplate")
    private RedisTemplate<String, LevelCO> levelCOTemplate;


    public void put(LevelCO level) {
        String key = PREFIX + level.getLevel();
        levelCOTemplate.opsForValue().set(key, level);
    }

    public LevelCO get(int level) {
        String key = PREFIX + level;
        LevelCO levelCO = levelCOTemplate.opsForValue().get(key);
        return levelCO;
    }


}
