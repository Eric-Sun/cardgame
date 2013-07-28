package com.h13.cardgame.cache.co;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-25
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public class SkillCO {

    private long id;
    private String name;
    private Map<String, String> data = new HashMap<String, String>();

    @Override
    public String toString() {
        return "SkillCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

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

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
