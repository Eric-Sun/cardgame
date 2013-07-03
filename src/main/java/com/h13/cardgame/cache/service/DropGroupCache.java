package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.DropGroupCO;
import com.h13.cardgame.cache.co.DropGroupDataCO;
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
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DropGroupCache {

    private static String PREFIX = "cg:dropGroup:";

    @Resource(name = "dropGroupTemplate")
    private RedisTemplate<String, DropGroupCO> dropGroupTemplate;
    @Autowired
    CacheUpdateQueue cacheUpdateQueue;

    public void put(DropGroupCO dropGroup) {
        String key = PREFIX + dropGroup.getId();
        dropGroupTemplate.opsForValue().set(key, dropGroup);
    }

    public DropGroupCO get(long dgId) {
        String key = PREFIX + dgId;
        DropGroupCO dropGroup = dropGroupTemplate.opsForValue().get(key);
        return dropGroup;
    }

    public void putToQueue(DropGroupCO obj) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(obj));
        msg.setType(Configuration.CACHE.QUEUE_DROPGROUP_KEY);
        cacheUpdateQueue.push(msg);
    }
}