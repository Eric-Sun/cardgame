package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.SkillCO;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.exceptions.CaptainCityCardLevelIsTopException;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.helper.CaptainHelper;
import com.h13.cardgame.jupiter.helper.CityCardHelper;
import com.h13.cardgame.jupiter.helper.StorageHelper;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.DataUtils;
import com.h13.cardgame.jupiter.vo.CaptainCityCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CaptainService {

    @Autowired
    CityCardHelper cityCardHelper;

    @Autowired
    StorageHelper storageHelper;
    @Autowired
    CaptainHelper captainHelper;

    /**
     * 获得一个captain卡
     *
     * @param cid
     * @param uid
     * @param captianCityCardId
     * @return
     * @throws CityCardNotExistsException
     */
    public CaptainCityCardVO get(long uid, long cid, long captianCityCardId)
            throws CityCardNotExistsException, CityCardIsNotYoursException {
        CityCardCO cityCard = cityCardHelper.get(cid, captianCityCardId);
        SkillCO skillCO = captainHelper.getSkill(cityCard);
        return DTOUtils.toCaptainCityCardVO(cityCard, skillCO);
    }


    public void upgradeCaptainLevel(long uid, long cid, long captainCityCardId,
                                    String otherCaptainCityCardIdArray) throws CityCardIsNotYoursException, CityCardNotExistsException, CaptainCityCardLevelIsTopException {
        CityCardCO captainCityCard = cityCardHelper.get(cid, captainCityCardId);
        StorageCO storageCO = storageHelper.getByCid(cid);
        String[] captainCityCardIdArr = otherCaptainCityCardIdArray.split(",");
        // 检查这些卡是不是属于你自己的卡
        for (String cityCardId : captainCityCardIdArr) {
            CityCardCO tmpCityCard = cityCardHelper.get(cid, new Long(cityCardId));
            if (!storageHelper.haveTheCaptainCard(storageCO, tmpCityCard))
                throw new CityCardIsNotYoursException("cityCardId = " + cityCardId);
        }
        // 计算所有的卡牌已经能有多少的经验
        int allExp = 0;
        List<CityCardCO> cityCardList = new LinkedList<CityCardCO>();
        for (String cityCardId : captainCityCardIdArr) {
            CityCardCO cityCardCO = cityCardHelper.get(cid, new Long(cityCardId));
            allExp += DataUtils.getSquardIntData(cityCardCO.getData(), Configuration.CAPTAIN_CITY_CARD.LEVEL_EXP_KEY);
        }
        // 在小队卡仓库中移除掉这些卡
        for (CityCardCO cityCard : cityCardList) {
            storageHelper.removeCaptainCityCard(storageCO, cityCard);
        }
        // 给cityCard添加经验加等级
        captainHelper.upgradeLevel(captainCityCard, allExp);
        storageHelper.cache(storageCO);
        cityCardHelper.cache(captainCityCard);
    }


}
