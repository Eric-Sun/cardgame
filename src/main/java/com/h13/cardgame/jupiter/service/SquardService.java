package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.TroopCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.helper.CardHelper;
import com.h13.cardgame.jupiter.helper.TroopHelper;
import com.h13.cardgame.jupiter.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * 小队的行为，战斗，等等
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午5:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SquardService {
    private static Log LOG = LogFactory.getLog(SquardService.class);

    @Autowired
    TroopHelper troopHelper;
    @Autowired
    CardHelper cardHelper;

    /**
     * 获得小队中所有的卡牌的小列表
     *
     * @return
     */
    public SimpleSquardVO getSimpleSquard(long cid) {
        SimpleSquardVO ss = new SimpleSquardVO();
        TroopCO squard = troopHelper.getByCid(cid);
        List<Long> list = squard.getMembers();
        List<CardVO> cList = new ArrayList<CardVO>();
        for (long ccId : list) {
            CityCardCO cc = cardHelper.getCaptianCard(ccId);
            CardVO card = new CardVO();
            card.setId(cc.getCardId());
            card.setImg(cc.getImg());
            card.setName(cc.getName());
            cList.add(card);
        }
        ss.setCardList(cList);
        return ss;
    }


    /**
     * 添加一个新的队员
     */
    public void addMember(long cid, long ccId) {
        TroopCO squard = troopHelper.getByCid(cid);
        troopHelper.addMember(squard, ccId);
        troopHelper.cacheSquard(squard);
    }

    /**
     * 计算小队的攻击力和防御力
     *
     * @return
     */
    public CombatAttributesVO getCombatAttributes(long cid) {
        TroopCO squard = troopHelper.getByCid(cid);
        CombatAttributesVO attributes = new CombatAttributesVO();
        for (Long ccId : squard.getMembers()) {
            CityCardCO cc = cardHelper.getCaptianCard(ccId);
            attributes.setAttackMax(attributes.getAttackMax() + cc.getAttackMax());
            attributes.setAttackMin(attributes.getAttackMin() + cc.getAttackMin());
            attributes.setDefenceMax(attributes.getDefenceMax() + cc.getDefenceMax());
            attributes.setDefenceMin(attributes.getDefenceMin() + cc.getDefenceMin());
        }
        return attributes;
    }


    /**
     * 获得小队列表
     *
     * @return
     */
    public SimpleSquardVO getLargeSquard(long cid) {
        SimpleSquardVO ss = new SimpleSquardVO();
        TroopCO squard = troopHelper.getByCid(cid);
        List<Long> list = squard.getMembers();
        List<CardVO> cList = new ArrayList<CardVO>();
        for (long ccId : list) {
            CityCardCO cc = cardHelper.getCaptianCard(ccId);
            CardVO card = new CardVO();
            card.setId(cc.getCardId());
            card.setImg(cc.getImg());
            card.setName(cc.getName());
            card.setAttackMax(cc.getAttackMax());
            card.setAttackMin(cc.getAttackMin());
            card.setDefenceMax(cc.getDefenceMax());
            card.setAttackMin(cc.getDefenceMin());
            cList.add(card);
        }
        ss.setCardList(cList);
        return ss;
    }


    /**
     * 攻击别人
     *
     * @param fromCid
     * @param toCid
     * @return
     */
    public CombatResultVO attack(long fromCid, long toCid) {
        CombatAttributesVO fromAttr = getCombatAttributes(fromCid);
        CombatAttributesVO toAttr = getCombatAttributes(toCid);
        LOG.info("fromId=" + fromCid + " toId=" + toCid + " from attr: " + fromAttr + " to attr: " + toAttr);
        Random random = new Random();
        int attack = fromAttr.getAttackMin() + random.nextInt(fromAttr.getAttackMax() - fromAttr.getAttackMin());
        int defence = toAttr.getDefenceMin() + random.nextInt(toAttr.getDefenceMax() - toAttr.getDefenceMin());
        if (attack > defence) {
            LOG.info("atttack success. attack=" + attack + " defence=" + defence);
            CombatResultVO result = new CombatResultVO();
            result.setResult(true);
            LOG.info(" result=" + result);
            return result;
        } else {
            LOG.info("atttack fail. attack=" + attack + " defence=" + defence);
            CombatResultVO result = new CombatResultVO();
            result.setResult(false);
            LOG.info(" result=" + result);
            return result;
        }
    }


    /**
     * 获得可以攻击的对象列表
     *
     * @param cid
     * @param pageNum
     * @return
     * @throws com.h13.cardgame.jupiter.exceptions.UserNotExistsException
     *
     */
    public List<AttackTargetVO> searchAttackTarget(long cid, int pageNum) throws UserNotExistsException {
        return troopHelper.getAttackTarget(cid, pageNum, Configuration.ATTACK_TARGET_PAGE_SIZE);
    }


}
