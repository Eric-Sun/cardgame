package com.h13.cardgame.scheduler;

import com.h13.cardgame.core.utils.WebApplicationContentHolder;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.scheduler.handler.HandlerController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-26
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class CardgameSchedulerJob implements Job {
    private static Log LOG = LogFactory.getLog(CardgameSchedulerJob.class);
    private static String KEY = "message";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchedulerMessage message = (SchedulerMessage) jobExecutionContext.getJobDetail().getJobDataMap().get(KEY);
        LOG.info("job started. jobId=" + message.getJobId() + " message=" + message);
        HandlerController controller = WebApplicationContentHolder.getApplicationContext().getBean(HandlerController.class);
        controller.dispatch(message);
        LOG.info("job stoped. jobId=" + message.getJobId() + " message=" + message);
    }
}
