package com.h13.cardgame.scheduler;

import com.h13.cardgame.cache.co.JobDetailCO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-7
 * Time: 下午7:11
 * To change this template use File | Settings | File Templates.
 */
public interface JobHandler {

    public boolean doHandle(JobDetailCO detail);
}
