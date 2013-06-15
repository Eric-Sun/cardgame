package com.h13.cardgame.cache.co;

import com.h13.cardgame.scheduler.SchedulerType;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-6-9
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class SchedulerCO {
    private long ts;
    private SchedulerType type;
    private long uid;
    /**
     * 可能是taskId，可能是其他的，跟type关联的
     */
    private long actionObjectId;

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

    public SchedulerType getType() {
        return type;
    }

    public void setType(SchedulerType type) {
        this.type = type;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}
