package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CaptainCityCardCO;
import com.h13.cardgame.cache.co.SkillCO;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.jupiter.exceptions.CaptainCityCardLevelIsTopException;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.helper.CaptainCityCardHelper;
import com.h13.cardgame.jupiter.helper.SquadCityCardHelper;
import com.h13.cardgame.jupiter.helper.StorageHelper;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.vo.CaptainCityCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CaptainService {


    @Autowired
    StorageHelper storageHelper;
    @Autowired
    CaptainCityCardHelper captainCityCardHelper;
    @Autowired
    SquadCityCardHelper squadCityCardHelper;



    public void upgradeCaptainLevel(long uid, long cid, long captainCityCardId,
                                    String otherCaptainCityCardIdArray) throws CityCardIsNotYoursException, CityCardNotExistsException, CaptainCityCardLevelIsTopException {
        CaptainCityCardCO captainCityCard = captainCityCardHelper.get(cid, captainCityCardId);
        StorageCO storageCO = storageHelper.getByCid(cid);
        String[] captainCityCardIdArr = otherCaptainCityCardIdArray.split(",");
        // 检查这些卡是不是属于你自己的卡
        for (String cityCardId : captainCityCardIdArr) {
            CaptainCityCardCO tmpCityCard = captainCityCardHelper.get(cid, new Long(cityCardId));
            if (!storageHelper.haveTheCaptainCard(storageCO, tmpCityCard))
                throw new CityCardIsNotYoursException("cityCardId = " + cityCardId);
        }
        // 计算所有的卡牌已经能有多少的经验
        int allExp = 0;
        List<CaptainCityCardCO> cityCardList = new LinkedList<CaptainCityCardCO>();
        for (String cityCardId : captainCityCardIdArr) {
            CaptainCityCardCO captainCityCardCO = captainCityCardHelper.get(cid, new Long(cityCardId));
            allExp += captainCityCardCO.getLevelExp();
        }
        // 在小队卡仓库中移除掉这些卡
        for (CaptainCityCardCO cityCard : cityCardList) {
            storageHelper.removeCaptainCityCard(storageCO, cityCard);
        }
        // 给cityCard添加经验加等级
        captainCityCardHelper.upgradeLevel(captainCityCard, allExp);
        storageHelper.cache(storageCO);
        captainCityCardHelper.cache(captainCityCard);
    }


}
