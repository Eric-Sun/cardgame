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
 * Date: 13-3-15
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DBTaskQueue {

    private static Log LOG = LogFactory.getLog(DBTaskQueue.class);

    private static String KEY = "dbtask:queue";

    @Resource(name = "dbTaskDetailTemplate")
    private RedisTemplate<String, DBTaskDetail> dbTaskQueueRedisTemplate;

    public void push(DBTaskDetail detail) {
        dbTaskQueueRedisTemplate.opsForList().leftPush(KEY, detail);
    }

    public void push(String sql, Object[] params) {
        DBTaskDetail detail = new DBTaskDetail(QueueUtil.getId(), sql, params);
        dbTaskQueueRedisTemplate.opsForList().leftPush(KEY, detail);
    }

    public DBTaskDetail peek() {
        return dbTaskQueueRedisTemplate.opsForList().rightPop(KEY);
    }

}
