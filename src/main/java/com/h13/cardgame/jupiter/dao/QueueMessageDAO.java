package com.h13.cardgame.jupiter.dao;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.jupiter.JdbcQueueTemplate;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.scheduler.SchedulerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-25
 * Time: 下午6:56
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class QueueMessageDAO {

    @Autowired
    JdbcQueueTemplate q;

    @Autowired
    JdbcTemplate j;

    public void add(String messageId, long uid, long cid, long startTime, Object attachment, long interval, SchedulerType schedulerType, long actionObjectId) {
        String sql = "insert into queue_message (message_id,uid,cid,start_time,attachment,`interval`,scheduler_type,action_object_id," +
                "create_time)" +
                " values(?,?,?,?,?,?,?,?,now())";
        q.update(sql, new Object[]{messageId, uid, cid, startTime, JSON.toJSONString(attachment), interval, schedulerType.name(),
                actionObjectId});
    }


    public void delete(String messageId) {
        String sql = "update queue_message set flag=-1 where message_id=?";
        q.update(sql, new Object[]{messageId});
    }


    public List<SchedulerMessage> getJob(long cid, SchedulerType schedulerType) {
        String sql = "select * from queue_message where cid=? and scheduler_type=?";
        List<SchedulerMessage> messageList = j.query(sql, new Object[]{cid, schedulerType}, new BeanPropertyRowMapper<SchedulerMessage>(SchedulerMessage.class));
        if (messageList.size() == 0)
            return null;
        else
            return messageList;
    }

    public int checkJob(long cid, SchedulerType schedulerType) {
        String sql = "select count(1) from queue_message where cid=? and scheduler_type=?";
        return j.queryForInt(sql, new Object[]{cid, schedulerType});
    }

}
