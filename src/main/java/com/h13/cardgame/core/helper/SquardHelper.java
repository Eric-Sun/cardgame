package com.h13.cardgame.core.helper;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CaptainCO;
import com.h13.cardgame.cache.co.CaptainCardCO;
import com.h13.cardgame.cache.co.SquardCO;
import com.h13.cardgame.cache.service.SquardCache;
import com.h13.cardgame.core.dao.SquardDAO;
import com.h13.cardgame.core.exceptions.ParameterIllegalException;
import com.h13.cardgame.core.vo.AttackTargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午6:33
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SquardHelper {
    @Autowired
    SquardCache squardCache;
    @Autowired
    SquardDAO squardDAO;

    @Autowired
    CardHelper cardHelper;

    @Autowired
    CaptainHelper captainHelper;

    public SquardCO get(long cid) {
        SquardCO squard = squardCache.get(cid);
        if (squard == null) {
            squard = squardDAO.getByCaptainId(cid);
            squardCache.put(squard);
        }
        return squard;
    }

    public void flushAttributes(SquardCO squard) {
        List<Long> ccidList = squard.getMembers();
        squard.setAttackMax(0);
        squard.setAttackMin(0);
        squard.setDefenceMax(0);
        squard.setDefenceMin(0);
        for (Long ccId : ccidList) {
            CaptainCardCO cc = cardHelper.getCaptianCard(ccId);
            squard.setAttackMax(squard.getAttackMax() + cc.getAttackMax());
            squard.setAttackMin(squard.getAttackMin() + cc.getAttackMin());
            squard.setDefenceMax(squard.getDefenceMax() + cc.getDefenceMax());
            squard.setDefenceMin(squard.getDefenceMin() + cc.getDefenceMin());
        }
        squardDAO.updateAttributes(squard.getId(), squard.getAttackMax(), squard.getAttackMin(), squard.getDefenceMax(), squard.getDefenceMin());
    }

    public void addMember(SquardCO squard, long ccId) {
        squard.getMembers().add(ccId);
        squardDAO.addMember(ccId, JSON.toJSONString(squard.getMembers()));
    }

    public void cacheSquard(SquardCO squard) {
        squardCache.put(squard);
    }


    public List<AttackTargetVO> getAttackTarget(long cid, int pageNum, int pageSize) throws ParameterIllegalException {
        List<AttackTargetVO> retList = new ArrayList<AttackTargetVO>();
        CaptainCO catpain = captainHelper.get(cid);
        int level = catpain.getLevel();
        int fromLevel = level - 10 < 0 ? 1 : level - 10;
        int toLevel = level + 10;
        List<Long> cidList = captainHelper.searchAttackTarget(cid, fromLevel, toLevel, pageNum, pageSize);
        for (Long id : cidList) {
            SquardCO squard = get(id);
            AttackTargetVO target = new AttackTargetVO();
            target.setAttackMax(squard.getAttackMax());
            target.setAttackMin(squard.getAttackMin());
            retList.add(target);
        }
        return retList;
    }
}
