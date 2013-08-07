package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.StorageCO;
import com.h13.cardgame.cache.co.TroopCO;
import com.h13.cardgame.jupiter.exceptions.CityExistsException;
import com.h13.cardgame.jupiter.exceptions.UserDontHaveThisCityException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.helper.*;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.CityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: sunbo
 * Date: 13-3-20
 * Time: 下午6:52
 */
@Service
public class CityService {

    @Autowired
    CityHelper cityHelper;
    @Autowired
    LevelHelper levelHelper;
    @Autowired
    TaskHelper taskHelper;
    @Autowired
    StorageHelper storageHelper;
    @Autowired
    TroopHelper troopHelper;


    /**
     * 创建一个captain
     *
     * @param uid
     * @param name
     * @return
     */
    public CityCO create(long uid, String name) throws UserNotExistsException, CityExistsException {
        CityCO city = cityHelper.create(uid, name);
        int troopSize = levelHelper.get(1).getTroopSize();
        cityHelper.cache(city);
        LogWriter.info(LogWriter.CITY, "create city. city=" + city);
        StorageCO storage = storageHelper.create(city.getId());
        storageHelper.cache(storage);
        LogWriter.info(LogWriter.CITY, "create storage. " + storage);
        TroopCO troop = troopHelper.create(city.getId(), troopSize);
        LogWriter.info(LogWriter.CITY, "create troop. " + troop);
        return city;
    }


    /**
     * 读取captain的相关信息
     *
     * @param uid
     * @param cid
     * @return
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public CityVO get(long uid, long cid) throws UserNotExistsException, UserDontHaveThisCityException {
        CityCO city = cityHelper.get(uid, cid);
        LogWriter.info(LogWriter.CITY, "load city. " + city);
        return DTOUtils.toCityVO(city);
    }


    public CityVO checkAndGetCity(long uid) {
        CityCO city = cityHelper.checkAndGetCity(uid);
        if (city == null)
            return null;
        return DTOUtils.toCityVO(city);
    }

    /**
     * 刷新能量
     * 计算上次记录的时间戳到现在应该产生多少的能量，并且更新缓存和数据库
     *
     * @param uid
     * @param cid
     */
    public void flushEnergy(long uid, long cid) throws UserNotExistsException, UserDontHaveThisCityException {
        CityCO city = cityHelper.get(uid, cid);
        cityHelper.tryAddEnergy(city);
        cityHelper.cache(city);
    }


}
