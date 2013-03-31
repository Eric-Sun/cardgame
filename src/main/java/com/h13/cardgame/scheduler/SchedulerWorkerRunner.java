package com.h13.cardgame.scheduler;

import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.scheduler.exception.SchedulerFatalException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-26
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerWorkerRunner implements Runnable {
    private static Log LOG = LogFactory.getLog(SchedulerWorkerRunner.class);

    private SchedulerWorker worker;

    public SchedulerWorkerRunner(SchedulerWorker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        try {
            worker.start();
        } catch (SchedulerException e) {
            LOG.error("scheduler start error.", e);
        }
        while (true) {
            try {
                worker.process();
                Thread.sleep(10L);
            } catch (Exception e) {
                LOG.error("scheduler error", e);
            }
        }
    }
}
