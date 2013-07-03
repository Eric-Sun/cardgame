package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.queue.CacheUpdateMessage;
import com.h13.cardgame.queue.CacheUpdateQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 卡牌缓存的配置
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class StorageCache {

    private static String PREFIX = "cg:package:";

    @Resource(name = "storageCOTemplate")
    private RedisTemplate<String, StorageCO> packageCOTemplate;
    @Autowired
    CacheUpdateQueue cacheUpdateQueue;

    public void put(StorageCO pck) {
        String key = PREFIX + pck.getCityId();
        packageCOTemplate.opsForValue().set(key, pck);
    }

    public StorageCO get(long cid) {
        String key = PREFIX + cid;
        StorageCO pck = packageCOTemplate.opsForValue().get(key);
        return pck;
    }

    public void putToQueue(StorageCO obj) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(obj));
        msg.setType(Configuration.CACHE.QUEUE_STORAGE_KEY);
        cacheUpdateQueue.push(msg);
    }



}
