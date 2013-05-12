package com.h13.cardgame.jupiter.helper;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.TroopCO;
import com.h13.cardgame.cache.service.TroopCache;
import com.h13.cardgame.jupiter.dao.TroopDAO;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.vo.AttackTargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

/**
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午6:33
 */
@Service
public class TroopHelper {
    @Autowired
    TroopCache squardCache;
    @Autowired
    TroopDAO troopDAO;

    @Autowired
    CardHelper cardHelper;

    @Autowired
    CityHelper cityHelper;

    public TroopCO getByCid(long cid) {
        TroopCO squard = squardCache.get(cid);
        if (squard == null) {
            squard = troopDAO.getByCaptainId(cid);
            squardCache.put(squard);
        }
        return squard;
    }

    public void flushAttributes(TroopCO squard) {
        List<Long> ccidList = squard.getMembers();
        squard.setAttackMax(0);
        squard.setAttackMin(0);
        squard.setDefenceMax(0);
        squard.setDefenceMin(0);
        for (Long ccId : ccidList) {
            CityCardCO cc = cardHelper.getCaptianCard(ccId);
            squard.setAttackMax(squard.getAttackMax() + cc.getAttackMax());
            squard.setAttackMin(squard.getAttackMin() + cc.getAttackMin());
            squard.setDefenceMax(squard.getDefenceMax() + cc.getDefenceMax());
            squard.setDefenceMin(squard.getDefenceMin() + cc.getDefenceMin());
        }
        troopDAO.updateAttributes(squard.getId(), squard.getAttackMax(), squard.getAttackMin(), squard.getDefenceMax(), squard.getDefenceMin());
    }

    public void addMember(TroopCO squard, long ccId) {
        squard.getMembers().add(ccId);
        troopDAO.addMember(ccId, JSON.toJSONString(squard.getMembers()));
    }

    public void cacheSquard(TroopCO squard) {
        squardCache.put(squard);
    }


    public List<AttackTargetVO> getAttackTarget(long cid, int pageNum, int pageSize) throws UserNotExistsException {
        List<AttackTargetVO> retList = new ArrayList<AttackTargetVO>();
        CityCO catpain = cityHelper.get(cid);
        int level = catpain.getLevel();
        int fromLevel = level - 10 < 0 ? 1 : level - 10;
        int toLevel = level + 10;
        List<Long> cidList = cityHelper.searchAttackTarget(cid, fromLevel, toLevel, pageNum, pageSize);
        for (Long id : cidList) {
            TroopCO squard = getByCid(id);
            AttackTargetVO target = new AttackTargetVO();
            target.setAttackMax(squard.getAttackMax());
            target.setAttackMin(squard.getAttackMin());
            retList.add(target);
        }
        return retList;
    }
}
