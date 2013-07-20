package com.h13.cardgame.jupiter.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-19
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class UnitsCardVO implements Comparable {
    private long id;
    private String name;
    private String icon;
    private int attackMax;
    private int attackMin;
    private int defenceMax;
    private int defenceMin;
    private long eCardId;
    private int silver;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public long geteCardId() {
        return eCardId;
    }

    public void seteCardId(long eCardId) {
        this.eCardId = eCardId;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    @Override
    public int compareTo(Object o) {
        UnitsCardVO vo = (UnitsCardVO) o;
        if (vo.getId() < this.getId())
            return 1;
        else
            return -1;
    }
}
