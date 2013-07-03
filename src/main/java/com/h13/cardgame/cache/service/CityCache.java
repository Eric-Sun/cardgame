package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.queue.CacheUpdateMessage;
import com.h13.cardgame.queue.CacheUpdateQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * captain緩存
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CityCache {
    private static Log LOG = LogFactory.getLog(CityCache.class);

    private static String PREFIX = "cg:city:";

    @Resource(name = "cityCacheTemplate")
    private RedisTemplate<String, CityCO> cityCacheTemplate;
    @Autowired
    CacheUpdateQueue cacheUpdateQueue;
    @Autowired
    WriteLockCache lockCache;

    public void put(CityCO city) {
        String key = PREFIX + city.getId();
        cityCacheTemplate.opsForValue().set(key, city);
        lockCache.releaseLock("CITY:" + city.getId());
        LOG.info("put city. city=" + JSON.toJSONString(city));
    }

    public CityCO get(long cid) throws InterruptedException {
        boolean lock = false;
        while ((lock == lockCache.lock("CITY:" + cid, 10))) {
            LOG.info("dont have lock-----------------------------------------------------------------------------");
            Thread.sleep(100L);
        }
        LOG.info("????????????have the lock?????///////");
        String key = PREFIX + cid;
        CityCO city = cityCacheTemplate.opsForValue().get(key);
        LOG.info("get city. city=" + JSON.toJSONString(city));
        return city;
    }


    public void putToQueue(CityCO city) {
        CacheUpdateMessage msg = new CacheUpdateMessage();
        msg.setData(JSON.toJSONString(city));
        msg.setType(Configuration.CACHE.QUEUE_CITY_KEY);
        cacheUpdateQueue.push(msg);
    }

}
