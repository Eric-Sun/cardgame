package com.h13.cardgame.scheduler.handler;

import com.h13.cardgame.cache.co.JobDetailCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.core.service.CaptainService;
import com.h13.cardgame.core.service.ConfigService;
import com.h13.cardgame.scheduler.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-7
 * Time: 下午7:10
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EnergyJobHandler implements JobHandler {

    @Autowired
    CaptainService captainService;

    @Autowired
    ConfigService confService;

    @Override
    public boolean doHandle(JobDetailCO detail) {
        long currentMs = System.currentTimeMillis();
        long detailMs = detail.getStartTs();
        long conf = new Long(confService.get(Configuration.Scheduler.ENERGY_UP_MS));
        int value = new Long((currentMs - detailMs) / conf).intValue();
        if (value == 0) {
            return true;
        } else {
            captainService.addEnergy(detail.getCid(), value);
        }
        return true;
    }
}
