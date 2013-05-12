package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.cache.service.CityCache;
import com.h13.cardgame.cache.service.TaskCache;
import com.h13.cardgame.cache.service.TaskGroupCache;
import com.h13.cardgame.jupiter.dao.TaskDAO;
import com.h13.cardgame.jupiter.dao.TaskGroupDAO;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.exceptions.RandomRewardException;
import com.h13.cardgame.jupiter.service.DropGroupService;
import com.h13.cardgame.jupiter.vo.CardVO;
import com.h13.cardgame.jupiter.vo.TaskRewardResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private CityCache captainCache;
    @Autowired
    private TaskGroupCache taskGroupCache;
    @Autowired
    private CardHelper cardHelper;
    @Autowired
    private CityHelper cityHelper;

    @Autowired
    private LevelHelper levelHelper;

    @Autowired
    private DropGroupService dropGroupService;


    /**
     * 通过groupid获得对应的任务组的信息，包括基本信息和包含哪些子任务
     *
     * @param taskGroupId
     * @return
     */
    public TaskGroupCO getTaskGroup(long taskGroupId) throws UserNotExistsException {
        TaskGroupCO taskGroup = taskGroupCache.get(taskGroupId);
        if (taskGroup == null) {
            taskGroup = taskGroupDAO.get(taskGroupId);
            taskGroupCache.put(taskGroup);
        }
        return taskGroup;
    }

    public TaskCO getTask(long taskId) throws UserNotExistsException {
        TaskCO task = taskCache.get(taskId);
        if (task == null) {
            task = taskDAO.getTask(taskId);
            taskCache.put(task);
        }
        return task;
    }

    /**
     * 完成任务
     *
     * @param captain
     * @param taskId
     */
    public void addTaskInfo(CityCO captain, long taskId) {
        CityTaskCO taskInfo = captain.getTaskInfo();
        if (taskInfo.getTaskMap().get(taskId) == null) {
            taskInfo.getTaskMap().put(taskId, new CityPerTaskCO(1, false));
        } else {
            CityPerTaskCO perTask = taskInfo.getTaskMap().get(taskId);
            perTask.setCount(perTask.getCount() + 1);
            perTask.setCanBeDo(false);
        }
        taskDAO.updateTaskInfo(captain.getId(), taskInfo);
    }


    public void resumeTask(CityCO captain, long taskId) {
        captain.getTaskInfo().getTaskMap().get(taskId).setCanBeDo(true);
        taskDAO.updateTaskInfo(captain.getId(), captain.getTaskInfo());
    }

    /**
     * 判断这个任务是不是这组任务中最后一个
     *
     * @param task
     * @return
     */
    public boolean isLastTaskInGroup(TaskCO task) {
        if (task.getLast() == 0)
            // 正常
            return false;
        else
            return true;

    }

    /**
     * 判断一个任务组是不是最后一个任务组，如果是的话，所有的任务已经做完了
     *
     * @param taskGroup
     * @return
     */
    public boolean isLastTaskGroup(TaskGroupCO taskGroup) {
        if (taskGroup.getLast() == 0)
            return false;
        else
            return true;
    }

    /**
     * 获得一个任务组下的任务列表
     *
     * @param taskGroupId
     * @return
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     */
    public List<TaskCO> getTaskList(long taskGroupId) throws UserNotExistsException {
        List<TaskCO> taskList = new ArrayList<TaskCO>();
        TaskGroupCO taskGroup = getTaskGroup(taskGroupId);
        List<Long> taskIdList = taskGroup.getTaskIdList();
        for (Long taskId : taskIdList) {
            TaskCO task = getTask(taskId);
            taskList.add(task);
        }
        return taskList;
    }


    public List<TaskGroupCO> getTaskGroupList() {
        return taskGroupCache.list();
    }


    /**
     * 进行任务奖励
     *
     * @param captain
     * @param task
     * @return
     * @throws RandomRewardException
     */
    public TaskRewardResultVO reward(CityCO captain, TaskCO task) throws RandomRewardException {
        DropGroupCO dropGroup = dropGroupService.get(task.getDropGroupId());
        // 依次判断 exp ,silver 等是否有问题
        int exp = randomCommonItem(dropGroup.getData().getExp());
        int silver = randomCommonItem(dropGroup.getData().getSilver());
        long cardId = randomCard(dropGroup.getData());

        captain.setExp(captain.getExp() + exp);
        captain.setSilver(captain.getSilver() + silver);
        cityHelper.updateReward(captain.getId(), exp, silver);

        // add package
        CardCO cardCO = cardHelper.get(cardId);
        CardVO cardVO = new CardVO();
        cardVO.setId(cardCO.getId());
        cardVO.setName(cardCO.getName());
        cardVO.setImg(cardCO.getImg());
        cardHelper.addCardToCaptain(captain, cardCO);

        TaskRewardResultVO vo = new TaskRewardResultVO();
        vo.setCard(cardVO);
        vo.setExp(exp);
        vo.setSilver(silver);
        return vo;
    }


    /**
     * 随机掉落item
     *
     * @param item
     * @return
     */
    private int randomCommonItem(CommonRewardItemCO item) {
        if (!item.isDrop())
            return 0;
        if (!item.isRandom())
            return item.getMax();
        Random random = new Random();
        int v = random.nextInt(item.getMax() - item.getMin());
        return v;
    }

    private long randomCard(DropGroupDataCO cardDropGroup) throws RandomRewardException {
        int weight = cardDropGroup.getWeightSum();
        int random = new Random().nextInt(weight);
        int v = 0;
        for (CardRewardItemCO cardItem : cardDropGroup.getCardDropList()) {
            v += cardItem.getWeight();
            if (random < v)
                return cardItem.getCardId();
        }
        throw new RandomRewardException(cardDropGroup.toString());
    }


}


