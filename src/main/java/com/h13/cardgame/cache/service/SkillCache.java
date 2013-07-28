package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.SkillCO;
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
public class SkillCache {
    private static String PREFIX = "cg:skill:";

    @Resource(name = "skillTemplate")
    private RedisTemplate<String, SkillCO> skillTemplate;

    public void put(SkillCO skill) {
        String key = PREFIX + skill.getId();
        skillTemplate.opsForValue().set(key, skill);
    }

    public SkillCO get(long cid) {
        String key = PREFIX + cid;
        SkillCO skill = skillTemplate.opsForValue().get(key);
        return skill;
    }
}
