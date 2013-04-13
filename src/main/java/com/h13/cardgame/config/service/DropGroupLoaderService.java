package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.DropGroupCO;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.cache.service.DropGroupCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.core.dao.CardDAO;
import com.h13.cardgame.core.dao.DropGroupDAO;
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
public class DropGroupLoaderService {

    private static Log LOG = LogFactory.getLog(DropGroupLoaderService.class);

    @Autowired
    DropGroupDAO dropGroupDAO;
    @Autowired
    DropGroupCache dropGroupCache;

    public void load() throws LoadException {
        try {
            List<DropGroupCO> list = dropGroupDAO.getAll();
            for (DropGroupCO dg : list) {
                dropGroupCache.put(dg);
                LOG.info("loaded dropGroup info. " + dg);
            }
            LOG.info("load dropGroup info successfully.");
        } catch (Exception e) {
            throw new LoadException("load dropGroup info error", e);
        }
    }

}
