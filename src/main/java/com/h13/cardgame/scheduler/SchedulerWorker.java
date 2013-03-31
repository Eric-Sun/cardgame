package com.h13.cardgame.scheduler;

import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.queue.SchedulerQueue;
import com.h13.cardgame.scheduler.exception.SchedulerFatalException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;


/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-25
 * Time: 下午6:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SchedulerWorker {
    private static Log LOG = LogFactory.getLog(SchedulerWorker.class);
    Scheduler scheduler = null;

    @Autowired
    SchedulerQueue queue;

    public void start() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        LOG.info("scheduler started.");
    }


    public void process() throws SchedulerFatalException, SchedulerException {
        SchedulerMessage message = queue.peek();
        if (message == null)
            return;
        if (scheduler == null)
            throw new SchedulerFatalException("scheduler didn't started.");
        JobDataMap map = new JobDataMap();
        map.put("message", message);
        JobDetail job = newJob(CardgameSchedulerJob.class).
                withIdentity(message.getJobId(), "gn")
                .usingJobData(map).build();
        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger_" + message.getJobId(), "gn")
                .forJob(job).startAt(
                        futureDate(new Long(message.getIntervalS()).intValue(), IntervalUnit.SECOND)
                ).build();
        scheduler.scheduleJob(job, trigger);
        LOG.info("add job . message=" + message);
    }


    public void stop() throws SchedulerException {
        if (scheduler != null)
            scheduler.shutdown();
        LOG.info("scheduler stoped.");
    }


}
