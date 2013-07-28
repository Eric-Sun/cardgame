package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CaptainLevelCO;
import com.h13.cardgame.cache.service.CaptainLevelCache;
import com.h13.cardgame.jupiter.dao.CaptainLevelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-23
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CaptainLevelHelper {

    @Autowired
    CaptainLevelDAO captainLevelDAO;
    @Autowired
    CaptainLevelCache captainLevelCache;

    public CaptainLevelCO get(long captainTitleId) {
        CaptainLevelCO captainLevelCO = captainLevelCache.get(captainTitleId);
        if (captainLevelCO == null) {
            captainLevelCO = captainLevelDAO.get(captainTitleId);
        }
        return captainLevelCO;
    }

}
