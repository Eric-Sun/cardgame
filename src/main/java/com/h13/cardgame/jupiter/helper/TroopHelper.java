package com.h13.cardgame.jupiter.helper;

import com.h13.cardgame.cache.co.CityCO;
import com.h13.cardgame.cache.co.SquadCityCardCO;
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
    TroopCache squadCache;
    @Autowired
    TroopDAO troopDAO;
    @Autowired
    CardHelper cardHelper;
    @Autowired
    CityHelper cityHelper;
    @Autowired
    CaptainCityCardHelper captainCityCardHelper;
    @Autowired
    SquadCityCardHelper squadCityCardHelper;

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
        TroopCO squad = squadCache.get(cid);
        if (squad == null) {
            squad = troopDAO.getByCaptainId(cid);
            squadCache.put(squad);
        }
        return squad;
    }

    public void flushAttributes(TroopCO troop) throws CityCardNotExistsException, CityCardIsNotYoursException {
        String[] ccidList = troop.getMembers();
        troop.setAttackMax(0);
        troop.setAttackMin(0);
        troop.setDefenceMax(0);
        troop.setDefenceMin(0);
        for (String ccId : ccidList) {
            if (ccId == null)
                continue;
            SquadCityCardCO squadCityCardCO = squadCityCardHelper.get(troop.getCityId(), new Long(ccId));
            troop.setAttackMax(troop.getAttackMax() + squadCityCardCO.getAttackMax());
            troop.setAttackMin(troop.getAttackMin() + squadCityCardCO.getAttackMin());
            troop.setDefenceMax(troop.getDefenceMax() + squadCityCardCO.getDefenceMax());
            troop.setDefenceMin(troop.getDefenceMin() + squadCityCardCO.getDefenceMin());
        }
        troopDAO.updateAttributes(troop.getId(), troop.getAttackMax(), troop.getAttackMin(), troop.getDefenceMax(), troop.getDefenceMin());
    }

    public void on(long cityId, TroopCO troop, int index, long captainCityCardId) throws IndexOfTroopHaveAnotherCardException,
            CityCardIsOnListException, CityCardIsNotYoursException, CityCardNotExistsException {
        if (captainCityCardHelper.get(cityId, captainCityCardId).getCityId() != cityId) {
            throw new CityCardIsNotYoursException("don't have the cityCard. captainCityCardId=" + captainCityCardId);
        }
        if (troop.getMembers()[index] != null)
            throw new IndexOfTroopHaveAnotherCardException("need null but " + troop.getMembers()[index]);
        for (String id : troop.getMembers()) {
            if (id == null)
                continue;
            if (id.equals(captainCityCardId + "")) {
                throw new CityCardIsOnListException("this card is on list already.captainCityCardId=" + captainCityCardId);
            }
        }
        troop.getMembers()[index] = captainCityCardId + "";
        troop.setCurSlot(troop.getCurSlot() + 1);
        troopDAO.addMember(cityId, troop.getMembers());
    }


    public void down(long cityId, TroopCO troop, int index, long captainCityCardId) throws IndexOfTroopHaveNoCardException, CityCardNotExistsException, CityCardIsNotYoursException {
        if (captainCityCardHelper.get(cityId, captainCityCardId).getCityId() != cityId) {
            throw new CityCardIsNotYoursException("don't have the cityCard.cityId=" + cityId + " captainCityCardId=" + captainCityCardId);
        }
        if (troop.getMembers()[index] == null)
            throw new IndexOfTroopHaveNoCardException("need not null  but index=" + index + " captainCityCardId=" + captainCityCardId + " cardInList=" + troop.getMembers()[index]);
        troop.getMembers()[index] = null;
        troop.setCurSlot(troop.getCurSlot() - 1);
        troopDAO.removeMember(cityId, troop.getMembers());
    }


    public void cache(TroopCO squad) {
        squadCache.put(squad);
    }


    public List<AttackTargetVO> getAttackTarget(long uid, long cid, int pageNum, int pageSize)
            throws UserNotExistsException, UserDontHaveThisCityException {
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
