package com.h13.cardgame.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
public class TaskResultCO {
    private int exp;
    private int gold;
    private int silver;

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

    @Override
    public String toString() {
        return "TaskResultCO{" +
                "exp=" + exp +
                ", gold=" + gold +
                ", silver=" + silver +
                '}';
    }
}
