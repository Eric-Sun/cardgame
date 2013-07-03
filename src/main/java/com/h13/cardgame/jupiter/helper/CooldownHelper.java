package com.h13.cardgame.jupiter.helper;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.TaskCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.dao.CityDAO;
import com.h13.cardgame.jupiter.exceptions.TaskIsCooldownException;
import com.h13.cardgame.jupiter.exceptions.TaskIsNotExistsException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来进行冷却时间的相关处理
 * 当冷却的时候，将冷却的状态等信息绑定到city上
 * 当需要尝试查看冷却的状态的时候（是否冷却结束，是否还继续冷却）会更新冷却的状态
 * User: sunbo
 * Date: 13-7-3
 * Time: 上午11:24
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CooldownHelper {
    private static Log LOG = LogFactory.getLog(CooldownHelper.class);
    private static String TASK_KEY = "TASK:";
    private static String ENERGY_KEY = "ENERGY:";

    @Autowired
    ConfigHelper configHelper;
    @Autowired
    TaskHelper taskHelper;
    @Autowired
    CityHelper cityHelper;
    @Autowired
    CityDAO cityDAO;

    /**
     * 完成一个任务的时候，添加这个任务的冷却时间
     * key为TASK:{taskId} value为冷却的开始时间(ms)
     *
     * @param city
     * @param task
     */
    public void addTaskCooldown(CityCO city, TaskCO task) throws TaskIsCooldownException {
        long currentTimeStamp = System.currentTimeMillis();
        // 判断任务是否在冷却
        if (city.getTaskStatus().getTaskMap().get(task.getId() + "") != null &&
                city.getTaskStatus().getTaskMap().get(task.getId() + "").isCanBeDo() == false) {
            // 当为false的时候，正在冷却
            throw new TaskIsCooldownException("task is cooldown. can't add cooldown status. taskId=" + task.getId());
        }
        city.getCooldownStatus().put(TASK_KEY + task.getId(), currentTimeStamp + "");
        cityDAO.updateCooldownStatus(city.getId(), JSON.toJSONString(city.getCooldownStatus()));
    }

    /**
     * 尝试添加能量的冷却，如果不够1个能量的时间，则什么都不做，否则更新timestamp，并且把能量加上
     *
     * @param city
     */
    public void attemptAddEnergyCooldown(CityCO city) throws UserNotExistsException {
        long currentTimeStamp = System.currentTimeMillis();
        if (city.getCooldownStatus().get(ENERGY_KEY + city.getId()) == null) {
            // 没有添加过冷却
            city.getCooldownStatus().put(ENERGY_KEY + city.getId(), currentTimeStamp + "");
            cityDAO.updateCooldownStatus(city.getId(), JSON.toJSONString(city.getCooldownStatus()));
            LOG.debug("flush energy. just add cooldown. cid=" + city.getId());
            return;
        }
        long lastTimeStamp = new Long(city.getCooldownStatus().get(ENERGY_KEY + city.getId()));
        if (city.getCooldownStatus().get(ENERGY_KEY + city.getId()) == null) {
            // 以前出现错误，能量不够，但是又没有冷却
            // 直接加上
            city.getCooldownStatus().put(ENERGY_KEY + city.getId(), currentTimeStamp + "");
            LOG.debug("flush energy. exp is full.");
            return;
        }
        if (currentTimeStamp - lastTimeStamp < new Long(configHelper.get(Configuration.CORE.ENERGY_UP_S))) {
            // 不够添加1个能量的，返回
            LOG.debug("flush energy. energy is not enough to add 1. return");
            return;
        } else {
            int energyWillAdd = new Long((currentTimeStamp - lastTimeStamp)
                    / new Long(configHelper.get(Configuration.CORE.ENERGY_UP_S))).intValue();
            // 添加能量
            boolean isFull = cityHelper.addEnergy(city, energyWillAdd);
            if (!isFull)
                city.getCooldownStatus().put(ENERGY_KEY + city.getId(), currentTimeStamp + "");
            cityDAO.updateCooldownStatus(city.getId(), JSON.toJSONString(city.getCooldownStatus()));
            LOG.debug("flush energy. add energy=" + energyWillAdd);
        }
    }


    public void removeEnerygyCooldown(CityCO city) {
        city.getCooldownStatus().remove(ENERGY_KEY + city.getId());
        cityDAO.updateCooldownStatus(city.getId(), JSON.toJSONString(city.getCooldownStatus()));
    }


    /**
     * 查看有哪些task的冷却可以结束，并且把他们的状态改回可以继续完成额
     *
     * @param city
     * @return
     */
    public void tryToFinishTaskCooldown(CityCO city) throws TaskIsNotExistsException {
        Map<String, String> statusMap = city.getCooldownStatus();
        Map<String, String> map2 = new HashMap<String, String>(statusMap);
        for (String key : statusMap.keySet()) {
            if (key.indexOf(TASK_KEY) >= 0) {
                // 依次判断是否可以去掉冷却
                String[] arr = key.split(":");
                long taskId = new Long(arr[arr.length - 1]);
                TaskCO task = taskHelper.getTask(taskId);
                long cooldownTimeStamp = task.getCooldown();
                long currentTimeStamp = System.currentTimeMillis();
                long lastTimeStamp = new Long(statusMap.get(key));
                if (currentTimeStamp - lastTimeStamp >= cooldownTimeStamp) {
                    // 冷却时间已经到
                    map2.remove(key);
                    LOG.debug("cooldown is over. taskId=" + task.getId());
                    taskHelper.resumeTask(city, task);
                }

            } else {
                continue;
            }
        }
        city.setCooldownStatus(map2);
        cityDAO.updateCooldownStatus(city.getId(), JSON.toJSONString(city.getCooldownStatus()));
    }

}
