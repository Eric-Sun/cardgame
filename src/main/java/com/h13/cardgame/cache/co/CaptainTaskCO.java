package com.h13.cardgame.cache.co;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class CaptainTaskCO {
    private long taskGroupId;
    private Map<Long,Integer> taskMap;

    public long getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(long taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public Map<Long, Integer> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<Long, Integer> taskMap) {
        this.taskMap = taskMap;
    }
}
