package com.h13.cardgame.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-6
 * Time: 上午1:36
 * To change this template use File | Settings | File Templates.
 */

import com.h13.cardgame.scheduler.JobType;

/**
 * scheduler系统中，存储在缓存的每个调度任务的job的信息
 */
public class JobDetailCO {

    private String jobId;
    private JobType jobType;
    private long cid;

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    /**
     * job开始的时间戳
     */
    private long startTs;
    /**
     * 一些其他的相关参数，可以是json或者其他格式，通过handler去解析
     */
    private Object attachment;

    public JobDetailCO(long cid, String jobId, JobType jobType, long startTs, Object attachment) {
        this.cid = cid;
        this.jobId = jobId;
        this.jobType = jobType;
        this.startTs = startTs;
        this.attachment = attachment;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public long getStartTs() {
        return startTs;
    }

    public void setStartTs(long startTs) {
        this.startTs = startTs;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "CardgameJobDetail{" +
                "jobId='" + jobId + '\'' +
                ", jobType=" + jobType +
                ", startTs=" + startTs +
                ", attachment=" + attachment +
                '}';
    }
}
