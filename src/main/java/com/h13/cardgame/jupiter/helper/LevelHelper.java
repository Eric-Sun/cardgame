package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.service.LevelCache;
import com.h13.cardgame.jupiter.dao.CityDAO;
import com.h13.cardgame.jupiter.dao.LevelDAO;
import com.h13.cardgame.jupiter.exceptions.LevelIsTopException;
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
    @Autowired
    CityDAO cityDAO;

    public LevelCO get(int level) {
        LevelCO levelCO = levelCache.get(level);
        if (levelCO == null) {
            // load from db
            levelCO = levelDAO.get(level);
            if (levelCO == null)
                return null;
            levelCache.put(levelCO);
        }
        return levelCO;
    }


    /**
     * 当可以升级的时候返回下一个等级的信息，如果已经满级了，抛出异常
     * 如果不可以升级返回null
     *
     * @param city
     * @return
     * @throws com.h13.cardgame.jupiter.exceptions.LevelIsTopException
     *
     */
    public LevelCO isLevelUp(CityCO city) throws LevelIsTopException {
        int level = city.getLevel();
        int toExp = city.getExp();
        LevelCO levelCO = get(level);
        if (levelCO.getExp() <= toExp) {
            if (levelCO.isMax())
                throw new LevelIsTopException("city=" + city + " level is top.");
            // 可以升级了
            int nextLevel = level + 1;
            LevelCO nextLevelCO = get(nextLevel);
            return nextLevelCO;
        } else
            return null;
    }


    /**
     * 为city添加经验，如果足够升级的话，会自动更新city的等级在数据库中，如果返回false表示没有升级等级，
     * 如果返回true表示已经升级了
     * @param city
     * @param addExp
     * @return
     * @throws LevelIsTopException
     */
    public boolean addLevelExp(CityCO city, int addExp) throws LevelIsTopException {
        city.setExp(city.getExp() + addExp);
        if (isLevelUp(city) != null) {
            int newlevel = city.getLevel() + 1;
            // 等级+1
            city.setLevel(newlevel);
            // 更新等级
            cityDAO.updateLevel(city);
            return true;
        }
        return false;
    }

}
