package com.h13.cardgame.scheduler;

import com.h13.cardgame.jupiter.utils.WebApplicationContentHolder;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.scheduler.handler.HandlerController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

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
//        LOG.info("job started. jobId=" + message.getJobId() + " message=" + message);
        HandlerController controller = WebApplicationContentHolder.getApplicationContext().getBean(HandlerController.class);
        boolean flag = controller.dispatch(message);
        if (!flag)
            rerun(jobExecutionContext, message);
        else {
//            LOG.info("job stoped. jobId=" + message.getJobId() + " message=" + message);
        }
    }

    private void rerun(JobExecutionContext context, SchedulerMessage message) {
        if (message.getRerunTime() == 3) {
            LOG.error("job rerun time =3. discard up. message=" + message);
        }
        message.setRerunTime(message.getRerunTime() + 1);
        JobDataMap map = new JobDataMap();
        map.put("message", message);
        JobDetail job = newJob(CardgameSchedulerJob.class).
                withIdentity(message.getJobId(), "gn")
                .usingJobData(map).build();
        SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger_" + message.getJobId(), "gn")
                .forJob(job).startAt(
                        futureDate(new Long(message.getIntervalS()).intValue(), DateBuilder.IntervalUnit.SECOND)
                ).build();
        try {
            context.getScheduler().scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error("return job error. message=" + message, e);
        }
        LOG.info("rerun job .rereun time = " + message.getRerunTime() + ". message=" + message);
    }


}
