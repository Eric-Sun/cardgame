package com.h13.cardgame.jupiter.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-17
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class CaptainCardVO {
    private long id;
    private String icon;
    private String name;
    private String desc;

    @Override
    public String toString() {
        return "CaptainCardVO{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
}
