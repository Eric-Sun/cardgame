package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.exceptions.*;
import com.h13.cardgame.jupiter.helper.*;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * 部队的行为，战斗，等等
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午5:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TroopService {

    @Autowired
    TroopHelper troopHelper;
    @Autowired
    CardHelper cardHelper;
    @Autowired
    CityCardHelper cityCardHelper;
    @Autowired
    CityHelper cityHelper;
    @Autowired
    StorageHelper storageHelper;

    @Autowired
    ConfigService configService;

    /**
     * 获得小队中所有的卡牌的小列表
     *
     * @return
     */
    public TroopVO getTroop(long cid) throws CityCardNotExistsException {
        TroopCO troop = troopHelper.getByCid(cid);
        return toVO(troop);
    }


    /**
     * 上阵
     */
    public TroopVO onList(long cid, int index, long cityCardId) throws IndexOfTroopHaveAnotherCardException,
            CityCardNotExistsException, CityCardIsNotYoursException, CityCardIsOnListException {
        TroopCO troop = troopHelper.getByCid(cid);
        troopHelper.on(cid, troop, index, cityCardId);
        troopHelper.flushAttributes(troop);
        troopHelper.cache(troop);
        return toVO(troop);
    }


    /**
     * 下阵
     */
    public TroopVO downList(long cid, int index, long cityCardId) throws CityCardIsOnListException, IndexOfTroopHaveAnotherCardException, IndexOfTroopHaveNoCardException, CityCardIsNotYoursException, CityCardNotExistsException {
        TroopCO troop = troopHelper.getByCid(cid);
        troopHelper.down(cid, troop, index, cityCardId);
        troopHelper.flushAttributes(troop);
        troopHelper.cache(troop);
        return toVO(troop);
    }

    private TroopVO toVO(TroopCO troop) throws CityCardNotExistsException {

        TroopVO vo = new TroopVO();
        vo.setAttackMax(troop.getAttackMax());
        vo.setAttackMin(troop.getAttackMin());
        vo.setDefenceMax(troop.getDefenceMax());
        vo.setDefenceMin(troop.getDefenceMin());
        vo.setCurSlot(troop.getCurSlot());
        vo.setMaxSlot(troop.getMaxSlot());
        vo.setTid(troop.getId());
        for (String id : troop.getMembers()) {
            if (id == null) {
                vo.getCardList().add(null);
                continue;
            }
            CityCardCO cityCard = cityCardHelper.get(new Long(id));
            CityCardVO cityCardVO = new CityCardVO();
            cityCardVO.setCardId(cityCard.getCardId());
            cityCardVO.setIcon(cityCard.getIcon());
            cityCardVO.setName(cityCard.getName());
            cityCardVO.setAttackMax(cityCard.getAttackMax());
            cityCardVO.setAttackMin(cityCard.getAttackMin());
            cityCardVO.setDefenceMax(cityCard.getDefenceMax());
            cityCardVO.setDefenceMin(cityCard.getDefenceMin());
            cityCardVO.setId(cityCard.getId());
            vo.getCardList().add(cityCardVO);
        }
        return vo;
    }

    /**
     * 计算小队的攻击力和防御力
     *
     * @return
     */
    public CombatAttributesVO getCombatAttributes(long cid) throws CityCardNotExistsException {
        TroopCO squard = troopHelper.getByCid(cid);
        CombatAttributesVO attributes = new CombatAttributesVO();
        for (String ccId : squard.getMembers()) {
            CityCardCO cc = cityCardHelper.get(new Long(ccId));
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
    public TroopVO getLargeSquard(long cid) throws CityCardNotExistsException {
        TroopVO ss = new TroopVO();
        TroopCO squard = troopHelper.getByCid(cid);
        String[] list = squard.getMembers();
        List<CityCardVO> cList = new ArrayList<CityCardVO>();
        for (String ccId : list) {
            CityCardCO cc = cityCardHelper.get(new Long(ccId));
            CityCardVO card = new CityCardVO();
            card.setId(cc.getCardId());
            card.setIcon(cc.getIcon());
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
    public CombatResultVO attack(long fromCid, long toCid) throws CityCardNotExistsException {
        CombatAttributesVO fromAttr = getCombatAttributes(fromCid);
        CombatAttributesVO toAttr = getCombatAttributes(toCid);
        LogWriter.info(LogWriter.TROOP, "fromId=" + fromCid + " toId=" + toCid + " from attr: " + fromAttr + " to attr: " + toAttr);
        Random random = new Random();
        int attack = fromAttr.getAttackMin() + random.nextInt(fromAttr.getAttackMax() - fromAttr.getAttackMin());
        int defence = toAttr.getDefenceMin() + random.nextInt(toAttr.getDefenceMax() - toAttr.getDefenceMin());
        if (attack > defence) {
            LogWriter.info(LogWriter.TROOP, "atttack success. attack=" + attack + " defence=" + defence);
            CombatResultVO result = new CombatResultVO();
            result.setResult(true);
            LogWriter.info(LogWriter.TROOP, " result=" + result);
            return result;
        } else {
            LogWriter.info(LogWriter.TROOP, "attack fail. attack=" + attack + " defence=" + defence);
            CombatResultVO result = new CombatResultVO();
            result.setResult(false);
            LogWriter.info(LogWriter.TROOP, " result=" + result);
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
    public List<AttackTargetVO> searchAttackTarget(long uid, long cid, int pageNum) throws UserNotExistsException, UserIllegalParamterException {
        return troopHelper.getAttackTarget(uid, cid, pageNum, Configuration.ATTACK_TARGET_PAGE_SIZE);
    }


    /**
     * 对于一个human卡，进行招募
     * <p>
     * 通过银币和装备卡
     * </p>
     *
     * @return
     */
    public HumanCardVO recruit(long uid, long cid, long eCardId, long hCardId, String eCityCard, String hCityCard)
            throws UserNotExistsException, UserIllegalParamterException, SilverNotEnoughException, ServerErrorException, CityCardNotEnoughSlotException, CityCardNotExistsException {
        CityCO city = cityHelper.get(uid, cid);
        // 检查所有的卡是否真的拥有
        StorageCO storage = storageHelper.getByCid(cid);
        Map<String, List<String>> cardData = storage.getCardData();
        String[] eCityCardArr = eCityCard.split(",");
        // 检查银币是否够
        int silverPerCard = new Integer(configService.get(Configuration.Core.RECUIT_SILVER_1));
        if (city.getSilver() < eCityCardArr.length * silverPerCard) {
            throw new SilverNotEnoughException("need " + eCityCardArr.length * silverPerCard + " but have " + city.getSilver());
        }
        for (String cityCardId : eCityCardArr) {
            // 检查卡是否已经被使用过
            CityCardCO cityCard = cityCardHelper.get(new Long(cityCardId));

            if (!cardData.get(eCardId + "").contains(cityCardId))
                throw new ServerErrorException("cityCardId=" + cityCardId + " not in card data.cid=" + cid + " uid=" + uid);
        }
        if (!cardData.get(hCardId + "").contains(hCityCard)) {
            throw new ServerErrorException("cityCardId=" + hCityCard + " not in card data.cid=" + cid + " uid=" + uid);
        }

        // 去掉相应的卡，和银币
        for (String cityCardId : eCityCardArr) {
            cardData.get(eCardId + "").remove(cityCardId + "");
            cityCardHelper.useCityCard(new Long(eCardId));
        }
        city.setSilver(city.getSilver() - eCityCardArr.length * silverPerCard);

        // 检查是否已经超出human卡的slot限制
        CityCardCO cc = cityCardHelper.get(new Long(hCityCard));
        if (cc.getMaxSlot() < cc.getCurSlot() + eCityCardArr.length) {
            throw new CityCardNotEnoughSlotException("cur is " + cc.getCurSlot() +
                    " new is " + eCityCardArr.length + " all is " + cc.getMaxSlot());
        }
        // 提高human卡的属性
        CardCO c = cardHelper.get(eCardId);
        cc.setAttackMax(c.getAttackMax() * eCityCardArr.length);
        cc.setAttackMin(c.getAttackMin() * eCityCardArr.length);
        cc.setDefenceMax(c.getDefenceMax() * eCityCardArr.length);
        cc.setDefenceMin(c.getDefenceMin() * eCityCardArr.length);
        cc.setCurSlot(cc.getCurSlot() + eCityCardArr.length);

        // 更新city cityCard storage的相关信息
        cityHelper.updateSilver(city);
        cityHelper.cache(city);
        storageHelper.updateCardData(city.getId(), storage);
        storageHelper.cache(storage);
        cityCardHelper.updateAttributes(cc);
        cityCardHelper.cache(cc);

        // 生成HumanCardVO
        HumanCardVO cardVO = new HumanCardVO();
        cardVO.setMaxSlot(cc.getMaxSlot());
        cardVO.setCurSlot(cc.getCurSlot());
        cardVO.setName(cc.getName());
        cardVO.setId(cc.getId());
        cardVO.setIcon(cc.getIcon());
        cardVO.setAttackMax(cc.getAttackMax());
        cardVO.setAttackMin(cc.getAttackMin());
        cardVO.setDefenceMax(cc.getDefenceMax());
        cardVO.setDefenceMin(cc.getDefenceMin());

        return cardVO;
    }
}
