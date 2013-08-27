package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CityLevelCO;
import com.h13.cardgame.cache.service.LevelCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.jupiter.dao.CityLevelDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-11
 * Time: 下午6:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LevelLoaderService {
    private static Log LOG = LogFactory.getLog(LevelLoaderService.class);
    @Autowired
    CityLevelDAO cityLevelDAO;

    @Autowired
    LevelCache levelCache;

    public void load() throws LoadException {
        try {
            List<CityLevelCO> levelList = cityLevelDAO.getAllLevels();
            for (CityLevelCO level : levelList) {
                levelCache.put(level);
                LOG.info("Loaded level "+level.toString());
            }
            LOG.info("load level info successfully.");
        } catch (Exception e) {
            throw new LoadException("load task info error");
        }
    }

}
