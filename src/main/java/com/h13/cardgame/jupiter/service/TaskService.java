package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.jupiter.exceptions.*;
import com.h13.cardgame.jupiter.helper.*;
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
    private TaskHelper taskHelper;
    @Autowired
    private DropGroupHelper dropGroupService;
    @Autowired
    private StorageHelper storageHelper;
    @Autowired
    private CooldownHelper cooldownHelper;

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
    public List<Object> d(long uid, long cid, long taskId) throws UserNotExistsException,
            EnergyNotEnoughException, TaskIsOverException, TaskCompletedTooManyException, EquipmentStorageIsFullException, SquadStorageIsFullException, TaskIsCooldownException, TaskIsNotExistsException, TaskGroupIsNotExistsException, UserDontHaveThisCityException, CaptainStorageIsFullException, LevelIsTopException {
        List<Object> resultList = new ArrayList<Object>();
        // 检测他完成的这个任务，在这个人物中是不是应该可以被完成
        CityCO city = cityHelper.get(uid, cid);
        long taskGroupId = city.getTaskStatus().getTaskGroupId();
        TaskGroupCO taskGroup = taskHelper.getTaskGroup(taskGroupId);
        List<Long> idList = taskGroup.getTaskIdList();
        if (!idList.contains(taskId)) {
            throw new UserNotExistsException("taskId[" + taskId + "]  not in groupId[" + taskGroupId + "]");
        }
        // 检查是否仓库已经满了
        StorageCO storage = storageHelper.getByCid(cid);
        storageHelper.checkAllStorageIsFull(storage);
        // 重新获取captain，然后进行任务完成的逻辑
        TaskCO task = taskHelper.getTask(taskId);
        CityTaskStatusCO taskInfo = city.getTaskStatus();
        if (taskInfo.getTaskMap().get(taskId) == null
                || taskInfo.getTaskMap().get(taskId).isCanBeDo()) {
            // 可以完成任务
            // 先进行任务是否可以完成的判断，也就是资源是否足够
            if (city.getEnergy() < task.getCondition().getEnergy()) {
                throw new EnergyNotEnoughException("captainId = " + cid + " need " + task.getCondition().getEnergy()
                        + " have " + city.getEnergy());
            }
            // 当任务完成次数过多的时候也无法完成
            if (taskInfo.getTaskMap().get(taskId) != null &&
                    task.getCount() == taskInfo.getTaskMap().get(taskId).getCount()) {
                throw new TaskCompletedTooManyException("uid=" + uid + " cid=" + cid + " taskId=" + taskId);
            }
            // 减去相应的energy
            cityHelper.subEnergy(city, task.getCondition().getEnergy());
            // 完成任务
            taskHelper.updateTaskInfo(city, taskId);
            // 获得奖励奖励，并且把获得的奖励插入数据库中
            TaskRewardResultVO result = taskHelper.reward(city, task);
            resultList.add(result);
            if (task.getCooldown() != 0)
                cooldownHelper.addTaskCooldown(city, task);
            // 保存用户的新的状态
            cityHelper.cache(city);
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
            throw new TaskIsCooldownException("taskId[" + taskId + "] is cooldowning");
        }
    }

    /**
     * 获得完成的任务组中的所有任务的信息，和完成情况
     *
     * @param cid
     * @return
     */
    public List<TaskVO> task(long uid, long cid) throws UserNotExistsException, TaskGroupIsNotExistsException, TaskIsNotExistsException, UserDontHaveThisCityException {
        CityCO city = cityHelper.get(uid, cid);
        List<TaskCO> taskList = taskHelper.getTaskList(city.getTaskStatus().getTaskGroupId());
        // convert co to vo
        List<TaskVO> returnList = new ArrayList<TaskVO>();
        cvtTaskCO2VO(city, taskList, returnList);
        return returnList;
    }

    private void cvtTaskCO2VO(CityCO city, List<TaskCO> coList, List<TaskVO> voList) {
        for (TaskCO task : coList) {
            TaskVO vo = new TaskVO();
            vo.setId(task.getId());
            vo.setName(task.getName());
            vo.setDesc(task.getDescription());
            vo.setCount(task.getCount());
            vo.setCooldown(task.getCooldown());
            vo.setCondition(task.getCondition());
            TaskRewardVO rewardVO = new TaskRewardVO();
            DropGroupCO dropGroup = dropGroupService.get(task.getDropGroupId());
            if (dropGroup.getData().getExp().isDrop())
                rewardVO.setExp(dropGroup.getData().getExp().getMax());
            if (dropGroup.getData().getSilver().isDrop())
                rewardVO.setSilver(dropGroup.getData().getSilver().getMax());
            vo.setReward(rewardVO);
            vo.setCurCount((city.getTaskStatus().getTaskMap().get(task.getId()) == null) ? 0 :
                    city.getTaskStatus().getTaskMap().get(task.getId()).getCount());
            // 获得这个任务的上次完成的时间
            vo.setLastTimeStamp(cooldownHelper.getTaskLastTimeStamp(city,task.getId()));
            voList.add(vo);
        }
    }

    public List<TaskGroupVO> taskGroup(long uid, long cid) throws UserNotExistsException, UserDontHaveThisCityException {
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
    public List<TaskVO> nextTask(long uid, long cid) throws UserNotExistsException, TaskGroupIsNotExistsException, TaskIsNotExistsException, UserDontHaveThisCityException {
        CityCO captain = cityHelper.get(uid, cid);
        long nextTaskGroupId = captain.getTaskStatus().getTaskGroupId();
        captain.getTaskStatus().getTaskMap().clear();
        captain.getTaskStatus().setTaskGroupId(nextTaskGroupId);
        List<TaskCO> taskList = taskHelper.getTaskList(nextTaskGroupId);
        List<TaskVO> returnList = new ArrayList<TaskVO>();
        cvtTaskCO2VO(captain, taskList, returnList);
        return returnList;
    }


    /**
     * 用户在访问的开始时候看看是否有任务的冷却已经到了，并且修改状态
     * @param cid
     * @param uid
     */
    public void flushTaskStatus(long uid, long cid) throws UserNotExistsException, TaskIsNotExistsException, UserDontHaveThisCityException {
        CityCO city = cityHelper.get(uid, cid);
        cooldownHelper.tryToFinishTaskCooldown(city);
        cityHelper.cache(city);
    }
}
