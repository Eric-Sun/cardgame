package com.h13.cardgame.core.vo;

import com.h13.cardgame.cache.co.ConditionCO;
import com.h13.cardgame.cache.co.TaskResultCO;

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
    private TaskResultCO result;
    private ConditionCO condition;
    private int sum;
    private int count;
    private long lastCommit;

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

    public TaskResultCO getResult() {
        return result;
    }

    public void setResult(TaskResultCO result) {
        this.result = result;
    }

    public ConditionCO getCondition() {
        return condition;
    }

    public void setCondition(ConditionCO condition) {
        this.condition = condition;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}