package com.h13.cardgame.core.service;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.cache.service.CaptainCache;
import com.h13.cardgame.core.dao.CaptainDAO;
import com.h13.cardgame.scheduler.JobType;
import com.h13.cardgame.scheduler.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */

@Service
public class CaptainService {

    @Autowired
    CaptainCache captainCache;

    @Autowired
    LevelService levelService;

    @Autowired
    CaptainDAO captainDAO;

    @Autowired
    SchedulerService schedulerService;

    public CaptainCO get(long cid) {
        schedulerService.attemptTrigger(cid, JobType.ENERGY_JOB);
        CaptainCO captain = captainCache.get(cid);
        if (captain == null) {
            // load data from db
            captain = captainDAO.get(cid);
            captainCache.put(captain);
        }
        return captain;
    }


    public void addEnergy(long cid, int value) {
        //获得当前这个人物的满级的energy
        CaptainCO captain = get(cid);
        LevelCO level = levelService.get(captain.getLevel());

        if (level.getEnergy() >= captain.getEnergy() + value) {
            // full
            captain.setEnergy(level.getEnergy());
        } else {
            captain.setEnergy(captain.getEnergy() + value);
            // add new scheduler
            schedulerService.addJob(cid, JobType.ENERGY_JOB, null);
        }

        // update cache
        captainCache.put(captain);
        // update db
        captainDAO.updateEnergy(captain.getId(), captain.getEnergy());
    }
}
