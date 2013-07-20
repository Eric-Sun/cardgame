package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.TroopCO;
import com.h13.cardgame.cache.co.UnitsCardCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * captain緩存
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UnitsCardCache {

    private static String PREFIX = "cg:unitsCard:";

    @Resource(name = "unitsCardTemplate")
    private RedisTemplate<String, UnitsCardCO> unitsCardCOTemplate;

    public void put(UnitsCardCO unitsCard) {
        String key = PREFIX + unitsCard.getId();
        unitsCardCOTemplate.opsForValue().set(key, unitsCard);
    }

    public UnitsCardCO get(long cid) {
        String key = PREFIX + cid;
        UnitsCardCO unitsCard = unitsCardCOTemplate.opsForValue().get(key);
        return unitsCard;
    }


    public List<UnitsCardCO> getAll() {
        Set<String> set = unitsCardCOTemplate.keys(PREFIX + "*");
        List<UnitsCardCO> list = unitsCardCOTemplate.opsForValue().multiGet(set);
        return list;
    }

}
