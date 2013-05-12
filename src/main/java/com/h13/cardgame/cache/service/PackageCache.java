package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.PackageCO;
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
public class PackageCache {

    private static String PREFIX = "cg:package:";

    @Resource(name = "packageCOTemplate")
    private RedisTemplate<String, PackageCO> packageCOTemplate;


    public void put(PackageCO pck) {
        String key = PREFIX + pck.getCityId();
        packageCOTemplate.opsForValue().set(key, pck);
    }

    public PackageCO get(long cid) {
        String key = PREFIX + cid;
        PackageCO pck = packageCOTemplate.opsForValue().get(key);
        return pck;
    }


}
