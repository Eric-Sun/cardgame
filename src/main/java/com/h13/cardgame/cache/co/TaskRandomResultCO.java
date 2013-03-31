package com.h13.cardgame.cache.co;

/**
 * 任务随机结果的配置，主要是类型和对应的随机奖品的id
 * User: sunbo
 * Date: 13-3-27
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class TaskRandomResultCO {
    private String type;
    private long id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
