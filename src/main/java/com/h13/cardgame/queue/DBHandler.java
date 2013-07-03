package com.h13.cardgame.queue;

import com.h13.cardgame.queue.DBTaskMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class DBHandler {

    @Autowired
    JdbcTemplate j;

    public void update(DBTaskMessage detail) {
        j.update(detail.getSql(), detail.getParams());
    }
}
