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
    private boolean isMax;
    private int sStorageSize;
    private int eStorageSize;
    private int troopSize;
    private int captainStorageSize;

    public int getCaptainStorageSize() {
        return captainStorageSize;
    }

    public void setCaptainStorageSize(int captainStorageSize) {
        this.captainStorageSize = captainStorageSize;
    }

    public int getTroopSize() {
        return troopSize;
    }

    public void setTroopSize(int troopSize) {
        this.troopSize = troopSize;
    }

    public int getSStorageSize() {
        return sStorageSize;
    }

    public void setSStorageSize(int sStorageSize) {
        this.sStorageSize = sStorageSize;
    }

    public int getEStorageSize() {
        return eStorageSize;
    }

    public void setEStorageSize(int eStorageSize) {
        this.eStorageSize = eStorageSize;
    }

    public boolean isMax() {
        return isMax;
    }

    public void setMax(boolean max) {
        isMax = max;
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
