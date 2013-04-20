package com.h13.cardgame.core.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-18
 * Time: 上午1:21
 * To change this template use File | Settings | File Templates.
 */
public class CombatAttributesVO {
    private int attackMin;
    private int attackMax;
    private int defenceMin;
    private int defenceMax;


    @Override
    public String toString() {
        return "CombatAttributesVO{" +
                "attackMin=" + attackMin +
                ", attackMax=" + attackMax +
                ", defenceMin=" + defenceMin +
                ", defenceMax=" + defenceMax +
                '}';
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }

    public int getDefenceMin() {
        return defenceMin;
    }

    public void setDefenceMin(int defenceMin) {
        this.defenceMin = defenceMin;
    }

    public int getDefenceMax() {
        return defenceMax;
    }

    public void setDefenceMax(int defenceMax) {
        this.defenceMax = defenceMax;
    }
}
