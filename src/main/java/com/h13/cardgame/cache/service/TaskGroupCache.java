package com.h13.cardgame.cache.service;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.TaskCO;
import com.h13.cardgame.cache.co.TaskGroupCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TaskGroupCache {

    private static String PREFIX = "cg:system:taskGroup:";

    @Resource(name = "redisStringTaskGroupCOTemplate")
    private RedisTemplate<String, TaskGroupCO> taskGroupCOTemplate;

    public void put(TaskGroupCO taskGroup) {
        String key = PREFIX + taskGroup.getId();
        taskGroupCOTemplate.opsForValue().set(key, taskGroup);
    }


    public TaskGroupCO get(long taskGroupId) {
        String key = PREFIX + taskGroupId;
        TaskGroupCO co = taskGroupCOTemplate.opsForValue().get(key);
        return co;
    }

    public List<TaskGroupCO> list() {
        List<TaskGroupCO> list = new ArrayList<TaskGroupCO>();
        Set<String> keySet = taskGroupCOTemplate.keys("*"+PREFIX+"*");
        for (String key : keySet) {
            list.add(taskGroupCOTemplate.opsForValue().get(key));
        }
        return list;
    }

}
