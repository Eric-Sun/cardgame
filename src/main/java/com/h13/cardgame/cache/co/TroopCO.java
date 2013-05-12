package com.h13.cardgame.cache.co;

import java.util.List;

/**
 * 城中军队
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
public class TroopCO {
    private long id;
    private long cityId;
    private String name;
    private List<Long> members;
    private int max;
    private int attackMax;
    private int attackMin;
    private int defenceMax;
    private int defenceMin;

    @Override
    public String toString() {
        return "SquardCO{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", max=" + max +
                ", attackMax=" + attackMax +
                ", attackMin=" + attackMin +
                ", defenceMax=" + defenceMax +
                ", defenceMin=" + defenceMin +
                '}';
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
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

    public List<Long> getMembers() {
        return members;
    }

    public void setMembers(List<Long> members) {
        this.members = members;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
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

    public int getDefenceMax() {
        return defenceMax;
    }

    public void setDefenceMax(int defenceMax) {
        this.defenceMax = defenceMax;
    }

    public int getDefenceMin() {
        return defenceMin;
    }

    public void setDefenceMin(int defenceMin) {
        this.defenceMin = defenceMin;
    }
}

