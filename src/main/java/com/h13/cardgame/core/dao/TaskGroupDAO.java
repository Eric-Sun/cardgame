package com.h13.cardgame.core.dao;

import com.h13.cardgame.cache.co.TaskGroupCO;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TaskGroupDAO {

    @Autowired
    JdbcTemplate j;

    public List<TaskGroupCO> getAllTaskGroup() {
        String sql = "select * from task_group";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<TaskGroupCO>(TaskGroupCO.class));
    }


    public TaskGroupCO get(long taskGroupId) throws ParameterIllegalException {
        String sql = "select * from task_group where task_group_id=?";
        List<TaskGroupCO> list = j.query(sql, new Object[]{taskGroupId},
                new BeanPropertyRowMapper<TaskGroupCO>(TaskGroupCO.class));
        if (list.size() == 0)
            throw new ParameterIllegalException("taskGroupId=" + taskGroupId);
        else
            return list.get(0);
    }


}
