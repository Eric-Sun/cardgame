package com.h13.cardgame.core.helper;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.service.CaptainCache;
import com.h13.cardgame.core.dao.CaptainDAO;
import com.h13.cardgame.core.exceptions.LevelIsTopException;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.service.SchedulerService;
import com.h13.cardgame.core.utils.CardgameObjectUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用来提供关于captain的所有院子的操作
 * User: sunbo
 * Date: 13-3-20
 * Time: 下午6:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptainHelper {
    private static Log LOG = LogFactory.getLog(CaptainHelper.class);
    @Autowired
    CaptainCache captainCache;
    @Autowired
    LevelHelper levelHelper;
    @Autowired
    CaptainDAO captainDAO;
    @Autowired
    TaskHelper taskHelper;
    @Autowired
    SchedulerService schedulerService;

    @Autowired
    UserHelper userHelper;

    /**
     * 从缓存中获取captain
     *
     * @param cid
     * @return
     * @throws ParameterIllegalException
     */
    public CaptainCO get(long cid) throws ParameterIllegalException {
        CaptainCO captain = captainCache.get(cid);
        if (captain == null) {
            // load data from db
            captain = captainDAO.get(cid);
            captainCache.put(captain);
        }
        return captain;
    }


    /**
     * 给一个captain添加energy,如果energy没有满的话，如果没有能量事件，
     * 添加一个定时任务来添加energy
     *
     * @param captain
     * @param value
     * @throws ParameterIllegalException
     */
    public void addEnergy(CaptainCO captain, int value) throws ParameterIllegalException {
        //获得当前这个人物的满级的energy
        LevelCO level = levelHelper.get(captain.getLevel());
        if (level.getEnergy() <= captain.getEnergy() + value) {
            // full
            captain.setEnergy(level.getEnergy());
        } else {
            captain.setEnergy(captain.getEnergy() + value);
            // add new scheduler
            schedulerService.checkAndAddEnergyUpJob(captain);
        }
        // update db
        captainDAO.updateEnergy(captain.getId(), captain.getEnergy());
    }

    /**
     * 为一个captain减少能量，检测如果有能量事件的话，不做操作，如果没有的话，
     * 添加一个能量事件
     *
     * @param captain
     * @param value
     * @throws ParameterIllegalException
     */
    public void subEnergy(CaptainCO captain, int value) throws ParameterIllegalException {
        if (captain.getEnergy() - value < 0)
            throw new ParameterIllegalException("energy  is not enough . need=" + value + " have=" + captain.getEnergy());
        schedulerService.checkAndAddEnergyUpJob(captain);
        captainDAO.updateEnergy(captain.getId(), captain.getEnergy() - value);
        captain.setEnergy(captain.getEnergy() - value);
    }

    /**
     * 把captain对象，更新到缓存中
     *
     * @param captain
     */
    public void cacheCaptain(CaptainCO captain) {
        captainCache.put(captain);
    }

    /**
     * 创建一个captain
     *
     * @param uid
     * @param name
     * @return
     */
    public CaptainCO create(long uid, String name) throws ParameterIllegalException {
        if (!userHelper.check(uid))
            throw new ParameterIllegalException("user not existed . uid=" + uid);
        CaptainCO captain = CardgameObjectUtil.newCaptain(uid, name);
        captain.setEnergy(levelHelper.get(1).getEnergy());
        long cid = captainDAO.create(captain);
        captain.setId(cid);
        return captain;
    }


    public void updateReward(long id, int exp, int silver) {
        captainDAO.updateReward(id, exp, silver);
    }


    /**
     * 当可以升级的时候返回下一个等级的信息，如果已经满级了，抛出异常
     * 如果不可以升级返回null
     * @param captain
     * @param level
     * @param toExp
     * @return
     * @throws LevelIsTopException
     */
    public LevelCO isLevelUp(CaptainCO captain, int level, int toExp) throws LevelIsTopException {
        LevelCO levelCO = levelHelper.get(level);
        if (levelCO.getExp() <= toExp) {
            if (levelCO.isMax())
                throw new LevelIsTopException("captain=" + captain + " level is top.");
            // 可以升级了
            int nextLevel = level + 1;
            LevelCO nextLevelCO = levelHelper.get(nextLevel);
            return nextLevelCO;
        }else
            return null;
    }
}
