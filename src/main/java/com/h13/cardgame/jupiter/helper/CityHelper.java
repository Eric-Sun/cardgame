package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.service.CityCache;
import com.h13.cardgame.jupiter.dao.CityDAO;
import com.h13.cardgame.jupiter.exceptions.CityExistsException;
import com.h13.cardgame.jupiter.exceptions.LevelIsTopException;
import com.h13.cardgame.jupiter.exceptions.ServerErrorException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.service.SchedulerService;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用来提供关于captain的所有院子的操作
 * User: sunbo
 * Date: 13-3-20
 * Time: 下午6:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CityHelper {
    @Autowired
    CityCache cityCache;
    @Autowired
    LevelHelper levelHelper;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    TaskHelper taskHelper;
    @Autowired
    SchedulerService schedulerService;

    @Autowired
    UserHelper userHelper;

    /**
     * 从缓存中获取city
     *
     * @param cid
     * @return
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public CityCO get(long cid) throws UserNotExistsException {
        CityCO city = cityCache.get(cid);
        if (city == null) {
            // load data from db
            city = cityDAO.get(cid);
            cityCache.put(city);
        }
        return city;
    }


    /**
     * 给一个city添加energy,如果energy没有满的话，如果没有能量事件，
     * 添加一个定时任务来添加energy
     *
     * @param city
     * @param value
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public void addEnergy(CityCO city, int value) throws UserNotExistsException {
        //获得当前这个人物的满级的energy
        LevelCO level = levelHelper.get(city.getLevel());
        if (level.getEnergy() <= city.getEnergy() + value) {
            // full
            city.setEnergy(level.getEnergy());
        } else {
            city.setEnergy(city.getEnergy() + value);
            // add new scheduler
            schedulerService.checkAndAddEnergyUpJob(city);
        }
        // update db
        cityDAO.updateEnergy(city.getId(), city.getEnergy());
    }

    /**
     * 为一个captain减少能量，检测如果有能量事件的话，不做操作，如果没有的话，
     * 添加一个能量事件
     *
     * @param captain
     * @param value
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public void subEnergy(CityCO captain, int value) throws UserNotExistsException {
        if (captain.getEnergy() - value < 0)
            throw new UserNotExistsException("energy is not enough . need=" + value + " have=" + captain.getEnergy());
        schedulerService.checkAndAddEnergyUpJob(captain);
        cityDAO.updateEnergy(captain.getId(), captain.getEnergy() - value);
        captain.setEnergy(captain.getEnergy() - value);
    }

    /**
     * 把city对象，更新到缓存中
     *
     * @param city
     */
    public void cache(CityCO city) {
        cityCache.put(city);
        LogWriter.debug(LogWriter.CITY, "cache city. city=" + city);
    }

    /**
     * 创建一个city
     *
     * @param uid
     * @param name
     * @return
     */
    public CityCO create(long uid, String name) throws UserNotExistsException, CityExistsException {
        if (!userHelper.check(uid))
            throw new UserNotExistsException("user not existed . uid=" + uid);
        if (checkCityExists(uid)) {
            throw new CityExistsException("city existsed. uid=" + uid);
        }
        CityCO city = new CityCO();
        city.setEnergy(levelHelper.get(1).getEnergy());
        city.setExp(0);
        city.setGold(0);
        city.setLevel(1);
        city.setSilver(0);
        city.setUserId(uid);
        city.setName(name);
        long cid = cityDAO.create(city);
        city.setId(cid);
        LogWriter.debug(LogWriter.CITY, " create city object. city=" + city);
        return city;
    }

    /**
     * 判断是否这个用户已经有城了
     * <p>
     *     如果返回true表示已经有城了
     * </p>
     * @param uid
     * @return
     */
    private boolean checkCityExists(long uid) {
        return cityDAO.checkCityExists(uid);

    }


    public void updateReward(long id, int exp, int silver) {
        cityDAO.updateReward(id, exp, silver);
    }


    /**
     * 当可以升级的时候返回下一个等级的信息，如果已经满级了，抛出异常
     * 如果不可以升级返回null
     *
     * @param captain
     * @param level
     * @param toExp
     * @return
     * @throws LevelIsTopException
     */
    public LevelCO isLevelUp(CityCO captain, int level, int toExp) throws LevelIsTopException {
        LevelCO levelCO = levelHelper.get(level);
        if (levelCO.getExp() <= toExp) {
            if (levelCO.isMax())
                throw new LevelIsTopException("captain=" + captain + " level is top.");
            // 可以升级了
            int nextLevel = level + 1;
            LevelCO nextLevelCO = levelHelper.get(nextLevel);
            return nextLevelCO;
        } else
            return null;
    }

    public List<Long> searchAttackTarget(long cid, int fromLevel, int toLevel, int pageNum, int pageSize) {
        List<Long> list = cityDAO.searchAttackTarget(cid, fromLevel, toLevel, pageNum, pageSize);
        return list;
    }
}
