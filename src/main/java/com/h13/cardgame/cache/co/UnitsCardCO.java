package com.h13.cardgame.cache.co;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-19
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class UnitsCardCO {
    private long id;
    private String name;
    private String desc;
    private Map<String,String> specData;
    private String icon;

    @Override
    public String toString() {
        return "UnitsCardCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", specData=" + specData +
                ", icon='" + icon + '\'' +
                '}';
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, String> getSpecData() {
        return specData;
    }

    public void setSpecData(Map<String, String> specData) {
        this.specData = specData;
    }
}
