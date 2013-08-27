package com.h13.cardgame.jupiter.vo;

/**
 * 卡牌的信息
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午10:49
 * To change this template use File | Settings | File Templates.
 */
public class CityCardVO {

    public static byte NULL = 0;
    public static byte NORMAL = 1;
    private long id;
    private String name;
    private String icon;
    private long cardId;
    private int attackMin;
    private int attackMax;
    private int defenceMin;
    private int defenceMax;
    private byte flag;

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
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
}
