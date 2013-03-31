package com.h13.cardgame.core.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CaptainTaskCO;
import com.h13.cardgame.cache.co.ConditionCO;
import com.h13.cardgame.cache.co.TaskCO;
import com.h13.cardgame.cache.co.TaskResultCO;
import com.h13.cardgame.core.JdbcQueueTemplate;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskDAO {

    @Autowired
    JdbcTemplate j;

    @Autowired
    JdbcQueueTemplate q;

    public List<TaskCO> getTaskByGroupId(long groupId) {
        String sql = "select id,task_group_id,name,description,result,count,cooldown,`condition` from task where task_group_id=?";
        List<TaskCO> list = j.query(sql, new Object[]{groupId}, new RowMapper<TaskCO>() {
            @Override
            public TaskCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                TaskCO co = new TaskCO();
                co.setId(rs.getLong(1));
                co.setTaskGroupId(rs.getLong(2));
                co.setName(rs.getString(3));
                co.setDescription(rs.getString(4));
                co.setResult(JSON.parseObject(rs.getString(5), TaskResultCO.class));
                co.setCount(rs.getInt(6));
                co.setCooldown(rs.getInt(7));
                co.setCondition(JSON.parseObject(rs.getString(8), ConditionCO.class));
                return co;
            }
        });
        return list;
    }

    public TaskCO getTask(long taskId) throws ParameterIllegalException {
        String sql = "select id,task_group_id,name,description,result,count,cooldown from task where id=?";
        List<TaskCO> list = j.query(sql, new Object[]{taskId}, new RowMapper<TaskCO>() {
            @Override
            public TaskCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                TaskCO co = new TaskCO();
                co.setId(rs.getLong(1));
                co.setTaskGroupId(rs.getLong(2));
                co.setName(rs.getString(3));
                co.setDescription(rs.getString(4));
                co.setResult(JSON.parseObject(rs.getString(5), TaskResultCO.class));
                co.setCount(rs.getInt(6));
                co.setCooldown(rs.getInt(7));
                return co;
            }
        });
        if (list.size() == 0)
            throw new ParameterIllegalException("taskId=" + taskId);
        else
            return list.get(0);
    }




    public void updateTaskInfo(long cid, CaptainTaskCO captainTask) {
        String sql = "update captain set task_info=? where id=?";
        q.update(sql, new Object[]{ JSON.toJSONString(captainTask),cid});
    }
}
