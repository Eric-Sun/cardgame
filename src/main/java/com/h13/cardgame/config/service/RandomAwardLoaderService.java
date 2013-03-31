package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.RandomAwardCO;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.cache.service.RandomAwardCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.core.dao.CardDAO;
import com.h13.cardgame.core.dao.RandomAwardDAO;
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
public class RandomAwardLoaderService {

    private static Log LOG = LogFactory.getLog(RandomAwardLoaderService.class);

    @Autowired
    RandomAwardDAO raDAO;
    @Autowired
    RandomAwardCache raCache;

    public void load() throws LoadException {
        try {
            List<RandomAwardCO> list = raDAO.getAll();
            for (RandomAwardCO ra : list) {
                raCache.put(ra);
                LOG.info("loaded randomAward info. " + ra);
            }
            LOG.info("load randomAward info successfully.");
        } catch (Exception e) {
            throw new LoadException("load randomAward info error", e);
        }
    }

}
