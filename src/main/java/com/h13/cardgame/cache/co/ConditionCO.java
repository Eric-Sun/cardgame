package com.h13.cardgame.cache.co;

/**
 * 任务完成的时候需要的条件，如果为0的话，任务不需要该条件
 * User: sunbo
 * Date: 13-3-19
 * Time: 下午6:58
 */
public class ConditionCO {
    private Integer energy = 0;
    private Integer silver = 0;
    private Integer gold = 0;

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
