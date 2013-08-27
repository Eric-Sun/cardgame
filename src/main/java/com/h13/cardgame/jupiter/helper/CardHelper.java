package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.cache.service.UnitsCardCache;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.config.service.UnitsCardLoaderService;
import com.h13.cardgame.jupiter.dao.CardDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-27
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CardHelper {
    private static Log LOG = LogFactory.getLog(CardHelper.class);

    @Autowired
    CardCache cardCache;
    @Autowired
    CardDAO cardDAO;
    @Autowired
    StorageHelper storageHelper;
    @Autowired
    ConfigHelper configHelper;
    @Autowired
    UnitsCardLoaderService unitsCardLoaderService;

    @Autowired
    UnitsCardCache unitsCardCache;

    public CardCO get(long cardId) {
        CardCO card = cardCache.get(cardId);
        if (card == null) {
            card = cardDAO.get(cardId);
            cardCache.put(card);
        }
        LOG.debug("loaded card. " + card);
        return card;
    }


    /**
     * 获得card对象存储的特殊的内容
     * <p/>
     * </P>
     *
     * @param card
     * @param key
     * @return
     */
    public int getCardSpecData(CardCO card, String key) {
        String value = card.getSpecData().get(key);
        if (value == null)
            throw new IllegalArgumentException("card dont have the data. key=" + key + " cardId=" + card.getId());
        else
            return new Integer(card.getSpecData().get(key));
    }

    public List<UnitsCardCO> getAllUnitsCards() throws LoadException {
        List<UnitsCardCO> list = unitsCardCache.getAll();
        if (null == list) {
            list = unitsCardLoaderService.load();
        }
        return list;
    }
}
