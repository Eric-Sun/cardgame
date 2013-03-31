package com.h13.cardgame.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午11:46
 * To change this template use File | Settings | File Templates.
 */
public class CardCO {

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

    @Override
    public String toString() {
        return "CardCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
