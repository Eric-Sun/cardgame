package com.h13.cardgame.config;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-8
 * Time: 下午11:30
 * To change this template use File | Settings | File Templates.
 */

import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.config.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 在初始化的时候把一些数据库的配置放到缓存中
 */
@Service
public class ConfigStarter {
    private static Log LOG = LogFactory.getLog(ConfigStarter.class);
    @Autowired
    LevelLoaderService levelLoaderService;
    @Autowired
    TaskLoaderService taskLoaderService;
    @Autowired
    ConfigLoaderService configLoaderService;
    @Autowired
    CardLoaderService cardLoaderService;
    @Autowired
    DropGroupLoaderService dropGroupLoaderService;

    public void init() {
        try {
            // 添加任务相关的信息到缓存中
            taskLoaderService.load();
            levelLoaderService.load();
            configLoaderService.load();
            cardLoaderService.load();
            dropGroupLoaderService.load();
            LOG.info("load all info successfully");
        } catch (Exception e) {
            LOG.error("load all info error.", e);
        }
    }


}
