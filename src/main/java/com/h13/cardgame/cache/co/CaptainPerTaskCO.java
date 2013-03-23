package com.h13.cardgame.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-19
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
public class CaptainPerTaskCO {
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

    public CaptainPerTaskCO() {
    }

    public CaptainPerTaskCO(int count, boolean canBeDo) {
        this.count = count;
        this.canBeDo = canBeDo;
    }
}
