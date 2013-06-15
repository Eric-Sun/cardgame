package com.h13.cardgame.scheduler.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.queue.SchedulerMessage;
import com.h13.cardgame.cache.co.TaskCO;
import com.h13.cardgame.jupiter.service.TaskService;
import com.h13.cardgame.scheduler.SchedulerHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-23
 * Time: 下午3:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TaskCooldownJobHandler implements SchedulerHandler {
    private static Log LOG = LogFactory.getLog(TaskCooldownJobHandler.class);

    @Autowired
    TaskService taskService;

    @Override
    public boolean doHandle(SchedulerMessage detail) {
        long currentMs = System.currentTimeMillis();
        long detailMs = detail.getStartTime();
        long uid = detail.getUid();
        long cid = detail.getCid();
        String str = ((JSONObject) detail.getAttachment()).toJSONString();
        TaskCO task = JSON.parseObject(str, TaskCO.class);
        if (task.getCooldown() <= currentMs - detailMs) {
            try {
                taskService.resumeTask(uid, cid, task.getId());
            } catch (UserNotExistsException e) {
                LogWriter.error(LogWriter.SCHEDULER, "uid=" + uid, e);
            } finally {
                return true;
            }
        } else {
            return false;
        }
    }
}
