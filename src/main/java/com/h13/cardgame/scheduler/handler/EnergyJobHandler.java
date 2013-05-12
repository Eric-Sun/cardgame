package com.h13.cardgame.scheduler.handler;

import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.jupiter.service.CityService;
import com.h13.cardgame.jupiter.service.ConfigService;
import com.h13.cardgame.scheduler.SchedulerHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class EnergyJobHandler implements SchedulerHandler {
    private static Log LOG = LogFactory.getLog(EnergyJobHandler.class);

    @Autowired
    CityService captainService;

    @Autowired
    ConfigService confService;

    @Override
    public boolean doHandle(SchedulerMessage detail) {
        try {
            captainService.addEnergy(detail.getCid(), 1);
        } catch (UserNotExistsException e) {
            LOG.error("scheduler error. detail=" + detail, e);
        }
        return true;
    }
}
