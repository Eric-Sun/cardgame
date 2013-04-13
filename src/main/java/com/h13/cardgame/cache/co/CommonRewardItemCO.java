package com.h13.cardgame.cache.co;

/**
 * 金币等，非卡牌项的配置
 * User: sunbo
 * Date: 13-4-6
 * Time: 下午8:40
 * To change this template use File | Settings | File Templates.
 */
public class CommonRewardItemCO {
    private boolean drop;  // 是否掉落
    private boolean random; // 是否是随机，如果不是随机的话，min和max应该一样
    private int min;      //  随机掉落的最小值
    private int max;      //  随机掉落的最大值

    @Override
    public String toString() {
        return "TaskRewardItemCO{" +
                "drop=" + drop +
                ", random=" + random +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public boolean isDrop() {
        return drop;
    }

    public void setDrop(boolean drop) {
        this.drop = drop;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
