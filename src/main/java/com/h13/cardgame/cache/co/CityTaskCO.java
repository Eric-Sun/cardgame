package com.h13.cardgame.cache.co;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:18
 * To change this template use File | Settings | File Templates.
 */
public class CityTaskCO {
    private long taskGroupId = 1;
    private Map<Long, CityPerTaskCO> taskMap= new HashMap<Long, CityPerTaskCO>();

    public long getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(long taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    public Map<Long, CityPerTaskCO> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<Long, CityPerTaskCO> taskMap) {
        this.taskMap = taskMap;
    }
}
