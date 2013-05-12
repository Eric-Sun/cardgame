package com.h13.cardgame.cache.co;

/**
 * 对于每一个任务的，city的完成情况，包括完成了几次，是否现在可以继续完成
 * User: sunbo
 * Date: 13-3-19
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
public class CityPerTaskCO {
    private int count;
    private boolean canBeDo;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCanBeDo() {
        return canBeDo;
    }

    public void setCanBeDo(boolean canBeDo) {
        this.canBeDo = canBeDo;
    }

    public CityPerTaskCO() {
    }

    public CityPerTaskCO(int count, boolean canBeDo) {
        this.count = count;
        this.canBeDo = canBeDo;
    }
}
