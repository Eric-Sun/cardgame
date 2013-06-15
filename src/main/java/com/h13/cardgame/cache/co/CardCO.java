package com.h13.cardgame.cache.co;

import com.h13.cardgame.jupiter.CardType;
import com.h13.cardgame.jupiter.Constants;

/**
 * 数据库中的card对象
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午11:46
 */
public class CardCO {
    private long id;
    private String name;
    private String icon;
    private CardType cardType;
    private int attackMin = 0;
    private int attackMax = 0;
    private int defenceMin = 0;
    private int defenceMax = 0;
    private int randomSlotCount = 1;
    private String desc;

    public int getRandomSlotCount() {
        return randomSlotCount;
    }

    public void setRandomSlotCount(int randomSlotCount) {
        this.randomSlotCount = randomSlotCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
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

    @Override
    public String toString() {
        return "CardCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", cardType=" + cardType +
                ", attackMin=" + attackMin +
                ", attackMax=" + attackMax +
                ", defenceMin=" + defenceMin +
                ", defenceMax=" + defenceMax +
                ", randomSlotCount=" + randomSlotCount +
                ", desc='" + desc + '\'' +
                '}';
    }
}
