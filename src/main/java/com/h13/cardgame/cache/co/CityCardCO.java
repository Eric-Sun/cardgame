package com.h13.cardgame.cache.co;

import com.h13.cardgame.jupiter.CardType;

import java.util.Map;

/**
 * captain 拥有的卡的对象
 * User: sunbo
 * Date: 13-4-13
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
public class CityCardCO {
    private long id;
    private long cityId;
    private long cardId;
    private String name;
    private String icon;
    private int status;
    private String desc;
    private CardType cardType;
    private Map<String, String> data;

    @Override
    public String toString() {
        return "CityCardCO{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", cardId=" + cardId +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", cardType=" + cardType +
                ", data=" + data +
                '}';
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
