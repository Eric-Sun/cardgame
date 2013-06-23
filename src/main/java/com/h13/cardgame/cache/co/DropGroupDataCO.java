package com.h13.cardgame.cache.co;

import java.util.List;

/**
 * 掉落组的配置
 * User: sunbo
 * Date: 13-4-6
 * Time: 下午8:43
 * To change this template use File | Settings | File Templates.
 */
public class DropGroupDataCO {

    private List<CardRewardItemCO> cardDropList;
    private CommonRewardItemCO exp;
    private CommonRewardItemCO silver;
    private int weightSum;

    public List<CardRewardItemCO> getCardDropList() {
        return cardDropList;
    }

    public void setCardDropList(List<CardRewardItemCO> cardDropList) {
        this.cardDropList = cardDropList;
    }

    public CommonRewardItemCO getExp() {
        return exp;
    }

    public void setExp(CommonRewardItemCO exp) {
        this.exp = exp;
    }

    public CommonRewardItemCO getSilver() {
        return silver;
    }

    public void setSilver(CommonRewardItemCO silver) {
        this.silver = silver;
    }

    public int getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }


    @Override
    public String toString() {
        return "DropGroupCO{" +
                ", cardDropList=" + cardDropList +
                ", exp=" + exp +
                ", silver=" + silver +
                ", weightSum=" + weightSum +
                '}';
    }
}
