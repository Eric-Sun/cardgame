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

    private boolean willDropCard ;
    private boolean willDropExp;
    private boolean willDropSilver;
    private List<CardRewardItemCO> cardDropList;
    private CommonRewardItemCO exp;
    private CommonRewardItemCO silver;
    private int weightSum;



    public boolean isWillDropCard() {
        return willDropCard;
    }

    public void setWillDropCard(boolean willDropCard) {
        this.willDropCard = willDropCard;
    }

    public boolean isWillDropExp() {
        return willDropExp;
    }

    public void setWillDropExp(boolean willDropExp) {
        this.willDropExp = willDropExp;
    }

    public boolean isWillDropSilver() {
        return willDropSilver;
    }

    public void setWillDropSilver(boolean willDropSilver) {
        this.willDropSilver = willDropSilver;
    }

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
                ", willDropCard=" + willDropCard +
                ", willDropExp=" + willDropExp +
                ", willDropSilver=" + willDropSilver +
                ", cardDropList=" + cardDropList +
                ", exp=" + exp +
                ", silver=" + silver +
                ", weightSum=" + weightSum +
                '}';
    }
}
