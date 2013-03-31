package com.h13.cardgame.core.vo;

/**
 * 用来返回上次任务完成请求之后，获得的奖励
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午10:48
 * To change this template use File | Settings | File Templates.
 */
public class TaskAwardVO {
    private int type;
    private CardVO card;
    private int silver;
    private int exp;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CardVO getCard() {
        return card;
    }

    public void setCard(CardVO card) {
        this.card = card;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
