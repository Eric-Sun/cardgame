package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.service.CityCardCache;
import com.h13.cardgame.jupiter.dao.CityCardDAO;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-5-29
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CityCardHelper {
    @Autowired
    CityCardDAO cityCardDAO;
    @Autowired
    CityCardCache cityCardCache;

    public CityCardCO get(long ccId) throws CityCardNotExistsException {
        CityCardCO cc = cityCardCache.get(ccId);
        if (cc == null) {
            cc = cityCardDAO.get(ccId);
            if (cc == null)
                throw new CityCardNotExistsException("cityCard not exists. cityCardId=" + ccId);
            cityCardCache.put(cc);
        }
        return cc;
    }

    public void create(CityCardCO cityCardCO) {
        cityCardDAO.add(cityCardCO);
        LogWriter.debug(LogWriter.TASK, "create cityCard", cityCardCO);
    }

    public void cache(CityCardCO cityCardCO) {
        cityCardCache.put(cityCardCO);
    }

    public void updateAttributes(CityCardCO cc) {
        cityCardDAO.updateAttributes(cc);
    }

    public void updateUCardId(CityCardCO squardCityCard, long uCardId) {
        cityCardDAO.updateUCardId(squardCityCard.getId(), uCardId);
    }
}
