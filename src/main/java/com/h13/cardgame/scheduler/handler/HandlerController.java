package com.h13.cardgame.scheduler.handler;

import com.h13.cardgame.cache.co.JobDetailCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-7
 * Time: 下午7:09
 * To change this template use File | Settings | File Templates.
 */
@Service
public class HandlerController {

    @Autowired
    EnergyJobHandler energyHandler;

    public boolean dispatch(JobDetailCO detail) {
        switch (detail.getJobType()) {
            case ENERGY_JOB:
                return energyHandler.doHandle(detail);
        }
        return false;
    }
}
