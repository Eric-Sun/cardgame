package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.co.SchedulerCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.queue.CacheUpdateMessage;
import com.h13.cardgame.queue.CacheUpdateQueue;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.scheduler.SchedulerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * captain緩存
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SchedulerCache {

    private static String PREFIX = "cg:scheduler:";

    @Resource(name = "schedulerCOTemplate")
    private RedisTemplate<String, SchedulerMessage> schedulerCOTemplate;
    @Autowired
    CacheUpdateQueue cacheUpdateQueue;

    public void put(SchedulerMessage sche) {
        String key = PREFIX + sche.getUid() + ":" + sche.getJobType().name() + ":" + sche.getActionObjectId();
        schedulerCOTemplate.opsForValue().set(key, sche);
    }

    public SchedulerMessage get(long uid, SchedulerType type, long actionObjectId) {
        String key = PREFIX + uid + ":" + type.name() + ":" + actionObjectId;
        SchedulerMessage sche = schedulerCOTemplate.opsForValue().get(key);
        return sche;
    }

    public void remove(SchedulerMessage sche) {
        String key = PREFIX + sche.getUid() + ":" + sche.getJobType().name() + ":" + sche.getActionObjectId();
        schedulerCOTemplate.delete(key);
    }

    public List<SchedulerMessage> getOldMessages() {
        String keyPattern = PREFIX + "*";
        Set<String> keySet = schedulerCOTemplate.keys(keyPattern);
        return schedulerCOTemplate.opsForValue().multiGet(keySet);
    }

    public void putToQueue(SchedulerMessage obj) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(obj));
        msg.setType(Configuration.CACHE.QUEUE_SCHEDULER_KEY);
        cacheUpdateQueue.push(msg);
    }

}
