package com.h13.cardgame.jupiter.vo;

import java.util.List;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午6:41
 * To change this template use File | Settings | File Templates.
 */
public class TroopVO {
    private long tid;
    private int attackMin;
    private int attackMax;
    private int defenceMin;
    private int defenceMax;
    private int curSlot;
    private int maxSlot;
    private List<CityCardVO> cardList = new ArrayList<CityCardVO>();

    public int getCurSlot() {
        return curSlot;
    }

    public void setCurSlot(int curSlot) {
        this.curSlot = curSlot;
    }

    public int getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
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

    public List<CityCardVO> getCardList() {
        return cardList;
    }

    public void setCardList(List<CityCardVO> cardList) {
        this.cardList = cardList;
    }

    @Override
    public String toString() {
        return "TroopVO{" +
                "tid=" + tid +
                ", attackMin=" + attackMin +
                ", attackMax=" + attackMax +
                ", defenceMin=" + defenceMin +
                ", defenceMax=" + defenceMax +
                ", curSlot=" + curSlot +
                ", maxSlot=" + maxSlot +
                ", cardList=" + cardList +
                '}';
    }
}
