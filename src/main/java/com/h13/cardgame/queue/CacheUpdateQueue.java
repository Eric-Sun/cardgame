package com.h13.cardgame.queue;

import com.h13.cardgame.queue.utils.QueueUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-2
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CacheUpdateQueue {

    private static Log LOG = LogFactory.getLog(DBTaskQueue.class);

    private static String KEY = "cg:queue:cache";

    @Resource(name = "cacheUpdateQueueRedisTemplate")
    private RedisTemplate<String, CacheUpdateMessage> cacheUpdateQueueRedisTemplate;

    public void push(CacheUpdateMessage detail) {
        cacheUpdateQueueRedisTemplate.opsForList().leftPush(KEY, detail);
    }


    public CacheUpdateMessage peek() {
        return cacheUpdateQueueRedisTemplate.opsForList().rightPop(KEY);
    }

}
