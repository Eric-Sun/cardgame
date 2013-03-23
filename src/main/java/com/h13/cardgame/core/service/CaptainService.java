package com.h13.cardgame.core.service;

import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.helper.CaptainHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-20
 * Time: 下午6:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptainService {
    private static Log LOG = LogFactory.getLog(CaptainService.class);

    @Autowired
    CaptainHelper captainHelper;

    /**
     * 添加能量
     *
     * @param cid
     * @param value
     * @throws ParameterIllegalException
     */
    public void addEnergy(long cid, int value) throws ParameterIllegalException {
        CaptainCO captain = captainHelper.get(cid);
        captainHelper.addEnergy(captain, value);
        captainHelper.cacheCaptain(captain);
        LOG.info("captain add energy. id=" + captain.getId() + " value=" + value);
    }

    /**
     * 减少能量
     *
     * @param cid
     * @param value
     * @throws ParameterIllegalException
     */
    public void subEnergy(long cid, int value) throws ParameterIllegalException {
        CaptainCO captain = captainHelper.get(cid);
        captainHelper.subEnergy(captain, value);
        captainHelper.cacheCaptain(captain);
        LOG.info("captain sub energy. id=" + captain.getId() + " value=" + value);
    }

    /**
     * 创建一个captain
     *
     * @param uid
     * @param name
     * @return
     */
    public CaptainCO create(long uid, String name) throws ParameterIllegalException {
        CaptainCO captain = captainHelper.create(uid, name);
        LOG.info("create captain. " + captain);
        return captain;
    }


    /**
     * 读取captain的相关信息
     * @param cid
     * @return
     * @throws ParameterIllegalException
     */
    public CaptainCO get(long cid) throws ParameterIllegalException {
        CaptainCO captain = captainHelper.get(cid);
        LOG.info("load captain. " + captain);
        return captain;
    }
}
