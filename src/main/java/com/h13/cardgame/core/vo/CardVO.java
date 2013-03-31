package com.h13.cardgame.core.vo;

/**
 * 卡牌的信息
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午10:49
 * To change this template use File | Settings | File Templates.
 */
public class CardVO {

    private long id;
    private String name;
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
