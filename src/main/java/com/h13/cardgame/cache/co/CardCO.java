package com.h13.cardgame.cache.co;

import com.h13.cardgame.core.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */
public class CardCO {
    private long id;
    private String name;
    private String img;
    private Constants.CardType cardType;
    private int attackMin;
    private int attackMax;
    private int defenceMin;
    private int defenceMax;

    public Constants.CardType getCardType() {
        return cardType;
    }

    public void setCardType(Constants.CardType cardType) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CardCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
