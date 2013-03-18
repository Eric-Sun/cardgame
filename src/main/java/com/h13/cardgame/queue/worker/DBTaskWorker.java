package com.h13.cardgame.queue.worker;

import com.h13.cardgame.queue.DBTaskDetail;
import com.h13.cardgame.queue.DBTaskQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DBTaskWorker {
    private static Log LOG = LogFactory.getLog(DBTaskWorker.class);

    @Autowired
    DBTaskQueue dbTaskQueue;

    @Autowired
    DBHandler handler;

    public void doProcess() {
        DBTaskDetail detail = dbTaskQueue.peek();
        if (detail != null) {
            handler.update(detail);
            LOG.info("Peek DB Task : " + detail.toString());
        }
    }
}
