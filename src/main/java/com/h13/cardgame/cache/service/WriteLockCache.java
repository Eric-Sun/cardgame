package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.TroopCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-2
 * Time: 下午6:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WriteLockCache {

    private static String PREFIX = "cg:system:lock:";

    @Resource(name = "lockTemplate")
    private RedisTemplate<String, Long> template;
    public final static int UN_GET_LOCK = 0;

    public final static int GET_LOCK = 1;

    /**
     * 加锁,若锁已超时，使用getSet操作抢占锁
     *
     * @param key
     * @param timeout
     * @return
     */
    public boolean lock(String key, int timeout) {
        boolean lock = template.opsForValue().setIfAbsent(key, getTimeStamp(timeout));
        if (!lock) {
            // 如果有数据 为false
            Object obj = template.opsForValue().get(key);
            if (obj == null) {
                return lock(key, timeout);
            } else {
                long timeStamp = (Long) obj;
                if (timeStamp < System.currentTimeMillis()) {
                    // 已经可以获得锁
                    long lastTimeout = 0;
                    lastTimeout = new Long(template.opsForValue().getAndSet(key, getTimeStamp(timeout)));
                    //如果超时说明获得了锁
                    if (lastTimeout <= System.currentTimeMillis())
                        lock = true;
                }
            }
        }
        return lock;
    }

    /**
     * 释放锁
     *
     * @param key
     */
    public void releaseLock(String key) {
        // 没有锁
        Object obj = template.opsForValue().get(key);
        if (obj == null)
            return;
        long timeStamp = (Long) obj;
        if (timeStamp < System.currentTimeMillis())
            return;
        else
            template.delete(key);
    }

    private static long getTimeStamp(long timeout) {
        return System.currentTimeMillis() + timeout * 1000 + 1;
    }


}
