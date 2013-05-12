package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.TaskCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.dao.QueueMessageDAO;
import com.h13.cardgame.jupiter.utils.QueueUtils;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.queue.SchedulerQueue;
import com.h13.cardgame.scheduler.SchedulerType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-25
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SchedulerService {
    private static Log LOG = LogFactory.getLog(SchedulerService.class);
    @Autowired
    SchedulerQueue queue;

    @Autowired
    QueueMessageDAO queueMessageDAO;

    @Autowired
    ConfigService configService;

    /**
     * 创建一个定时任务
     *
     * @param cid
     * @param schedulerType
     * @param attachment
     * @param interval      多久之后触发这个事件
     */
    private void add(long cid, SchedulerType schedulerType, Object attachment, long interval) {
        long startTime = System.currentTimeMillis();
        SchedulerMessage detail = new SchedulerMessage(cid, QueueUtils.getMessageId(cid), schedulerType,
                startTime, interval,
                attachment);
        // 添加到数据库中，用来查询
        queueMessageDAO.add(detail.getJobId(), cid, startTime, attachment, interval, schedulerType);
        // 添加到
        queue.push(detail);
        LOG.info("push to queue. message=" + detail);
    }


    /**
     * 获取是否已经在队列中有计时器的任务，比如恢复能量
     *
     * @param cid
     * @param schedulerType
     * @return
     */
    private SchedulerMessage checkAndGetMessage(long cid, SchedulerType schedulerType) {
        List<SchedulerMessage> messageList = queueMessageDAO.getJob(cid, schedulerType);
        if (messageList == null)
            return null;
        else
            return messageList.get(0);

    }

    /**
     * 检测一种定时器任务是否存在
     * <p>
     * true--->存在
     * </p>
     * <p>
     * false--->不存在
     * </p>
     *
     * @param cid
     * @param schedulerType
     * @return
     */
    private boolean checkMessage(long cid, SchedulerType schedulerType) {
        int count = queueMessageDAO.checkJob(cid, schedulerType);
        if (count == 0)
            return false;
        else
            return true;
    }


    /**
     * 添加一个任务冷却的定时任务
     *
     * @param captain
     * @param task
     */
    public void addTaskCooldownJob(CityCO captain, TaskCO task) {
        add(captain.getId(), SchedulerType.TASK_COOLDOWN_JOB, task, task.getCooldown());
    }


    public void addEnergyUpJob(CityCO captain) {
        add(captain.getId(), SchedulerType.ENERGY_JOB, null,
                new Long(configService.get(Configuration.Scheduler.ENERGY_UP_S)));
    }

    public void checkAndAddEnergyUpJob(CityCO captain) {
        if (!checkMessage(captain.getId(), SchedulerType.ENERGY_JOB)) {
            addEnergyUpJob(captain);
        }

    }
}
