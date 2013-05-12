package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.jupiter.exceptions.CityExistsException;
import com.h13.cardgame.jupiter.exceptions.ServerErrorException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.helper.CityHelper;
import com.h13.cardgame.jupiter.helper.LevelHelper;
import com.h13.cardgame.jupiter.helper.TaskHelper;
import com.h13.cardgame.jupiter.utils.LogWriter;
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

    /**
     * 添加能量
     *
     * @param cid
     * @param value
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public void addEnergy(long cid, int value) throws UserNotExistsException {
        CityCO city = cityHelper.get(cid);
        cityHelper.addEnergy(city, value);
        cityHelper.cache(city);
        LogWriter.info(LogWriter.CITY, "city add energy. id=" + city.getId() + " value=" + value);
    }

    /**
     * 减少能量
     *
     * @param cid
     * @param value
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public void subEnergy(long cid, int value) throws UserNotExistsException {
        CityCO captain = cityHelper.get(cid);
        cityHelper.subEnergy(captain, value);
        cityHelper.cache(captain);
        LogWriter.info(LogWriter.CITY, "captain sub energy. id=" + captain.getId() + " value=" + value);
    }

    /**
     * 创建一个captain
     *
     * @param uid
     * @param name
     * @return
     */
    public CityCO create(long uid, String name) throws UserNotExistsException, CityExistsException {
        CityCO city = cityHelper.create(uid, name);
        cityHelper.cache(city);
        LogWriter.info(LogWriter.CITY, "create city. city=" + city);
        return city;
    }


    /**
     * 读取captain的相关信息
     *
     * @param cid
     * @return
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public CityCO get(long cid) throws UserNotExistsException {
        CityCO captain = cityHelper.get(cid);
        LogWriter.info(LogWriter.CITY, "load captain. " + captain);
        return captain;
    }


}
