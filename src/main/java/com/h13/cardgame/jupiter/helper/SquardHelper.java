package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-24
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SquardHelper {
    @Autowired
    CityCardHelper cityCardHelper;

    /**
     * 检查是否有队长城市卡，如果有返回true，如果没有返回false
     *
     * @return
     * @throws CityCardNotExistsException
     * @throws CityCardIsNotYoursException
     */
    public boolean haveCaptainCityCard(CityCardCO cityCardCO) {
        if (DataUtils.getSquardIntData(cityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_ID_KEY)
                == Configuration.SQUARD_CITY_CARD.DEFAULT_CAPTAIN_ID_VALUE) {
            return false;
        } else
            return true;
    }

    /**
     * 设置captain
     */
    public void onCaptainCityCard(CityCardCO squardCityCardCO, CityCardCO captainCityCardCO) {
        DataUtils.putSquardStringData(squardCityCardCO.getData(),
                Configuration.SQUARD_CITY_CARD.CAPTAIN_ID_KEY, captainCityCardCO.getId());

        int captainAttackMin = DataUtils.getSquardIntData(captainCityCardCO.getData(), Configuration.CAPTAIN_CITY_CARD.ATTACK_MIN_KEY);
        int captainAttackMax = DataUtils.getSquardIntData(captainCityCardCO.getData(), Configuration.CAPTAIN_CITY_CARD.ATTACK_MAX_KEY);
        int captainDefenceMin = DataUtils.getSquardIntData(captainCityCardCO.getData(), Configuration.CAPTAIN_CITY_CARD.DEFENCE_MIN_KEY);
        int captainDefenceMax = DataUtils.getSquardIntData(captainCityCardCO.getData(), Configuration.CAPTAIN_CITY_CARD.DEFENCE_MAX_KEY);

        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_ATTACK_MAX_KEY, captainAttackMax);
        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_ATTACK_MIN_KEY, captainAttackMin);
        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_DEFENCE_MAX_KEY, captainDefenceMax);
        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_DEFENCE_MIN_KEY, captainDefenceMin);

    }

    /**
     * 设置captain
     */
    public void offCaptainCityCard(CityCardCO squardCityCardCO) {
        DataUtils.putSquardStringData(squardCityCardCO.getData(),
                Configuration.SQUARD_CITY_CARD.CAPTAIN_ID_KEY, Configuration.SQUARD_CITY_CARD.DEFAULT_CAPTAIN_ID_VALUE);

        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_ATTACK_MAX_KEY,
                Configuration.SQUARD_CITY_CARD.CAPTAIN_INIT_ATTACK_MAX);
        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_ATTACK_MIN_KEY,
                Configuration.SQUARD_CITY_CARD.CAPTAIN_INIT_ATTACK_MIN);
        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_DEFENCE_MAX_KEY,
                Configuration.SQUARD_CITY_CARD.CAPTAIN_INIT_DEFENCE_MAX);
        DataUtils.putSquardLongData(squardCityCardCO.getData(), Configuration.SQUARD_CITY_CARD.CAPTAIN_DEFENCE_MIN_KEY,
                Configuration.SQUARD_CITY_CARD.CAPTAIN_INIT_DEFENCE_MIN);

    }
}
