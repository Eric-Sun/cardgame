package com.h13.cardgame.cache.co;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-16
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
public class BarCO {
    private long cityId;
    private long lastFlush;
    private int size;
    private List<String> list = new LinkedList<String>();

    public long getLastFlush() {
        return lastFlush;
    }

    public void setLastFlush(long lastFlush) {
        this.lastFlush = lastFlush;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BarCO{" +
                "cityId=" + cityId +
                ", lastFlush=" + lastFlush +
                ", size=" + size +
                ", list=" + list +
                '}';
    }
}
