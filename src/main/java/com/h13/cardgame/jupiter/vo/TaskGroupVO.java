package com.h13.cardgame.jupiter.vo;

/**
 * 任务组相关的内容，用来现在一共有多少个任务组，现在完成到第几个了
 * User: sunbo
 * Date: 13-3-27
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class TaskGroupVO {
    private long id;
    private String name;
    private boolean current;  // 是否是当前正在完成的

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

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
