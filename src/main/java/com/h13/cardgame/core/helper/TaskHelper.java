package com.h13.cardgame.core.helper;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.cache.service.CaptainCache;
import com.h13.cardgame.cache.service.TaskCache;
import com.h13.cardgame.cache.service.TaskGroupCache;
import com.h13.cardgame.core.dao.TaskDAO;
import com.h13.cardgame.core.dao.TaskGroupDAO;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-20
 * Time: 下午6:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TaskHelper {
    @Autowired
    private TaskCache taskCache;
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private TaskGroupDAO taskGroupDAO;
    @Autowired
    private CaptainCache captainCache;
    @Autowired
    private TaskGroupCache taskGroupCache;

    /**
     * 通过groupid获得对应的任务组的信息，包括基本信息和包含哪些子任务
     *
     * @param taskGroupId
     * @return
     */
    public TaskGroupCO getTaskGroup(long taskGroupId) throws ParameterIllegalException {
        TaskGroupCO taskGroup = taskGroupCache.get(taskGroupId);
        if (taskGroup == null) {
            taskGroup = taskGroupDAO.get(taskGroupId);
            taskGroupCache.put(taskGroup);
        }
        return taskGroup;
    }

    public TaskCO getTask(long taskId) throws ParameterIllegalException {
        TaskCO task = taskCache.get(taskId);
        if (task == null) {
            task = taskDAO.getTask(taskId);
            taskCache.put(task);
        }
        return task;
    }

    public void updateTaskInfo(CaptainCO captain, long taskId) {
        CaptainTaskCO taskInfo = captain.getTaskInfo();
        if (taskInfo.getTaskMap().get(taskId) == null) {
            taskInfo.getTaskMap().put(taskId, new CaptainPerTaskCO(1, false));
        } else {
            CaptainPerTaskCO perTask = taskInfo.getTaskMap().get(taskId);
            perTask.setCount(perTask.getCount() + 1);
            perTask.setCanBeDo(false);
        }
        taskDAO.updateTaskInfo(captain.getId(), taskInfo);
    }
}
