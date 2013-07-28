package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.CaptainLevelCO;
import com.h13.cardgame.cache.co.CaptainTitleCO;
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
public class CaptainTitleCache {
    private static String PREFIX = "cg:captainTitle:";

    @Resource(name = "captainTitleTemplate")
    private RedisTemplate<String, CaptainTitleCO> captainTitleTemplate;

    public void put(CaptainTitleCO captainTitle) {
        String key = PREFIX + captainTitle.getId();
        captainTitleTemplate.opsForValue().set(key, captainTitle);
    }


    public CaptainTitleCO get(long captainTitleId) {
        String key = PREFIX + captainTitleId;
        CaptainTitleCO captainTitle = captainTitleTemplate.opsForValue().get(key);
        return captainTitle;
    }
}
