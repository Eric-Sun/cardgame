package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.ConfigCO;
import com.h13.cardgame.cache.service.ConfigurationCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.jupiter.dao.ConfigDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-15
 * Time: 下午5:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ConfigLoaderService {
    private static Log LOG = LogFactory.getLog(LevelLoaderService.class);
    @Autowired
    ConfigDAO configDAO;
    @Autowired
    ConfigurationCache confCache;

    public void load() throws LoadException {
        List<ConfigCO> list = configDAO.loadAll();
        for (ConfigCO conf : list) {
            confCache.put(conf.getKey(), conf.getValue());
            LOG.info("load config. " + conf.toString());
        }
        LOG.info("Load all config successfully.");
    }
}
