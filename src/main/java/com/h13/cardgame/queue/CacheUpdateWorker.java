package com.h13.cardgame.queue;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.cache.service.*;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.utils.WebApplicationContentHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-2
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CacheUpdateWorker {
    private static Log LOG = LogFactory.getLog(CacheUpdateWorker.class);
    @Autowired
    CacheUpdateQueue cacheUpdateQueue;
    @Autowired
    CardCache cardCache;
    @Autowired
    CityCache cityCache;
    @Autowired
    CityCardCache cityCardCache;
    @Autowired
    ConfigurationCache configurationCache;
    @Autowired
    LevelCache levelCache;
    @Autowired
    SchedulerCache schedulerCache;
    @Autowired
    DropGroupCache dropGroupCache;
    @Autowired
    StorageCache storageCache;
    @Autowired
    TaskCache taskCache;
    @Autowired
    TaskGroupCache taskGroupCache;
    @Autowired
    TroopCache troopCache;


    public void doProcess() {
        CacheUpdateMessage message = cacheUpdateQueue.peek();
        if (message == null)
            return;
        LOG.debug("cache queue process. m=" + message);
        switch (message.getType()) {
            case QUEUE_CARD_KEY:
                cardCache.put(JSON.parseObject(message.getData(), CardCO.class));
                break;
            case QUEUE_CITY_KEY:
                cityCache.put(JSON.parseObject(message.getData(), CityCO.class));
                break;
            case QUEUE_CITYCARD_KEY:
                cityCardCache.put(JSON.parseObject(message.getData(), CityCardCO.class));
                break;
            case QUEUE_CONFIGURATION_KEY:
                configurationCache.put(JSON.parseObject(message.getData(), String.class));
                break;
            case QUEUE_DROPGROUP_KEY:
                dropGroupCache.put(JSON.parseObject(message.getData(), DropGroupCO.class));
                break;
            case QUEUE_LEVEL_KEY:
                levelCache.put(JSON.parseObject(message.getData(), LevelCO.class));
                break;
            case QUEUE_SCHEDULER_KEY:
                schedulerCache.put(JSON.parseObject(message.getData(), SchedulerMessage.class));
                break;
            case QUEUE_STORAGE_KEY:
                storageCache.put(JSON.parseObject(message.getData(), StorageCO.class));
                break;
            case QUEUE_TASK_KEY:
                taskCache.put(JSON.parseObject(message.getData(), TaskCO.class));
                break;
            case QUEUE_TASKGROUP_KEY:
                taskGroupCache.put(JSON.parseObject(message.getData(), TaskGroupCO.class));
                break;
            case QUEUE_TROOP_KEY:
                troopCache.put(JSON.parseObject(message.getData(), TroopCO.class));
                break;
        }
    }
}
