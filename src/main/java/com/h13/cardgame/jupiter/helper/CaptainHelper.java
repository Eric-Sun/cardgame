package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CaptainLevelCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.SkillCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.exceptions.CaptainCityCardLevelIsTopException;
import com.h13.cardgame.jupiter.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-23
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptainHelper {

    @Autowired
    CityCardHelper cityCardHelper;
    @Autowired
    CaptainLevelHelper captainLevelHelper;
    @Autowired
    SkillHelper skillHelper;
    public void upgradeLevel(CityCardCO cityCard, int addExp) throws CaptainCityCardLevelIsTopException {
        int currentLevel = DataUtils.getSquardIntData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.LEVEL_KEY);
        int currentLevelExp = DataUtils.getSquardIntData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.TITLE_EXP_KEY);
        int finalLevelExp = currentLevelExp + addExp;
        int finalLevel = findUpgradedLevel(currentLevel, finalLevelExp);
        DataUtils.putSquardLongData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.LEVEL_KEY, finalLevel);
        DataUtils.putSquardLongData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.LEVEL_EXP_KEY, finalLevelExp);
    }

    private int findUpgradedLevel(int currentLevel, int finalLevelExp) throws CaptainCityCardLevelIsTopException {
        while (true) {
            CaptainLevelCO captainLevelCO = captainLevelHelper.get(currentLevel);
            if (captainLevelCO == null)
                throw new CaptainCityCardLevelIsTopException("");
            if (captainLevelCO.getExp() > finalLevelExp)
                return captainLevelCO.getId();
            currentLevel++;
        }
    }

    public SkillCO getSkill(CityCardCO captainCityCard) {
        long skillId = DataUtils.getSquardLongData(captainCityCard.getData(), Configuration.CAPTAIN_CITY_CARD.SKILL_ID_KEY);
        SkillCO skillCO = skillHelper.get(skillId);
        return skillCO;
    }

}
