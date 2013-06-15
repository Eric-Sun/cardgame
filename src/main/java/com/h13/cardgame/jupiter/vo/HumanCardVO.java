package com.h13.cardgame.jupiter.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-5-29
 * Time: 上午12:16
 * To change this template use File | Settings | File Templates.
 */
public class HumanCardVO {
    private long id;
    private String name;
    private String icon;
    private int attackMin;
    private int attackMax;
    private int defenceMin;
    private int defenceMax;
    private int maxSlot;
    private int curSlot;

    public int getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }

    public int getCurSlot() {
        return curSlot;
    }

    public void setCurSlot(int curSlot) {
        this.curSlot = curSlot;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
