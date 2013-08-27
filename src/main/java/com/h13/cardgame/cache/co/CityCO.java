package com.h13.cardgame.cache.co;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */
public class CityCO {
    private long id;
    private long userId;
    private String name;
    private int level;
    private int energy;
    private int exp;
    private int gold;
    private int silver;
    private CityTaskStatusCO taskStatus;
    private int barSize;
    private Map<String, String> cooldownStatus = new HashMap<String, String>();


    public Map<String, String> getCooldownStatus() {
        return cooldownStatus;
    }

    public int getBarSize() {
        return barSize;
    }

    public void setBarSize(int barSize) {
        this.barSize = barSize;
    }

    public void setCooldownStatus(Map<String, String> cooldownStatus) {
        this.cooldownStatus = cooldownStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public CityTaskStatusCO getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(CityTaskStatusCO taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "CityCO{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", energy=" + energy +
                ", exp=" + exp +
                ", gold=" + gold +
                ", silver=" + silver +
                ", taskStatus=" + taskStatus +
                ", barSize=" + barSize +
                ", cooldownStatus=" + cooldownStatus +
                '}';
    }
}
