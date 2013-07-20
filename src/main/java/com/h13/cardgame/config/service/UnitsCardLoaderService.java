package com.h13.cardgame.config.service;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.UnitsCardCO;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.cache.service.UnitsCardCache;
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
public class UnitsCardLoaderService {

    private static Log LOG = LogFactory.getLog(UnitsCardLoaderService.class);

    @Autowired
    CardDAO cardDAO;
    @Autowired
    UnitsCardCache unitsCardCache;

    public List<UnitsCardCO> load() throws LoadException {
        try {
            List<UnitsCardCO> list = cardDAO.getAllUnitsCards();
            for (UnitsCardCO card : list) {
                unitsCardCache.put(card);
                LOG.info("loaded units card info. " + card);
            }
            LOG.info("load units card info successfully.");
            return list;
        } catch (Exception e) {
            throw new LoadException("load card info error", e);
        }
    }

}
