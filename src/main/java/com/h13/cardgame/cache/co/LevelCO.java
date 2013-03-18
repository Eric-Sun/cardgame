package com.h13.cardgame.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-11
 * Time: 下午6:51
 * To change this template use File | Settings | File Templates.
 */
public class LevelCO {
    private int id;
    private int level;
    private int exp;
    private int energy;

    @Override
    public String toString() {
        return "LevelCO{" +
                "id=" + id +
                ", level=" + level +
                ", exp=" + exp +
                ", energy=" + energy +
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
