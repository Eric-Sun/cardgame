package com.h13.cardgame.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */
public class CaptainCO {
    private long id;
    private long userId;
    private String name;
    private int level;
    private int energy;
    private int exp;
    private int gold;
    private int silver;
    private CaptainTaskCO taskInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public CaptainTaskCO getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(CaptainTaskCO taskInfo) {
        this.taskInfo = taskInfo;
    }
}
