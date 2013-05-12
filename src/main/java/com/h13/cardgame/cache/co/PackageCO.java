package com.h13.cardgame.cache.co;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 每一个captain拥有的包裹，其中包含卡牌
 * User: sunbo
 * Date: 13-4-13
 * Time: 下午10:25
 * To change this template use File | Settings | File Templates.
 */
public class PackageCO {

    private long id;
    private long cityId;
    private int max;
    private int current;
    // cardId 和 captainCardId 的关联
    private Map<Long, List<Long>> cardData = new HashMap<Long, List<Long>>();

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Map<Long, List<Long>> getCardData() {
        return cardData;
    }

    public void setCardData(Map<Long, List<Long>> cardData) {
        this.cardData = cardData;
    }

    @Override
    public String toString() {
        return "PackageCO{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", max=" + max +
                ", current=" + current +
                ", cardData=" + cardData +
                '}';
    }
}

