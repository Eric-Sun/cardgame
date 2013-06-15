package com.h13.cardgame.jupiter.vo;

/**
 * 用于在任务列表中显示每个任务给的奖励是什么
 * User: sunbo
 * Date: 13-4-12
 * Time: 下午6:27
 */
public class TaskRewardVO {
    private int silver;
    private int exp;

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
