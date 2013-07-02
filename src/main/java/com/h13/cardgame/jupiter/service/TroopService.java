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
    public CombatResultVO attack(long uid, long fromCid, long toCid) throws CityCardNotExistsException {
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
     * 通过nCardId，找到需要的的装备卡牌id，然后进行扣除，如果装备卡牌id为-1，说明不需要装备就可以合成出来
     * </p>
     *
     * @param sCardId   小队卡牌对应的原卡id
     * @param sCityCard 小队卡牌的id
     * @param count     一共合成几个uCardId的兵
     * @param uCardId   要训练成那种兵种的卡的id
     * @return
     */
    public HumanCardVO recruit(long uid, long cid, long sCardId, String sCityCard, int count, long uCardId)
            throws UserNotExistsException, UserIllegalParamterException, SilverNotEnoughException, ServerErrorException, SquardCardNotEnoughSlotException, CityCardNotExistsException, SquardECardException, RecuitCardIsErrorException {
        CityCO city = cityHelper.get(uid, cid);
        // 准备资源
        StorageCO storage = storageHelper.getByCid(cid);
        CityCardCO squardCityCard = cityCardHelper.get(new Long(sCityCard));
        CardCO unitCard = cardHelper.get(uCardId);
        Map<String, String> eCardData = storage.getECardData();
        Map<String, List<String>> sCardData = storage.getSCardData();

        // 判断兵种卡牌中需要的装备卡牌的id
        long eCardId = cardHelper.getCardSpecData(unitCard, Configuration.CARD.E_CARD_ID_KEY);
        // 检测装备仓库中装备是否有，并且数量是否够
        // 当装备卡为0的时候，表示不需要装备，生成的是最简单的兵种
        int eCardAmountInStorage = 0;
        if (eCardId != Configuration.CARD.E_CARD_ID_DEFAULT_VALUE) {
            eCardAmountInStorage = new Integer(eCardData.get(eCardId + ""));
            if (eCardAmountInStorage < count) {
                throw new ServerErrorException("eCardId=" + eCardId + " have " + eCardAmountInStorage + " in storage < " + count);
            }
            if (!sCardData.get(sCardId + "").contains(sCityCard)) {
                throw new ServerErrorException("cityCardId=" + sCityCard + " not in card data.cid=" + cid + " uid=" + uid);
            }
        }
        // 检查小队卡牌中是否已经设定了兵种，如果设定了兵种卡的话，就不可以用其他的卡了
        // -1表示没有设置过兵种卡
        if (squardCityCard.getUCardId() != uCardId && squardCityCard.getUCardId() != Configuration.SQUARD.DEFAULT_SQUARD_U_CARD_ID_VALUE) {
            throw new SquardECardException("squard has " + squardCityCard.getUCardId() + " recuit uCardId=" + uCardId);
        }

        // 检查银币是否够
        int silverPerCard = cardHelper.getCardSpecData(unitCard, Configuration.CARD.SILVER_KEY);
        if (city.getSilver() < count * silverPerCard) {
            throw new SilverNotEnoughException("need " + count * silverPerCard + " but have " + city.getSilver());
        }

        // 去掉相应的卡，和银币
        if (eCardId != Configuration.CARD.E_CARD_ID_DEFAULT_VALUE) {
            // 如果为0的话，不需要删除卡
            eCardData.put(eCardId + "", eCardAmountInStorage - count + "");
        }
        city.setSilver(city.getSilver() - count * silverPerCard);

        // 检查是否已经超出小队卡的slot限制
        if (squardCityCard.getMaxSlot() < squardCityCard.getCurSlot() + count) {
            throw new SquardCardNotEnoughSlotException("cur is " + squardCityCard.getCurSlot() +
                    " new is " + count + " all is " + squardCityCard.getMaxSlot());
        }
        // 提高小队卡的属性
        squardCityCard.setAttackMax(squardCityCard.getAttackMax() + cardHelper.getCardSpecData(unitCard, Configuration.CARD.ATTACK_MAX_KEY) * count);
        squardCityCard.setAttackMin(squardCityCard.getAttackMin() + cardHelper.getCardSpecData(unitCard, Configuration.CARD.ATTACK_MIN_KEY) * count);
        squardCityCard.setDefenceMax(squardCityCard.getDefenceMax() + cardHelper.getCardSpecData(unitCard, Configuration.CARD.DEFENCE_MAX_KEY) * count);
        squardCityCard.setDefenceMin(squardCityCard.getDefenceMin() + cardHelper.getCardSpecData(unitCard, Configuration.CARD.DEFENCE_MIN_KEY) * count);
        squardCityCard.setCurSlot(squardCityCard.getCurSlot() + count);
        squardCityCard.setUCardId(uCardId);

        // 更新city cityCard storage的相关信息
        cityHelper.updateSilver(city);
        cityHelper.cache(city);
        storageHelper.updateEquipmentCardData(city.getId(), storage);
        storageHelper.cache(storage);
        cityCardHelper.updateAttributes(squardCityCard);
        cityCardHelper.updateUCardId(squardCityCard, uCardId);
        cityCardHelper.cache(squardCityCard);

        // 生成HumanCardVO
        HumanCardVO cardVO = new HumanCardVO();
        cardVO.setMaxSlot(squardCityCard.getMaxSlot());
        cardVO.setCurSlot(squardCityCard.getCurSlot());
        cardVO.setName(squardCityCard.getName());
        cardVO.setId(squardCityCard.getId());
        cardVO.setIcon(squardCityCard.getIcon());
        cardVO.setAttackMax(squardCityCard.getAttackMax());
        cardVO.setAttackMin(squardCityCard.getAttackMin());
        cardVO.setDefenceMax(squardCityCard.getDefenceMax());
        cardVO.setDefenceMin(squardCityCard.getDefenceMin());

        return cardVO;
    }
}
