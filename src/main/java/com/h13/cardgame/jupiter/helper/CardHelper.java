package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.cache.service.CityCardCache;
import com.h13.cardgame.cache.service.CardCache;
import com.h13.cardgame.cache.service.UnitsCardCache;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.config.exception.LoadException;
import com.h13.cardgame.config.service.UnitsCardLoaderService;
import com.h13.cardgame.jupiter.CardType;
import com.h13.cardgame.jupiter.dao.CityCardDAO;
import com.h13.cardgame.jupiter.dao.CardDAO;
import com.h13.cardgame.jupiter.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        return card;
    }


    /**
     * 添加一张军备牌，存在军备仓库中
     *
     * @param city
     * @param card
     */
    public void addEquipmentCard(CityCO city, CardCO card) {
        // add to package
        StorageCO storageCO = storageHelper.getByCid(city.getId());
        storageHelper.addToEquipmentPackage(city.getUserId(), city.getId(), card.getId(), storageCO);
        storageHelper.cache(storageCO);
    }


    /**
     * 添加一张小队牌
     *
     * @param city
     * @param card
     */
    public void addSquardCard(CityCO city, CardCO card) {
        CityCardCO cityCard = new CityCardCO();
        cityCard.setCardId(card.getId());
        cityCard.setCardType(card.getCardType());
        cityCard.setData(new HashMap<String, String>());

        // 初始化data
        cityCardHelper.putSquardStringData(cityCard.getData(), Configuration.CITY_CARD.ATTACK_MAX_KEY, 0);
        cityCardHelper.putSquardStringData(cityCard.getData(), Configuration.CITY_CARD.ATTACK_MIN_KEY, 0);
        cityCardHelper.putSquardStringData(cityCard.getData(), Configuration.CITY_CARD.DEFENCE_MAX_KEY, 0);
        cityCardHelper.putSquardStringData(cityCard.getData(), Configuration.CITY_CARD.DEFENCE_MIN_KEY, 0);
        cityCardHelper.putSquardStringData(cityCard.getData(), Configuration.CITY_CARD.U_CARD_ID_KEY, Configuration.SQUARD.DEFAULT_SQUARD_U_CARD_ID_VALUE);


        cityCardHelper.putSquardLongData(cityCard.getData(), Configuration.CITY_CARD.CUR_SLOT_KEY, 0);
        cityCard.setIcon(card.getIcon());
        cityCard.setName(card.getName());
        cityCard.setCityId(city.getId());
        cityCardHelper.putSquardLongData(cityCard.getData(), Configuration.CITY_CARD.CUR_SLOT_KEY,
                Configuration.SQUARD.DEFAULT_SQUARD_U_CARD_ID_VALUE);
        cityCard.setCardType(CardType.SQUARD);
        cityCardHelper.putSquardLongData(cityCard.getData(), Configuration.CITY_CARD.MAX_SLOT_KEY, RandomUtils.random(getCardSpecData(card, Configuration.CARD.MIN_SLOT_KEY),
                getCardSpecData(card, Configuration.CARD.MAX_SLOT_KEY)));
        cityCardHelper.create(cityCard);
        // add to package
        StorageCO storageCO = storageHelper.getByCid(city.getId());
        storageHelper.addToSquardPackage(city.getUserId(), city.getId(), card.getId(), cityCard.getId(), storageCO);
        storageHelper.cache(storageCO);
        cityCardHelper.cache(cityCard);
    }

    /**
     * 获得card对象存储的特殊的内容
     * <p/>
     * squard: maxSlot minSlot
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

    public void addCaptainCard(CityCO city, CardCO card) {
        StorageCO storageCO = storageHelper.getByCid(city.getId());
        CityCardCO cityCard = new CityCardCO();
        cityCard.setCardId(card.getId());
        cityCard.setCardType(card.getCardType());
        cityCard.setData(new HashMap<String, String>());
        cityCard.setIcon(card.getIcon());
        cityCard.setName(card.getName());
        cityCard.setCityId(city.getId());
        cityCardHelper.create(cityCard);
        storageHelper.addToCaptainPackage(city.getUserId(), city.getId(),
                card.getId(), cityCard.getId(), storageCO);
        cityCardHelper.cache(cityCard);
        storageHelper.cache(storageCO);
    }


    public List<UnitsCardCO> getAllUnitsCards() throws LoadException {
        List<UnitsCardCO> list = unitsCardCache.getAll();
        if (null == list) {
            list = unitsCardLoaderService.load();
        }
        return list;
    }
}
