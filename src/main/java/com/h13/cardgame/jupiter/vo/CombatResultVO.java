package com.h13.cardgame.jupiter.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-18
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class CombatResultVO {
    private CityCardVO card;
    private boolean result;

    public CityCardVO getCard() {
        return card;
    }

    public void setCard(CityCardVO card) {
        this.card = card;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
