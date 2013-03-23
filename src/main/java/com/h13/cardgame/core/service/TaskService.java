package com.h13.cardgame.core.service;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.cache.service.CaptainCache;
import com.h13.cardgame.cache.service.TaskCache;
import com.h13.cardgame.cache.service.TaskGroupCache;
import com.h13.cardgame.core.dao.CaptainDAO;
import com.h13.cardgame.core.dao.TaskDAO;
import com.h13.cardgame.core.dao.TaskGroupDAO;
import com.h13.cardgame.core.exceptions.EnergyNotEnoughException;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.helper.CaptainHelper;
import com.h13.cardgame.core.helper.TaskHelper;
import com.h13.cardgame.scheduler.JobType;
import com.h13.cardgame.scheduler.SchedulerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-19
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TaskService {
    private static Log LOG = LogFactory.getLog(CaptainService.class);

    @Autowired
    private CaptainHelper captainHelper;
    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private TaskHelper taskHelper;

    /**
     * 尝试完成一个任务
     *
     * @param cid
     * @param taskId
     * @throws ParameterIllegalException
     * @throws EnergyNotEnoughException
     */
    public void d(long cid, long taskId) throws ParameterIllegalException, EnergyNotEnoughException {
        // 检测他完成的这个任务，在这个人物中是不是应该可以被完成
        CaptainCO captain = captainHelper.get(cid);
        long taskGroupId = captain.getTaskInfo().getTaskGroupId();
        TaskGroupCO taskGroup = taskHelper.getTaskGroup(taskGroupId);
        List<Long> idList = taskGroup.getTaskIdList();
        if (!idList.contains(taskId)) {
            throw new ParameterIllegalException("taskId[" + taskId + "]  not in groupId[" + taskGroupId + "]");
        }
        // 判断是否任务正在冷却中，如果可以的话，这个人物所有的相关任务的冷却标志都重置
        schedulerService.attemptTrigger(cid, JobType.TASK_COOLDOWN_JOB);

        // 重新获取captain，然后进行任务完成的逻辑
        captain = captainHelper.get(cid);
        TaskCO task = taskHelper.getTask(taskId);
        CaptainTaskCO taskInfo = captain.getTaskInfo();
        if (taskInfo.getTaskMap().get(taskId) == null || taskInfo.getTaskMap().get(taskId).isCanBeDo()) {
            // 可以完成任务
            // 先进行任务是否可以完成的判断，也就是资源是否足够
            if (captain.getEnergy() < task.getCondition().getEnergy()) {
                throw new EnergyNotEnoughException("captainId = " + cid + " need " + task.getCondition().getEnergy()
                        + " have " + captain.getEnergy());
            }
            // 减去相应的energy
            captainHelper.subEnergy(captain, task.getCondition().getEnergy());
            // 完成任务并且把captain写回缓存，并且更新数据库的energy
            taskHelper.updateTaskInfo(captain, taskId);
            schedulerService.addJob(cid, JobType.TASK_COOLDOWN_JOB, task.getCooldown());
            // 保存用户的新的状态
            captainHelper.subEnergy(captain, task.getCondition().getEnergy());
            captainHelper.cacheCaptain(captain);

        } else {
            // 不可以完成任务
            throw new ParameterIllegalException("taskId[" + taskId + "] is cooldowning");
        }
    }


}
