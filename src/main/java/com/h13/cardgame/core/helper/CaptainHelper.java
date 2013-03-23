package com.h13.cardgame.core.helper;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.service.CaptainCache;
import com.h13.cardgame.core.dao.CaptainDAO;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.utils.CardgameObjectUtil;
import com.h13.cardgame.scheduler.JobType;
import com.h13.cardgame.scheduler.SchedulerService;
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
    @Autowired
    CaptainCache captainCache;
    @Autowired
    LevelHelper levelService;
    @Autowired
    CaptainDAO captainDAO;
    @Autowired
    SchedulerService schedulerService;
    @Autowired
    LevelHelper levelHelper;

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
        schedulerService.attemptTrigger(cid, JobType.ENERGY_JOB);
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
        LevelCO level = levelService.get(captain.getLevel());
        if (level.getEnergy() >= captain.getEnergy() + value) {
            // full
            captain.setEnergy(level.getEnergy());
        } else {
            captain.setEnergy(captain.getEnergy() + value);
            // add new scheduler
            schedulerService.checkAndAddJob(captain.getId(), JobType.ENERGY_JOB, null);
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
        captain.setEnergy(captain.getEnergy() - value);
        captainDAO.updateEnergy(captain.getId(), captain.getEnergy());
        schedulerService.checkAndAddJob(captain.getId(), JobType.ENERGY_JOB, null);
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
}
