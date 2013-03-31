package com.h13.cardgame.core.helper;

import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.core.dao.CardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-27
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CardHelper {

    @Autowired
    CardCache cardCache;
    @Autowired
    CardDAO cardDAO;

    public CardCO get(long cardId) {
        CardCO card = cardCache.get(cardId);
        if (card == null) {
            card = cardDAO.get(cardId);
            cardCache.put(card);
        }
        return card;
    }


}
