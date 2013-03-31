package com.h13.cardgame.scheduler.handler;

import com.h13.cardgame.core.dao.QueueMessageDAO;
import com.h13.cardgame.queue.SchedulerMessage;
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
    @Autowired
    TaskCooldownJobHandler taskHandler;
    @Autowired
    QueueMessageDAO queueMessageDAO;

    public boolean dispatch(SchedulerMessage detail) {
        switch (detail.getJobType()) {
            case ENERGY_JOB:
                energyHandler.doHandle(detail);
                break;
            case TASK_COOLDOWN_JOB:
                taskHandler.doHandle(detail);
                break;
        }
        queueMessageDAO.delete(detail.getJobId());
        return true;
    }
}
