package com.h13.cardgame.cache.co;

/**
 * 卡的等级的表
 * User: sunbo
 * Date: 13-4-13
 * Time: 下午11:32
 * To change this template use File | Settings | File Templates.
 */
public class CardLevelCO {
    private int id;
    private int level;
    private int exp;

    @Override
    public String toString() {
        return "CardLevelCO{" +
                "id=" + id +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
