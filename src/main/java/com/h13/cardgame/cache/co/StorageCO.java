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
    private int sMax;
    private int sCurrent;
    // cardId 和 captainCardId 的关联
    private Map<String, List<String>> sCardData = new HashMap<String, List<String>>();
    private int eMax;
    private int eCurrent;
    private Map<String, String> eCardData = new HashMap<String, String>();

    @Override
    public String toString() {
        return "StorageCO{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", sMax=" + sMax +
                ", sCurrent=" + sCurrent +
                ", sCardData=" + sCardData +
                ", eMax=" + eMax +
                ", eCurrent=" + eCurrent +
                ", eCardData=" + eCardData +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public int getSMax() {
        return sMax;
    }

    public void setSMax(int sMax) {
        this.sMax = sMax;
    }

    public int getSCurrent() {
        return sCurrent;
    }

    public void setSCurrent(int sCurrent) {
        this.sCurrent = sCurrent;
    }

    public Map<String, List<String>> getSCardData() {
        return sCardData;
    }

    public void setSCardData(Map<String, List<String>> sCardData) {
        this.sCardData = sCardData;
    }

    public int getEMax() {
        return eMax;
    }

    public void setEMax(int eMax) {
        this.eMax = eMax;
    }

    public int getECurrent() {
        return eCurrent;
    }

    public void setECurrent(int eCurrent) {
        this.eCurrent = eCurrent;
    }

    public Map<String, String> getECardData() {
        return eCardData;
    }

    public void setECardData(Map<String, String> eCardData) {
        this.eCardData = eCardData;
    }
}

