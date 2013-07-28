package com.h13.cardgame.cache.co;

/**
 * 小队等级
 * User: sunbo
 * Date: 13-7-23
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class CaptainLevelCO {
    private int id;
    private String name;
    private float attackMinRate;
    private float attackMaxRate;
    private float defenceMinRate;
    private float defenceMaxRate;
    private int exp;

    @Override
    public String toString() {
        return "CaptainLevelCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attackMinRate=" + attackMinRate +
                ", attackMaxRate=" + attackMaxRate +
                ", defenceMinRate=" + defenceMinRate +
                ", defenceMaxRate=" + defenceMaxRate +
                ", exp=" + exp +
                '}';
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAttackMinRate() {
        return attackMinRate;
    }

    public void setAttackMinRate(float attackMinRate) {
        this.attackMinRate = attackMinRate;
    }

    public float getAttackMaxRate() {
        return attackMaxRate;
    }

    public void setAttackMaxRate(float attackMaxRate) {
        this.attackMaxRate = attackMaxRate;
    }

    public float getDefenceMinRate() {
        return defenceMinRate;
    }

    public void setDefenceMinRate(float defenceMinRate) {
        this.defenceMinRate = defenceMinRate;
    }

    public float getDefenceMaxRate() {
        return defenceMaxRate;
    }

    public void setDefenceMaxRate(float defenceMaxRate) {
        this.defenceMaxRate = defenceMaxRate;
    }
}
