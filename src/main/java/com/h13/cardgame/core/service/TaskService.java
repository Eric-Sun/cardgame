package com.h13.cardgame.core.service;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.core.exceptions.EnergyNotEnoughException;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.exceptions.TaskIsOverException;
import com.h13.cardgame.core.helper.CaptainHelper;
import com.h13.cardgame.core.helper.TaskHelper;
import com.h13.cardgame.core.vo.TaskGroupVO;
import com.h13.cardgame.core.vo.TaskVO;
import com.h13.cardgame.scheduler.SchedulerType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

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
     * <p/>
     * 返回false:说明需要进行获取下一组任务
     *
     * @param cid
     * @param taskId
     * @throws ParameterIllegalException
     * @throws EnergyNotEnoughException
     */
    public boolean d(long cid, long taskId) throws ParameterIllegalException, EnergyNotEnoughException, TaskIsOverException {
        // 检测他完成的这个任务，在这个人物中是不是应该可以被完成
        CaptainCO captain = captainHelper.get(cid);
        long taskGroupId = captain.getTaskInfo().getTaskGroupId();
        TaskGroupCO taskGroup = taskHelper.getTaskGroup(taskGroupId);
        List<Long> idList = taskGroup.getTaskIdList();
        if (!idList.contains(taskId)) {
            throw new ParameterIllegalException("taskId[" + taskId + "]  not in groupId[" + taskGroupId + "]");
        }
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
            taskHelper.addTaskInfo(captain, taskId);
            captainHelper.addTaskResult(captain, task.getResult());
            schedulerService.addTaskCooldownJob(captain, task);
            // 保存用户的新的状态
            captainHelper.cacheCaptain(captain);
            if (taskHelper.isLastTaskInGroup(task)) {
                if (taskHelper.isLastTaskGroup(taskGroup)) {
                    throw new TaskIsOverException("");
                } else {
                    // 给他传下一个任务组的信息
                    return false;
                }
            }
            return true;
        } else {
            // 不可以完成任务
            throw new ParameterIllegalException("taskId[" + taskId + "] is cooldowning");
        }
    }

    /**
     * 恢复一个任务，让他可以被继续完成
     *
     * @param cid
     * @param taskId
     * @throws ParameterIllegalException
     */
    public void resumeTask(long cid, long taskId) throws ParameterIllegalException {
        CaptainCO captain = captainHelper.get(cid);
        taskHelper.resumeTask(captain, taskId);
        captainHelper.cacheCaptain(captain);
        LOG.info("resume the task. cid=" + cid + " taskId=" + taskId);
    }


    /**
     * 获得完成的任务组中的所有任务的信息，和完成情况
     *
     * @param cid
     * @return
     */
    public List<TaskVO> task(long cid) throws ParameterIllegalException {
        CaptainCO captain = captainHelper.get(cid);
        List<TaskCO> taskList = taskHelper.getTaskList(captain.getTaskInfo().getTaskGroupId());
        // convert co to vo
        List<TaskVO> returnList = new ArrayList<TaskVO>();
        cvtTaskCO2VO(captain, taskList, returnList);
        return returnList;  //To change body of created methods use File | Settings | File Templates.
    }

    private void cvtTaskCO2VO(CaptainCO captain, List<TaskCO> coList, List<TaskVO> voList) {
        for (TaskCO task : coList) {
            TaskVO vo = new TaskVO();
            vo.setId(task.getId());
            vo.setName(task.getName());
            vo.setDesc(task.getDescription());
            vo.setCount(task.getCount());
            vo.setCondition(task.getCondition());
            vo.setResult(task.getResult());
            vo.setSum((captain.getTaskInfo().getTaskMap().get(task.getId()) == null) ? 0 :
                    captain.getTaskInfo().getTaskMap().get(task.getId()).getCount());
            voList.add(vo);
        }
    }

    public List<TaskGroupVO> taskGroup(long cid) throws ParameterIllegalException {
        List<TaskGroupVO> list = new ArrayList<TaskGroupVO>();
        CaptainCO captain = captainHelper.get(cid);
        List<TaskGroupCO> taskGroupList = taskHelper.getTaskGroupList();
        for (TaskGroupCO taskGroup : taskGroupList) {
            TaskGroupVO vo = new TaskGroupVO();
            vo.setId(taskGroup.getId());
            vo.setName(taskGroup.getName());
            vo.setCurrent((captain.getTaskInfo().getTaskGroupId() == taskGroup.getId() ? true : false));
            list.add(vo);
        }
        return list;
    }

    /**
     * 当前任务已经完成之后，调用，清楚captain中的相关任务信息，并且获取下一任务组的信息
     * @param cid
     * @return
     * @throws ParameterIllegalException
     */
    public List<TaskVO> nextTask(long cid) throws ParameterIllegalException {
        CaptainCO captain = captainHelper.get(cid);
        long nextTaskGroupId = captain.getTaskInfo().getTaskGroupId();
        captain.getTaskInfo().getTaskMap().clear();
        captain.getTaskInfo().setTaskGroupId(nextTaskGroupId);
        List<TaskCO> taskList = taskHelper.getTaskList(nextTaskGroupId);
        List<TaskVO> returnList = new ArrayList<TaskVO>();
        cvtTaskCO2VO(captain, taskList, returnList);
        return returnList;
    }
}
