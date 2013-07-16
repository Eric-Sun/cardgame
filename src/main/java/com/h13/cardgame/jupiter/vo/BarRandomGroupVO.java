package com.h13.cardgame.jupiter.vo;

import com.h13.cardgame.cache.co.CardRewardItemCO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class BarRandomGroupVO {

    private int weightSum;
    private List<CardRewardItemCO> list = new LinkedList<CardRewardItemCO>();

    public int getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(int weightSum) {
        this.weightSum = weightSum;
    }

    public List<CardRewardItemCO> getList() {
        return list;
    }

    public void setList(List<CardRewardItemCO> list) {
        this.list = list;
    }
}
