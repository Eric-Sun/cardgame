package com.h13.cardgame.cache.co;

import com.h13.cardgame.jupiter.CardType;

/**
 * captain 拥有的卡的对象
 * User: sunbo
 * Date: 13-4-13
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
public class CityCardCO {
    private long id;
    private long cityId;
    private long cardId;
    private String name;
    private String icon;
    private int attackMin;
    private int attackMax;
    private int defenceMin;
    private int defenceMax;
    private int baseAttackMin;
    private int baseAttackMax;
    private int baseDefenceMax;
    private int baseDefenceMin;
    private int curSlot;
    private int maxSlot;
    private int status;
    private CardType cardType;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "CityCardCO{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", attackMin=" + attackMin +
                ", attackMax=" + attackMax +
                ", defenceMin=" + defenceMin +
                ", defenceMax=" + defenceMax +
                ", baseAttackMin=" + baseAttackMin +
                ", baseAttackMax=" + baseAttackMax +
                ", baseDefenceMax=" + baseDefenceMax +
                ", baseDefenceMin=" + baseDefenceMin +
                ", curSlot=" + curSlot +
                ", cardType=" + cardType +
                '}';
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
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

    public int getBaseAttackMin() {
        return baseAttackMin;
    }

    public void setBaseAttackMin(int baseAttackMin) {
        this.baseAttackMin = baseAttackMin;
    }

    public int getBaseAttackMax() {
        return baseAttackMax;
    }

    public void setBaseAttackMax(int baseAttackMax) {
        this.baseAttackMax = baseAttackMax;
    }

    public int getBaseDefenceMax() {
        return baseDefenceMax;
    }

    public void setBaseDefenceMax(int baseDefenceMax) {
        this.baseDefenceMax = baseDefenceMax;
    }

    public int getBaseDefenceMin() {
        return baseDefenceMin;
    }

    public void setBaseDefenceMin(int baseDefenceMin) {
        this.baseDefenceMin = baseDefenceMin;
    }

    public int getCurSlot() {
        return curSlot;
    }

    public void setCurSlot(int curSlot) {
        this.curSlot = curSlot;
    }
}
