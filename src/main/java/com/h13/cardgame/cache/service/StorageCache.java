package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.StorageCO;
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

    private static String PREFIX = "cg:storage:";

    @Resource(name = "storageCOTemplate")
    private RedisTemplate<String, StorageCO> packageCOTemplate;

    public void put(StorageCO pck) {
        String key = PREFIX + pck.getCityId();
        packageCOTemplate.opsForValue().set(key, pck);
    }

    public StorageCO get(long cid) {
        String key = PREFIX + cid;
        StorageCO pck = packageCOTemplate.opsForValue().get(key);
        return pck;
    }


}
