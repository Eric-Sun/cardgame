package com.h13.cardgame.cache.co;

import java.util.HashMap;
import java.util.Map;

/**
 * 随机奖励
 * User: sunbo
 * Date: 13-3-27
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
public class RandomAwardCO {
    private long id;
    private String name;
    private RandomAwardType type;
    private Map<String,String> data = new HashMap<String, String>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RandomAwardType getType() {
        return type;
    }

    public void setType(RandomAwardType type) {
        this.type = type;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RandomAwardCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", data=" + data +
                '}';
    }
}
