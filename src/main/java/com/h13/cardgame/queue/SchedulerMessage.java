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
    private long uid;
    private SchedulerType jobType;
    private long cid;
    private long startTime;
    private Object attachment;
    private long intervalS;
    private long actionObjectId;
    private int rerunTime = 0;

    public int getRerunTime() {
        return rerunTime;
    }

    public void setRerunTime(int rerunTime) {
        this.rerunTime = rerunTime;
    }

    public long getActionObjectId() {
        return actionObjectId;
    }

    public void setActionObjectId(long actionObjectId) {
        this.actionObjectId = actionObjectId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getIntervalS() {
        return intervalS;
    }

    public void setIntervalS(long intervalS) {
        this.intervalS = intervalS;
    }

    public SchedulerMessage() {
    }

    public SchedulerMessage(long uid, long cid, String jobId, SchedulerType jobType, long startTime, long intervalS, Object attachment,
                            long actionObjectId) {
        this.uid = uid;
        this.cid = cid;
        this.jobId = jobId;
        this.jobType = jobType;
        this.startTime = startTime;
        this.attachment = attachment;
        this.intervalS = intervalS;
        this.actionObjectId = actionObjectId;
    }

    @Override
    public String toString() {
        return "SchedulerMessage{" +
                "jobId='" + jobId + '\'' +
                ", uid=" + uid +
                ", jobType=" + jobType +
                ", cid=" + cid +
                ", startTime=" + startTime +
                ", attachment=" + attachment +
                ", intervalS=" + intervalS +
                ", actionObjectId=" + actionObjectId +
                ", rerunTime=" + rerunTime +
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
