package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CaptainLevelCO;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.service.CaptainLevelCache;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.jupiter.dao.CaptainLevelDAO;
import com.h13.cardgame.jupiter.dao.CardDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-10
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptainLevelLoaderService {

    private static Log LOG = LogFactory.getLog(CaptainLevelLoaderService.class);

    @Autowired
    CaptainLevelDAO captainLevelDAO;
    @Autowired
    CaptainLevelCache captainLevelCache;

    public void load() throws LoadException {
        try {
            List<CaptainLevelCO> list = captainLevelDAO.getAll();
            for (CaptainLevelCO cl : list) {
                captainLevelCache.put(cl);
                LOG.info("loaded captain level info. " + cl);
            }
            LOG.info("load captain level info successfully.");
        } catch (Exception e) {
            throw new LoadException("load captain level info error", e);
        }
    }

}
