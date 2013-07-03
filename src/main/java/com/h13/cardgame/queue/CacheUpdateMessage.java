package com.h13.cardgame.queue;

import com.h13.cardgame.config.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-2
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class CacheUpdateMessage {
    private String id;
    private Configuration.CACHE type;
    private String data;

    @Override
    public String toString() {
        return "CacheUpdateMessage{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Configuration.CACHE getType() {
        return type;
    }

    public void setType(Configuration.CACHE type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
