package com.h13.cardgame.core.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.TaskCO;
import com.h13.cardgame.cache.co.TaskResultCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public List<TaskCO> getTaskByGroupId(long groupId) {
        String sql = "select id,task_group_id,name,description,result,count,cooldown from task where task_group_id=?";
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
                return co;
            }
        });
        return list;
    }


}
