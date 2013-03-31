package com.h13.cardgame.queue;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-6
 * Time: 上午1:36
 * To change this template use File | Settings | File Templates.
 */

import com.h13.cardgame.scheduler.SchedulerType;

/**
 * 放在redis的scheduler队列当中的message
 */
public class SchedulerMessage {

    private String jobId;
    private SchedulerType jobType;
    private long cid;
    private long startTime;
    private Object attachment;
    private long intervalS;

    public long getIntervalS() {
        return intervalS;
    }

    public void setIntervalS(long intervalS) {
        this.intervalS = intervalS;
    }

    public SchedulerMessage(){}
    public SchedulerMessage(long cid, String jobId, SchedulerType jobType, long startTime, long intervalS, Object attachment) {
        this.cid = cid;
        this.jobId = jobId;
        this.jobType = jobType;
        this.startTime = startTime;
        this.attachment = attachment;
        this.intervalS = intervalS;
    }

    @Override
    public String toString() {
        return "SchedulerMessage{" +
                "jobId='" + jobId + '\'' +
                ", jobType=" + jobType +
                ", cid=" + cid +
                ", startTime=" + startTime +
                ", attachment=" + attachment +
                ", intervalS=" + intervalS +
                '}';
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public SchedulerType getJobType() {
        return jobType;
    }

    public void setJobType(SchedulerType jobType) {
        this.jobType = jobType;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }


}
