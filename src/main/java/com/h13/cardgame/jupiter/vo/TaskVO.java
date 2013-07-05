package com.h13.cardgame.jupiter.vo;

import com.h13.cardgame.cache.co.ConditionCO;

/**
 * 任务对象，用来显示，当前有几个任务，和每个任务的完成情况
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class TaskVO {

    private long id;
    private String name;
    private String desc;
    private TaskRewardVO reward;
    private ConditionCO condition;
    private int curCount;
    private int count;
    private long lastCommit;
    private long cooldown;
    private long lastTimeStamp;

    public long getLastTimeStamp() {
        return lastTimeStamp;
    }

    public void setLastTimeStamp(long lastTimeStamp) {
        this.lastTimeStamp = lastTimeStamp;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public long getLastCommit() {
        return lastCommit;
    }

    public void setLastCommit(long lastCommit) {
        this.lastCommit = lastCommit;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public TaskRewardVO getReward() {
        return reward;
    }

    public void setReward(TaskRewardVO reward) {
        this.reward = reward;
    }

    public ConditionCO getCondition() {
        return condition;
    }

    public void setCondition(ConditionCO condition) {
        this.condition = condition;
    }

    public int getCurCount() {
        return curCount;
    }

    public void setCurCount(int curCount) {
        this.curCount = curCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
