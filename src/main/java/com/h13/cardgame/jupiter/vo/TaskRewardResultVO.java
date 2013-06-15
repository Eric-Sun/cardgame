package com.h13.cardgame.jupiter.vo;

/**
 * 用来返回上次任务完成请求之后，获得的奖励
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午10:48
 * To change this template use File | Settings | File Templates.
 */
public class TaskRewardResultVO {

    public static int NORMAL = 0;
    public static int LEVEL_UP = 1;

    private int cityStatus = NORMAL;
    private CityCardVO card;
    private int silver;
    private int exp;

    public int getCityStatus() {
        return cityStatus;
    }

    public void setCityStatus(int cityStatus) {
        this.cityStatus = cityStatus;
    }

    public CityCardVO getCard() {
        return card;
    }

    public void setCard(CityCardVO card) {
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

    @Override
    public String toString() {
        return "TaskRewardVO{" +
                "cityStatus=" + cityStatus +
                ", card=" + card +
                ", silver=" + silver +
                ", exp=" + exp +
                '}';
    }
}
