package com.h13.cardgame.config.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.TaskCO;
import com.h13.cardgame.cache.co.TaskGroupCO;
import com.h13.cardgame.cache.service.TaskCache;
import com.h13.cardgame.cache.service.TaskGroupCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.core.dao.TaskDAO;
import com.h13.cardgame.core.dao.TaskGroupDAO;
import com.h13.cardgame.core.utils.LogWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TaskLoaderService {

    private static Log LOG = LogFactory.getLog(TaskLoaderService.class);

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    TaskGroupDAO taskGroupDAO;

    @Autowired
    TaskCache taskCache;

    @Autowired
    TaskGroupCache taskGroupCache;

    public void load() throws LoadException {
        try {
            List<TaskGroupCO> taskGroupCOList = taskGroupDAO.getAllTaskGroup();

            for (TaskGroupCO group : taskGroupCOList) {
                List<TaskCO> taskList = taskDAO.getTaskByGroupId(group.getId());
                for (TaskCO task : taskList) {
                    taskCache.put(task);
                    group.getTaskIdList().add(task.getId());
                    LOG.info("loaded task " + task.toString());
                }
                taskGroupCache.put(group);
                LOG.info("loaded taskGroup " + group.toString());
            }
            LOG.info("load task info successfully.");
        } catch (Exception e) {
            LOG.error("load task info error", e);
            throw new LoadException("load task info error", e);
        }
    }

}
