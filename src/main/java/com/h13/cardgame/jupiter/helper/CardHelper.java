package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.cache.service.CityCardCache;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.jupiter.dao.CityCardDAO;
import com.h13.cardgame.jupiter.dao.CardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

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
    StorageHelper storageHelper;
    @Autowired
    CityCardHelper cityCardHelper;

    public CardCO get(long cardId) {
        CardCO card = cardCache.get(cardId);
        if (card == null) {
            card = cardDAO.get(cardId);
            cardCache.put(card);
        }
        return card;
    }


    /**
     * 添加一张装备牌
     *
     * @param city
     * @param card
     */
    public void addEquipmentCardToCaptain(CityCO city, CardCO card) {
        CityCardCO cityCard = new CityCardCO();
        cityCard.setCardId(card.getId());
        cityCard.setAttackMax(card.getAttackMax());
        cityCard.setAttackMin(card.getAttackMin());
        cityCard.setDefenceMax(card.getDefenceMax());
        cityCard.setDefenceMin(card.getDefenceMin());
        cityCard.setBaseAttackMax(card.getAttackMax());
        cityCard.setBaseAttackMin(card.getAttackMin());
        cityCard.setBaseDefenceMax(card.getDefenceMax());
        cityCard.setBaseDefenceMin(card.getDefenceMin());
        cityCard.setCardType(card.getCardType());
        cityCard.setCurSlot(0);
        cityCard.setIcon(card.getIcon());
        cityCard.setName(card.getName());
        cityCard.setCityId(city.getId());
        cityCardHelper.create(cityCard);
        // add to package
        StorageCO storageCO = storageHelper.getByCid(city.getId());
        storageHelper.addToPackage(city.getUserId(), city.getId(), card.getId(), cityCard.getId(), storageCO);
        storageHelper.cache(storageCO);
        cityCardHelper.cache(cityCard);
    }


    public void addHumanCardToCaptain(CityCO city, CardCO card) {
        CityCardCO cityCard = new CityCardCO();
        cityCard.setCardId(card.getId());
        cityCard.setAttackMax(card.getAttackMax());
        cityCard.setAttackMin(card.getAttackMin());
        cityCard.setDefenceMax(card.getDefenceMax());
        cityCard.setDefenceMin(card.getDefenceMin());
        cityCard.setBaseAttackMax(card.getAttackMax());
        cityCard.setBaseAttackMin(card.getAttackMin());
        cityCard.setBaseDefenceMax(card.getDefenceMax());
        cityCard.setBaseDefenceMin(card.getDefenceMin());
        cityCard.setCardType(card.getCardType());
        cityCard.setCurSlot(0);
        cityCard.setIcon(card.getIcon());
        cityCard.setName(card.getName());
        cityCard.setCityId(city.getId());
        Random random = new Random();
        cityCard.setMaxSlot(random.nextInt(card.getRandomSlotCount()));
        cityCardHelper.create(cityCard);
        // add to package
        StorageCO storageCO = storageHelper.getByCid(city.getId());
        storageHelper.addToPackage(city.getUserId(), city.getId(), card.getId(), cityCard.getId(), storageCO);
        storageHelper.cache(storageCO);
        cityCardHelper.cache(cityCard);
    }
}
