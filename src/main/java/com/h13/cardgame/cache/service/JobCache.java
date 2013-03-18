package com.h13.cardgame.cache.service;

import com.h13.cardgame.cache.co.JobDetailCO;
import com.h13.cardgame.cache.co.LevelCO;
import com.h13.cardgame.core.utils.JobUtils;
import com.h13.cardgame.scheduler.JobType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-6
 * Time: 上午1:47
 * To change this template use File | Settings | File Templates.
 */
@Service
public class JobCache {

    private static String PREFIX = "job:";

    @Resource(name = "redisStringJobDetailCOTemplate")
    private RedisTemplate<String, JobDetailCO> jobCOTemplate;

    /**
     * 往缓存中存入一个定时器任务
     *
     * @param cid
     * @param detail
     * @return
     */
    public boolean put(long cid, JobDetailCO detail) {
        String key = PREFIX + detail.getJobType().name() + ":" + cid;
        jobCOTemplate.opsForList().rightPush(key, detail);
        return true;
    }

    /**
     * 获得这个cid，某一个jobType的所有的定时器任务
     *
     * @param cid
     * @return
     */
    public List<JobDetailCO> get(long cid, JobType jobType) {
        String key = PREFIX + jobType.name() + ":" + cid;
        return jobCOTemplate.opsForList().range(key, 0, jobCOTemplate.opsForList().size(key)-1);
    }


    /**
     * 获得所有的定时任务，返回的是一个map
     *
     * @param cid
     * @return
     */
    public Map<JobType, List<JobDetailCO>> get(long cid) {
        Map<JobType, List<JobDetailCO>> finalMap = new HashMap<JobType, List<JobDetailCO>>();
        JobType[] jobTypeArr = JobType.values();
        for (JobType jobType : jobTypeArr) {
            List<JobDetailCO> list = this.get(cid, jobType);
            finalMap.put(jobType, list);
        }
        return finalMap;
    }

}
