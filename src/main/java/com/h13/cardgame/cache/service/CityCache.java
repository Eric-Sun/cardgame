package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CityCO;
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

    private static String PREFIX = "cg:city:";

    @Resource(name = "cityCacheTemplate")
    private RedisTemplate<String, CityCO> cityCacheTemplate;


    public void put(CityCO city) {
        String key = PREFIX + city.getId();
        cityCacheTemplate.opsForValue().set(key, city);
    }

    public CityCO get(long cid) {
        String key = PREFIX + cid;
        CityCO city = cityCacheTemplate.opsForValue().get(key);
        return city;
    }


}
