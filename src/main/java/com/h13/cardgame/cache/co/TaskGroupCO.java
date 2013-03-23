package com.h13.cardgame.cache.co;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:09
 * To change this template use File | Settings | File Templates.
 */
public class TaskGroupCO {

    /**
     * 任务组的id
     */
    private long id;
    /**
     * 任务组的名称
     */
    private String name;
    /**
     * 这个任务组完成之后下一个需要完成的任务组
     */
    private long nextTaskGroupId;

    /**
     * 这个任务组包含的所有的任务的id
     */
    private List<Long> taskIdList = new LinkedList<Long>();

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

    public long getNextTaskGroupId() {
        return nextTaskGroupId;
    }

    public void setNextTaskGroupId(long nextTaskGroupId) {
        this.nextTaskGroupId = nextTaskGroupId;
    }

    public List<Long> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(List<Long> taskIdList) {
        this.taskIdList = taskIdList;
    }

    @Override
    public String toString() {
        return "TaskGroupCO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nextTaskGroupId=" + nextTaskGroupId +
                ", taskIdList=" + taskIdList +
                '}';
    }
}
