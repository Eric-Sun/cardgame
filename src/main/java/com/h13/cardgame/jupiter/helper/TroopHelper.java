package com.h13.cardgame.jupiter.helper;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.TroopCO;
import com.h13.cardgame.cache.service.TroopCache;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.dao.TroopDAO;
import com.h13.cardgame.jupiter.exceptions.*;
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
    CityCardHelper cityCardHelper;
    @Autowired
    CityHelper cityHelper;

    public TroopCO create(long cid, int troopSize) {
        TroopCO troop = new TroopCO();
        troop.setCityId(cid);
        troop.setAttackMax(Configuration.Troop.INIT_ATTACK_MAX);
        troop.setAttackMin(Configuration.Troop.INIT_ATTACK_MIN);
        troop.setDefenceMax(Configuration.Troop.INIT_DEFENCE_MAX);
        troop.setDefenceMin(Configuration.Troop.INIT_DEFENCE_MIN);
        troop.setMembers(new String[troopSize]);
        troop.setName("");
        troop.setMaxSlot(troopSize);
        troopDAO.create(troop);
        return troop;
    }

    public TroopCO getByCid(long cid) {
        TroopCO squard = squardCache.get(cid);
        if (squard == null) {
            squard = troopDAO.getByCaptainId(cid);
            squardCache.putToQueue(squard);
        }
        return squard;
    }

    public void flushAttributes(TroopCO squard) throws CityCardNotExistsException {
        String[] ccidList = squard.getMembers();
        squard.setAttackMax(0);
        squard.setAttackMin(0);
        squard.setDefenceMax(0);
        squard.setDefenceMin(0);
        for (String ccId : ccidList) {
            if (ccId == null)
                continue;
            CityCardCO cc = cityCardHelper.get(new Long(ccId));
            squard.setAttackMax(squard.getAttackMax() + cc.getAttackMax());
            squard.setAttackMin(squard.getAttackMin() + cc.getAttackMin());
            squard.setDefenceMax(squard.getDefenceMax() + cc.getDefenceMax());
            squard.setDefenceMin(squard.getDefenceMin() + cc.getDefenceMin());
        }
        troopDAO.updateAttributes(squard.getId(), squard.getAttackMax(), squard.getAttackMin(), squard.getDefenceMax(), squard.getDefenceMin());
    }

    public void on(long cityId, TroopCO troop, int index, long cityCardId) throws IndexOfTroopHaveAnotherCardException,
            CityCardIsOnListException, CityCardIsNotYoursException, CityCardNotExistsException {
        if (cityCardHelper.get(cityCardId).getCityId() != cityId) {
            throw new CityCardIsNotYoursException("don't have the cityCard. cityCardId=" + cityCardId);
        }
        if (troop.getMembers()[index] != null)
            throw new IndexOfTroopHaveAnotherCardException("need null but " + troop.getMembers()[index]);
        for (String id : troop.getMembers()) {
            if (id == null)
                continue;
            if (id.equals(cityCardId + "")) {
                throw new CityCardIsOnListException("this card is on list already.cityCardId=" + cityCardId);
            }
        }
        troop.getMembers()[index] = cityCardId + "";
        troop.setCurSlot(troop.getCurSlot() + 1);
        troopDAO.addMember(cityId, troop.getMembers());
    }


    public void down(long cityId, TroopCO troop, int index, long cityCardId) throws IndexOfTroopHaveNoCardException, CityCardNotExistsException, CityCardIsNotYoursException {
        if (cityCardHelper.get(cityCardId).getCityId() != cityId) {
            throw new CityCardIsNotYoursException("don't have the cityCard.cityId=" + cityId + " cityCardId=" + cityCardId);
        }
        if (troop.getMembers()[index] == null)
            throw new IndexOfTroopHaveNoCardException("need not null  but index=" + index + " cityCardId=" + cityCardId + " cardInList=" + troop.getMembers()[index]);
        troop.getMembers()[index] = null;
        troop.setCurSlot(troop.getCurSlot() - 1);
        troopDAO.removeMember(cityId, troop.getMembers());
    }


    public void cache(TroopCO squard) {
        squardCache.putToQueue(squard);
    }


    public List<AttackTargetVO> getAttackTarget(long uid, long cid, int pageNum, int pageSize) throws UserNotExistsException, UserIllegalParamterException {
        List<AttackTargetVO> retList = new ArrayList<AttackTargetVO>();
        CityCO catpain = cityHelper.get(uid, cid);
        int level = catpain.getLevel();
        int fromLevel = level - 10 < 0 ? 1 : level - 10;
        int toLevel = level + 10;
        List<Long> cidList = cityHelper.searchAttackTarget(cid, fromLevel, toLevel, pageNum, pageSize);
        for (Long id : cidList) {
            TroopCO troop = getByCid(id);
            AttackTargetVO target = new AttackTargetVO();
            target.setAttackMax(troop.getAttackMax());
            target.setAttackMin(troop.getAttackMin());
            target.setCid(id);
            retList.add(target);
        }
        return retList;
    }
}
