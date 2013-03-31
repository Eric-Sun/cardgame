package com.h13.cardgame.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-25
 * Time: 下午6:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SchedulerQueue {

    private static Log LOG = LogFactory.getLog(DBTaskQueue.class);

    private static String KEY = "cg:queue:scheduler";

    @Resource(name = "schedulerDetailTemplate")
    private RedisTemplate<String, SchedulerMessage> schedulerDetailTemplate;


    public void push(SchedulerMessage detail) {
        schedulerDetailTemplate.opsForList().leftPush(KEY, detail);
    }

    public SchedulerMessage peek() {
        return schedulerDetailTemplate.opsForList().rightPop(KEY);
    }

}
