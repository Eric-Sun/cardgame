package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.jupiter.exceptions.CityCardIsNotYoursException;
import com.h13.cardgame.jupiter.exceptions.CityCardNotExistsException;
import com.h13.cardgame.jupiter.exceptions.SquardDontHaveCaptainException;
import com.h13.cardgame.jupiter.exceptions.SquardHaveCaptainException;
import com.h13.cardgame.jupiter.helper.CityCardHelper;
import com.h13.cardgame.jupiter.helper.SquardHelper;
import com.h13.cardgame.jupiter.helper.StorageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-24
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SquardService {

    @Autowired
    SquardHelper squardHelper;
    @Autowired
    CityCardHelper cityCardHelper;
    @Autowired
    StorageHelper storageHelper;

    public void onCaptain(long uid, long cid, long squardCityCardId, long captainCityCardId) throws CityCardNotExistsException, CityCardIsNotYoursException, SquardHaveCaptainException {
        // 如果captain已经有了
        CityCardCO cityCardCO = cityCardHelper.get(cid, squardCityCardId);
        CityCardCO captainCityCardCO = cityCardHelper.get(cid, captainCityCardId);
        StorageCO storageCO = storageHelper.getByCid(cid);
        if (squardHelper.haveCaptainCityCard(cityCardCO))
            throw new SquardHaveCaptainException("cityId=" + cid + " squardCityCardId=" + squardCityCardId);

        // 检查这个captain是不是自己的
        if (!storageHelper.haveTheCaptainCard(storageCO, captainCityCardCO)) {
            throw new CityCardIsNotYoursException("cityCardId = " + captainCityCardId);
        }
        // 设置
        squardHelper.onCaptainCityCard(cityCardCO, captainCityCardCO);
        cityCardHelper.updateAttributes(cityCardCO);
        cityCardHelper.cache(cityCardCO);
        }


    public void offCaptain(long uid, long cid, long squardCityCardId) throws CityCardNotExistsException, CityCardIsNotYoursException, SquardDontHaveCaptainException {
        CityCardCO cityCardCO = cityCardHelper.get(cid, squardCityCardId);
        if (!squardHelper.haveCaptainCityCard(cityCardCO))
            throw new SquardDontHaveCaptainException("cityId=" + cid + " squardCityCardId=" + squardCityCardId);
        // 设置
        squardHelper.offCaptainCityCard(cityCardCO);
        cityCardHelper.updateAttributes(cityCardCO);
        cityCardHelper.cache(cityCardCO);
    }

}
