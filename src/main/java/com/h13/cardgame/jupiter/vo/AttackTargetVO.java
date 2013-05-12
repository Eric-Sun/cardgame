package com.h13.cardgame.jupiter.vo;

/**
 * 查询
 * User: sunbo
 * Date: 13-4-19
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class AttackTargetVO {
    private long cid;
    private int attackMax;
    private int attackMin;

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }
}
