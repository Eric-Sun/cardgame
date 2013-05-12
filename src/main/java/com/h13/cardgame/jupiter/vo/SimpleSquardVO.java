package com.h13.cardgame.jupiter.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午6:41
 * To change this template use File | Settings | File Templates.
 */
public class SimpleSquardVO {
    private List<CardVO> cardList ;

    @Override
    public String toString() {
        return "SimpleSquardVO{" +
                "cardList=" + cardList +
                '}';
    }

    public List<CardVO> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardVO> cardList) {
        this.cardList = cardList;
    }
}
