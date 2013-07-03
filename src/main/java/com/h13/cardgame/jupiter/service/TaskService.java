package com.h13.cardgame.jupiter.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.jupiter.exceptions.*;
import com.h13.cardgame.jupiter.helper.CityHelper;
import com.h13.cardgame.jupiter.helper.StorageHelper;
import com.h13.cardgame.jupiter.helper.TaskHelper;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.TaskGroupVO;
import com.h13.cardgame.jupiter.vo.TaskRewardResultVO;
import com.h13.cardgame.jupiter.vo.TaskRewardVO;
import com.h13.cardgame.jupiter.vo.TaskVO;
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
    private static Log LOG = LogFactory.getLog(CityService.class);

    @Autowired
    private CityHelper cityHelper;
    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private TaskHelper taskHelper;
    @Autowired
    private DropGroupService dropGroupService;
    @Autowired
    private StorageHelper storageHelper;

    /**
     * 尝试完成一个任务
     * <p/>
     * 返回一个list，第一个对象为获得的奖励，第二对象true/false:说明需要进行获取下一组任务
     *
     * @param cid
     * @param taskId
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     * @throws EnergyNotEnoughException
     */
    public List<Object> d(long uid, long cid, long taskId) throws UserNotExistsException, EnergyNotEnoughException, TaskIsOverException, RandomRewardException, UserIllegalParamterException, TaskCompletedTooManyException, EquipmentStorageIsFullException, SquardStorageIsFullException {
        List<Object> resultList = new ArrayList<Object>();
        // 检测他完成的这个任务，在这个人物中是不是应该可以被完成
        CityCO captain = cityHelper.get(uid, cid);
        long taskGroupId = captain.getTaskStatus().getTaskGroupId();
        TaskGroupCO taskGroup = taskHelper.getTaskGroup(taskGroupId);
        List<Long> idList = taskGroup.getTaskIdList();
        if (!idList.contains(taskId)) {
            throw new UserNotExistsException("taskId[" + taskId + "]  not in groupId[" + taskGroupId + "]");
        }
        // 检查是否仓库已经满了
        StorageCO storage = storageHelper.getByCid(cid);
        storageHelper.checkAllStorageIsFull(storage);
        // 重新获取captain，然后进行任务完成的逻辑
        captain = cityHelper.get(uid, cid);
        TaskCO task = taskHelper.getTask(taskId);
        CityTaskStatusCO taskInfo = captain.getTaskStatus();
        if (taskInfo.getTaskMap().get(taskId) == null
                || taskInfo.getTaskMap().get(taskId).isCanBeDo()) {
            // 可以完成任务
            // 先进行任务是否可以完成的判断，也就是资源是否足够
            if (captain.getEnergy() < task.getCondition().getEnergy()) {
                throw new EnergyNotEnoughException("captainId = " + cid + " need " + task.getCondition().getEnergy()
                        + " have " + captain.getEnergy());
            }
            // 当任务完成次数过多的时候也无法完成
            if (taskInfo.getTaskMap().get(taskId) != null &&
                    task.getCount() == taskInfo.getTaskMap().get(taskId).getCount()) {
                throw new TaskCompletedTooManyException("uid=" + uid + " cid=" + cid + " taskId=" + taskId);
            }
            // 减去相应的energy
            cityHelper.subEnergy(captain, task.getCondition().getEnergy());
            // 完成任务并且把captain写回缓存，并且更新数据库的energy
            taskHelper.addTaskInfo(captain, taskId);
            // 奖励
            TaskRewardResultVO result = taskHelper.reward(captain, task);
            resultList.add(result);
            if (task.getCooldown() != 0)
                schedulerService.addTaskCooldownJob(captain, task);
            // 保存用户的新的状态
            cityHelper.cache(captain);
            if (taskHelper.isLastTaskInGroup(task)) {
                if (taskHelper.isLastTaskGroup(taskGroup)) {
                    throw new TaskIsOverException("");
                } else {
                    // 给他传下一个任务组的信息
                    resultList.add(false);
                    return resultList;
                }
            }
            resultList.add(true);
            return resultList;
        } else {
            // 不可以完成任务
            throw new UserNotExistsException("taskId[" + taskId + "] is cooldowning");
        }
    }

    /**
     * 恢复一个任务，让他可以被继续完成
     *
     * @param cid
     * @param taskId
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public void resumeTask(long uid, long cid, long taskId) throws UserNotExistsException, UserIllegalParamterException {
        CityCO city = cityHelper.get(uid, cid);
        taskHelper.resumeTask(city, taskId);
        cityHelper.cache(city);
        LogWriter.info(LogWriter.TASK, cid, taskId, JSON.toJSONString(city));
    }


    /**
     * 获得完成的任务组中的所有任务的信息，和完成情况
     *
     * @param cid
     * @return
     */
    public List<TaskVO> task(long uid, long cid) throws UserNotExistsException, UserIllegalParamterException {
        CityCO city = cityHelper.get(uid, cid);
        List<TaskCO> taskList = taskHelper.getTaskList(city.getTaskStatus().getTaskGroupId());
        // convert co to vo
        List<TaskVO> returnList = new ArrayList<TaskVO>();
        cvtTaskCO2VO(city, taskList, returnList);
        return returnList;
    }

    private void cvtTaskCO2VO(CityCO captain, List<TaskCO> coList, List<TaskVO> voList) {
        for (TaskCO task : coList) {
            TaskVO vo = new TaskVO();
            vo.setId(task.getId());
            vo.setName(task.getName());
            vo.setDesc(task.getDescription());
            vo.setCount(task.getCount());
            vo.setCondition(task.getCondition());
            TaskRewardVO rewardVO = new TaskRewardVO();
            DropGroupCO dropGroup = dropGroupService.get(task.getDropGroupId());
            if (dropGroup.getData().getExp().isDrop())
                rewardVO.setExp(dropGroup.getData().getExp().getMax());
            if (dropGroup.getData().getSilver().isDrop())
                rewardVO.setSilver(dropGroup.getData().getSilver().getMax());
            vo.setReward(rewardVO);
            vo.setCurCount((captain.getTaskStatus().getTaskMap().get(task.getId()) == null) ? 0 :
                    captain.getTaskStatus().getTaskMap().get(task.getId()).getCount());
            voList.add(vo);
        }
    }

    public List<TaskGroupVO> taskGroup(long uid, long cid) throws UserNotExistsException, UserIllegalParamterException {
        List<TaskGroupVO> list = new ArrayList<TaskGroupVO>();
        CityCO captain = cityHelper.get(uid, cid);
        List<TaskGroupCO> taskGroupList = taskHelper.getTaskGroupList();
        for (TaskGroupCO taskGroup : taskGroupList) {
            TaskGroupVO vo = new TaskGroupVO();
            vo.setId(taskGroup.getId());
            vo.setName(taskGroup.getName());
            if (captain.getTaskStatus().getTaskGroupId() < taskGroup.getId())
                vo.setStatus(1);
            else if (captain.getTaskStatus().getTaskGroupId() == taskGroup.getId())
                vo.setStatus(0);
            else
                vo.setStatus(-1);
            list.add(vo);
        }
        return list;
    }

    /**
     * 当前任务已经完成之后，调用，清楚captain中的相关任务信息，并且获取下一任务组的信息
     *
     * @param cid
     * @return
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public List<TaskVO> nextTask(long uid, long cid) throws UserNotExistsException, UserIllegalParamterException {
        CityCO captain = cityHelper.get(uid, cid);
        long nextTaskGroupId = captain.getTaskStatus().getTaskGroupId();
        captain.getTaskStatus().getTaskMap().clear();
        captain.getTaskStatus().setTaskGroupId(nextTaskGroupId);
        List<TaskCO> taskList = taskHelper.getTaskList(nextTaskGroupId);
        List<TaskVO> returnList = new ArrayList<TaskVO>();
        cvtTaskCO2VO(captain, taskList, returnList);
        return returnList;
    }
}
