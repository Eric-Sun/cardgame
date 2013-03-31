package com.h13.cardgame.cache.co;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:09
 * To change this template use File | Settings | File Templates.
 */
public class TaskCO {

    /**
     * 任务的id
     */
    private long id;
    /**
     * 任务的名称
     */
    private String name;
    private String description;
    /**
     * 任务需要完成的次数
     */
    private int count;
    /**
     * 任务的奖励
     */
    private TaskResultCO result;

    /**
     * 任务本次完成之后下一次完成需要的时间（秒）
     */
    private long cooldown;
    private long taskGroupId;
    private ConditionCO condition;
    private int isLast;

    public int getLast() {
        return isLast;
    }

    public void setLast(int last) {
        isLast = last;
    }

    public ConditionCO getCondition() {
        return condition;
    }

    public void setCondition(ConditionCO condition) {
        this.condition = condition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TaskResultCO getResult() {
        return result;
    }

    public void setResult(TaskResultCO result) {
        this.result = result;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public long getTaskGroupId() {
        return taskGroupId;
    }

    public void setTaskGroupId(long taskGroupId) {
        this.taskGroupId = taskGroupId;
    }

    @Override
    public String toString() {
        return "TaskCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", result=" + result +
                ", cooldown=" + cooldown +
                ", taskGroupId=" + taskGroupId +
                '}';
    }
}
