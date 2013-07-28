package com.h13.cardgame.cache.co;

/**
 * 队长官位
 * User: sunbo
 * Date: 13-7-23
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public class CaptainTitleCO {

    private long id;
    private String name;
    private float rate;
    private int exp;

    @Override
    public String toString() {
        return "CaptainTitleCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
