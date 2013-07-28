package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CaptainLevelCO;
import com.h13.cardgame.cache.co.CaptainTitleCO;
import com.h13.cardgame.cache.service.CaptainLevelCache;
import com.h13.cardgame.cache.service.CaptainTitleCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.jupiter.dao.CaptainLevelDAO;
import com.h13.cardgame.jupiter.dao.CaptainTitleDAO;
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
public class CaptainTitleLoaderService {

    private static Log LOG = LogFactory.getLog(CaptainTitleLoaderService.class);

    @Autowired
    CaptainTitleDAO captainTitleDAO;
    @Autowired
    CaptainTitleCache captainTitleCache;

    public void load() throws LoadException {
        try {
            List<CaptainTitleCO> list = captainTitleDAO.getAll();
            for (CaptainTitleCO ct : list) {
                captainTitleCache.put(ct);
                LOG.info("loaded captain title info. " + ct);
            }
            LOG.info("load captain title info successfully.");
        } catch (Exception e) {
            throw new LoadException("load captain title info error", e);
        }
    }

}
