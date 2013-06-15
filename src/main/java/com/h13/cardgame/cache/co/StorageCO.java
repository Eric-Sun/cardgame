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
public class StorageCO {

    private long id;
    private long cityId;
    private int max;
    private int current;
    // cardId 和 captainCardId 的关联
    private Map<String, List<String>> cardData = new HashMap<String, List<String>>();

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

    public Map<String, List<String>> getCardData() {
        return cardData;
    }

    public void setCardData(Map<String, List<String>> cardData) {
        this.cardData = cardData;
    }

    @Override
    public String toString() {
        return "StorageCO{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", max=" + max +
                ", current=" + current +
                ", cardData=" + cardData +
                '}';
    }
}

