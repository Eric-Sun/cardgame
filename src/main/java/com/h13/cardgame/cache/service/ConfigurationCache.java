package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.queue.CacheUpdateMessage;
import com.h13.cardgame.queue.CacheUpdateQueue;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CacheUpdateQueue cacheUpdateQueue;

    public void put(String obj) {
        String confKey = obj.split(",")[0];
        String confValue = obj.split(",")[1];
        String key = PREFIX + confKey;
        configurationCOTemplate.opsForValue().set(key, confValue);
    }

    public String get(String confKey) {
        String key = PREFIX + confKey;
        String value = configurationCOTemplate.opsForValue().get(key);
        return value;
    }

    public void putToQueue(String obj) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(obj));
        msg.setType(Configuration.CACHE.QUEUE_CONFIGURATION_KEY);
        cacheUpdateQueue.push(msg);
    }

}
