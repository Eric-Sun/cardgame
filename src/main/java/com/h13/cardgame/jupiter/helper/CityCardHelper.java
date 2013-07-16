package com.h13.cardgame.jupiter.helper;

import com.alibaba.fastjson.JSONObject;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.service.CityCardCache;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.CardType;
import com.h13.cardgame.jupiter.dao.CityCardDAO;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.utils.LogWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CityCardHelper {
    @Autowired
    CityCardDAO cityCardDAO;
    @Autowired
    CityCardCache cityCardCache;

    public CityCardCO get(long cityId, long cityCardId) throws CityCardNotExistsException,
            CityCardIsNotYoursException {
        CityCardCO cc = cityCardCache.get(cityCardId);

        if (cc == null) {
            cc = cityCardDAO.get(cityCardId);
            if (cc == null)
                throw new CityCardNotExistsException("cityCard not exists. cityCardId=" + cityCardId);
            cityCardCache.put(cc);
        }
        if (cc.getCityId() != cityId) {
           throw new CityCardIsNotYoursException("cityId="+cityId+" cityCardId="+cityCardId);
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
        cityCardDAO.updateData(cc);
    }

    public void updateUCardId(CityCardCO squardCityCard, long uCardId) {
        putSquardLongData(squardCityCard.getData(), Configuration.CITY_CARD.U_CARD_ID_KEY, uCardId);
        cityCardDAO.updateData(squardCityCard);
    }


    public long getSquardLongData(Map data, String key) {
        return new Long(((Map<String, String>) data).get(key).toString());
    }

    public int getSquardIntData(Map data, String key) {
        return new Integer(((Map<String, String>) data).get(key).toString());
    }

    public String getSquardStringData(Map data, String key) {
        return ((Map<String, String>) data).get(key).toString();
    }

    public void putSquardLongData(Map data, String key, Object value) {
        ((Map<String, String>) data).put(key, value.toString());
    }

    public void putSquardStringData(Map data, String key, Object value) {
        ((Map<String, String>) data).put(key, value.toString());
    }

}
