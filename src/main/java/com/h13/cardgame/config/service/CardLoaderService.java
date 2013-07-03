package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.config.exception.LoadException;
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
public class CardLoaderService {

    private static Log LOG = LogFactory.getLog(CardLoaderService.class);

    @Autowired
    CardDAO cardDAO;
    @Autowired
    CardCache cardCache;

    public void load() throws LoadException {
        try {
            List<CardCO> list = cardDAO.getAll();
            for (CardCO card : list) {
                cardCache.putToQueue(card);
                LOG.info("loaded card info. " + card);
            }
            LOG.info("load card info successfully.");
        } catch (Exception e) {
            throw new LoadException("load card info error", e);
        }
    }

}
