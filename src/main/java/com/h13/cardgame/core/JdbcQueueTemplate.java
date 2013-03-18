package com.h13.cardgame.core;

import com.h13.cardgame.queue.DBTaskQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
@Service
public class JdbcQueueTemplate {

    @Autowired
    DBTaskQueue queue;

    public void update(String sql, Object... param) {
        queue.push(sql, param);
    }
}
