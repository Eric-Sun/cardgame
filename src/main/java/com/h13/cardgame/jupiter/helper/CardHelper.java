package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.service.CityCardCache;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.jupiter.dao.CityCardDAO;
import com.h13.cardgame.jupiter.dao.CardDAO;
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
    @Autowired
    CityCardDAO cityCardDAO;
    @Autowired
    CityCardCache cityCardCache;

    public CardCO get(long cardId) {
        CardCO card = cardCache.get(cardId);
        if (card == null) {
            card = cardDAO.get(cardId);
            cardCache.put(card);
        }
        return card;
    }


    public void addCardToCaptain(CityCO captain, CardCO card) {
        long id = cityCardDAO.add(captain.getId(), card);
        CityCardCO captainCard = new CityCardCO();
        captainCard.setId(id);
        captainCard.setAttackMax(card.getAttackMax());
        captainCard.setAttackMin(card.getAttackMin());
        captainCard.setCardId(card.getId());
        captainCard.setDefenceMax(card.getDefenceMax());
        captainCard.setDefenceMin(card.getDefenceMin());
        captainCard.setImg(card.getImg());
        captainCard.setLevel(1);
        captainCard.setName(card.getName());
        cityCardCache.put(captainCard);
    }


    public CityCardCO getCaptianCard(long ccId) {
        CityCardCO cc = cityCardCache.get(ccId);
        if (cc == null) {
            cc = cityCardDAO.get(ccId);
            cityCardCache.put(cc);
        }
        return cc;
    }


}
