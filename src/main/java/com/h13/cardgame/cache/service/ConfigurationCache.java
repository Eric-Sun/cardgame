package com.h13.cardgame.cache.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ConfigurationCache {

    private static String PREFIX = "cg:system:config:";

    @Resource(name = "redisStringConfigurationCOTemplate")
    private RedisTemplate<String, String> configurationCOTemplate;


    public void put(String confKey, String confValue) {
        String key = PREFIX + confKey;
        configurationCOTemplate.opsForValue().set(key, confValue);
    }

    public String get(String confKey) {
        String key = PREFIX + confKey;
        String value = configurationCOTemplate.opsForValue().get(key);
        return value;
    }

}
