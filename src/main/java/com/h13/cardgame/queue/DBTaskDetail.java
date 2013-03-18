package com.h13.cardgame.queue;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class DBTaskDetail {
    private String taskId;
    private String sql;
    private Object[] params;

    @Override
    public String toString() {
        return "DBTaskDetail{" +
                "taskId='" + taskId + '\'' +
                ", sql='" + sql + '\'' +
                ", params=" + (params == null ? null : Arrays.asList(params)) +
                '}';
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public DBTaskDetail(){}
    public DBTaskDetail(String taskId, String sql, Object[] params) {
        this.taskId = taskId;
        this.sql = sql;
        this.params = params;
    }
}
