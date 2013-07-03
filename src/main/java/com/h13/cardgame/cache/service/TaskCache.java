package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.co.TaskCO;
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
public class TaskCache {
    private static String PREFIX = "cg:system:task:";

    @Resource(name = "taskCacheTemplate")
    private RedisTemplate<String, TaskCO> taskCOTemplate;

    @Autowired
    CacheUpdateQueue cacheUpdateQueue;
    public void put(TaskCO task) {
        String key = PREFIX + task.getId();
        taskCOTemplate.opsForValue().set(key, task);
    }

    public TaskCO get(long taskId) {
        String key = PREFIX + taskId;
        TaskCO co = taskCOTemplate.opsForValue().get(key);
        return co;
    }

    public void putToQueue(TaskCO obj) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(obj));
        msg.setType(Configuration.CACHE.QUEUE_TASK_KEY);
        cacheUpdateQueue.push(msg);
    }

}
