package com.h13.cardgame.jupiter.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-18
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class CombatResultVO {
    private CardVO card;
    private boolean result;

    public CardVO getCard() {
        return card;
    }

    public void setCard(CardVO card) {
        this.card = card;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
