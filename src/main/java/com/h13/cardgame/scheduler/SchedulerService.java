package com.h13.cardgame.scheduler;

import com.h13.cardgame.cache.co.JobDetailCO;
import com.h13.cardgame.cache.service.JobCache;
import com.h13.cardgame.core.utils.JobUtils;
import com.h13.cardgame.scheduler.handler.HandlerController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-6
 * Time: 上午1:45
 * To change this template use File | Settings | File Templates.
 */


/**
 * 在每次操作之前检测一下是否有事件需要出发，并且更新相应的状态，返回相应的值
 */
@Service
public class SchedulerService {

    private static Log LOG = LogFactory.getLog(SchedulerService.class);

    @Autowired
    JobCache cache;

    @Autowired
    HandlerController controller;

    public void attemptTrigger(long cid) {
        Map<JobType, List<JobDetailCO>> jobMap = cache.get(cid);
        for (JobType jobType : jobMap.keySet()) {
            List<JobDetailCO> list = new ArrayList<JobDetailCO>();
            for (JobDetailCO detail : list) {
                controller.dispatch(detail);
            }
        }
    }


    public void attemptTrigger(long cid, JobType jobType) {
        List<JobDetailCO> list = cache.get(cid, jobType);
        for (JobDetailCO detail : list) {
            controller.dispatch(detail);
        }
    }

    public void addJob(long cid, JobType jobType, Object attachment) {
        String jobId = JobUtils.getJobId(cid);
        long startTs = System.currentTimeMillis();
        JobDetailCO detail = new JobDetailCO(cid, jobId, jobType, startTs, attachment);
        cache.put(cid, detail);
        LOG.info("Add job." + jobId + ":" + detail.toString());
    }

}
