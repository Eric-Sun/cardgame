package com.h13.cardgame.core.helper;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.CaptainCardCO;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.service.CaptainCardCache;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.core.dao.CaptainCardDAO;
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
    @Autowired
    CaptainCardDAO captainCardDAO;
    @Autowired
    CaptainCardCache captainCardCache;

    public CardCO get(long cardId) {
        CardCO card = cardCache.get(cardId);
        if (card == null) {
            card = cardDAO.get(cardId);
            cardCache.put(card);
        }
        return card;
    }


    public void addCardToCaptain(CaptainCO captain, CardCO card) {
        long id = captainCardDAO.add(captain.getId(), card);
        CaptainCardCO captainCard = new CaptainCardCO();
        captainCard.setId(id);
        captainCard.setAttackMax(card.getAttackMax());
        captainCard.setAttackMin(card.getAttackMin());
        captainCard.setCardId(card.getId());
        captainCard.setDefenceMax(card.getDefenceMax());
        captainCard.setDefenceMin(card.getDefenceMin());
        captainCard.setImg(card.getImg());
        captainCard.setLevel(1);
        captainCard.setName(card.getName());
        captainCardCache.put(captainCard);
    }


    public CaptainCardCO getCaptianCard(long ccId) {
        CaptainCardCO cc = captainCardCache.get(ccId);
        if (cc == null) {
            cc = captainCardDAO.get(ccId);
            captainCardCache.put(cc);
        }
        return cc;
    }


}
