package com.h13.cardgame.cache.co;

/**
 * captain 拥有的卡的对象
 * User: sunbo
 * Date: 13-4-13
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
public class CityCardCO {
    private long id;
    private long cardId;
    private String name;
    private String img;
    private int level;
    private int attackMin;
    private int attackMax;
    private int defenceMin;
    private int defenceMax;


    @Override
    public String toString() {
        return "CaptainCardCO{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", level=" + level +
                ", attackMin=" + attackMin +
                ", attackMax=" + attackMax +
                ", defenceMin=" + defenceMin +
                ", defenceMax=" + defenceMax +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
