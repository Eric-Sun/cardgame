package com.h13.cardgame.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-12
 * Time: 下午6:01
 * To change this template use File | Settings | File Templates.
 */
public class DropGroupCO {
    private long id;
    private String name;
    private DropGroupDataCO data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DropGroupDataCO getData() {
        return data;
    }

    public void setData(DropGroupDataCO data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DropGroupCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
