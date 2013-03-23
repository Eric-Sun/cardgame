package com.h13.cardgame.core.helper;

import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.service.LevelCache;
import com.h13.cardgame.core.dao.LevelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LevelHelper {

    @Autowired
    LevelDAO levelDAO;
    @Autowired
    LevelCache levelCache;

    public LevelCO get(int level) {
        LevelCO levelCO = levelCache.get(level);
        if (levelCO == null) {
            // load from db
            levelCO = levelDAO.get(level);
            levelCache.put(levelCO);
        }
        return levelCO;
    }
}
