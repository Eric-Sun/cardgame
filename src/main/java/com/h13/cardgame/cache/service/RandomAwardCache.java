package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.RandomAwardCO;
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
public class RandomAwardCache {

    private static String PREFIX = "cg:random_award:";

    @Resource(name = "redisStringRandomAwardCOTemplate")
    private RedisTemplate<String, RandomAwardCO> randomAwardCOTemplate;


    public void put(RandomAwardCO ra) {
        String key = PREFIX + ra.getId();
        randomAwardCOTemplate.opsForValue().set(key, ra);
    }

    public RandomAwardCO get(long cid) {
        String key = PREFIX + cid;
        RandomAwardCO ra = randomAwardCOTemplate.opsForValue().get(key);
        return ra;
    }


}
