package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.BarCO;
import com.h13.cardgame.cache.co.CardCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
@Service
public class BarCache {
    private static String PREFIX = "cg:bar:";

    @Resource(name = "barTemplate")
    private RedisTemplate<String, BarCO> barCOTemplate;

    public void put(BarCO bar, long l) {
        String key = PREFIX + bar.getCityId();
        barCOTemplate.opsForValue().set(key, bar, l);
    }

    public BarCO get(long cid) {
        String key = PREFIX + cid;
        BarCO bar = barCOTemplate.opsForValue().get(key);
        return bar;
    }

    public void delete(BarCO barCO) {
        String key = PREFIX + barCO.getCityId();
        barCOTemplate.delete(key);
    }
}
