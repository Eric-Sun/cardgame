package com.h13.cardgame.jupiter.service;

import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.exceptions.*;
import com.h13.cardgame.jupiter.helper.*;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 部队的行为，战斗，等等
 * User: sunbo
 * Date: 13-4-17
 * Time: 下午5:41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TroopService {
    private Log LOG = LogFactory.getLog(PassportService.class);

    @Autowired
    TroopHelper troopHelper;
    @Autowired
    CardHelper cardHelper;
    @Autowired
    CityHelper cityHelper;
    @Autowired
    StorageHelper storageHelper;
    @Autowired
    ConfigHelper configService;
    @Autowired
    SquadCityCardHelper squadCityCardHelper;
    @Autowired
    CaptainCityCardHelper captainCityCardHelper;

    /**
     * 获得小队中所有的卡牌的小列表
     *
     * @return
     */
    public TroopVO getTroop(long cid) throws CityCardNotExistsException, CityCardIsNotYoursException {
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

    private TroopVO toVO(TroopCO troop) throws CityCardNotExistsException, CityCardIsNotYoursException {

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
                vo.getCardList().add(DTOUtils.newNullCityCardVO());
                continue;
            }
            SquadCityCardCO squadCityCardCO = squadCityCardHelper.get(troop.getCityId(), new Long(id));
            CityCardVO cityCardVO = new CityCardVO();
            cityCardVO.setCardId(squadCityCardCO.getCardId());
            cityCardVO.setIcon(squadCityCardCO.getIcon());
            cityCardVO.setName(squadCityCardCO.getName());
            cityCardVO.setAttackMax(squadCityCardCO.getAttackMax());
            cityCardVO.setAttackMin(squadCityCardCO.getAttackMin());
            cityCardVO.setDefenceMax(squadCityCardCO.getDefenceMax());
            cityCardVO.setDefenceMin(squadCityCardCO.getDefenceMin());
            cityCardVO.setId(squadCityCardCO.getId());
            vo.getCardList().add(cityCardVO);
        }
        return vo;
    }

    /**
     * 计算小队的攻击力和防御力
     *
     * @return
     */
    public CombatAttributesVO getCombatAttributes(long cid) throws CityCardNotExistsException, CityCardIsNotYoursException {
        TroopCO troop = troopHelper.getByCid(cid);
        CombatAttributesVO attributes = new CombatAttributesVO();
        for (String ccId : troop.getMembers()) {
            if (ccId == null)
                continue;
            SquadCityCardCO squadCityCardCO = squadCityCardHelper.get(cid, new Long(ccId));
            attributes.setAttackMax(attributes.getAttackMax() + squadCityCardCO.getAttackMax());
            attributes.setAttackMin(attributes.getAttackMin() + squadCityCardCO.getAttackMin());
            attributes.setDefenceMax(attributes.getDefenceMax() + squadCityCardCO.getDefenceMax());
            attributes.setDefenceMin(attributes.getDefenceMin() + squadCityCardCO.getDefenceMin());
            LOG.debug("squads list attr: " + attributes);
            // 合成captain的攻击力
            long captainCityCardId = squadCityCardCO.getCaptainId();
            CaptainCityCardCO captainCityCardCO = captainCityCardHelper.get(cid, captainCityCardId);
            if (captainCityCardId != Configuration.SQUAD_CITY_CARD.DEFAULT_CAPTAIN_ID_VALUE) {
                int captainAttackMax = captainCityCardCO.getAttackMax();
                int captainAttackMin = captainCityCardCO.getAttackMin();
                int captainDefenceMax = captainCityCardCO.getDefenceMax();
                int captainDefenceMin = captainCityCardCO.getDefenceMin();
                LOG.debug("captain attr: attackMax:" + captainAttackMax + " attackMin:" + captainAttackMin
                        + " defenceMax:" + captainDefenceMax + " defenceMin:" + captainDefenceMin);
                attributes.setAttackMax(attributes.getAttackMax() + captainAttackMax);
                attributes.setAttackMin(attributes.getAttackMin() + captainAttackMin);
                attributes.setDefenceMax(attributes.getDefenceMax() + captainDefenceMax);
                attributes.setDefenceMin(attributes.getDefenceMin() + captainDefenceMin);
            }
        }
        return attributes;
    }


    /**
     * 获得小队列表
     *
     * @return
     */

    public TroopVO getLargeSquad(long cid) throws CityCardNotExistsException, CityCardIsNotYoursException {
        TroopVO ss = new TroopVO();
        TroopCO squad = troopHelper.getByCid(cid);
        String[] list = squad.getMembers();
        List<CityCardVO> cList = new ArrayList<CityCardVO>();
        for (String ccId : list) {
            SquadCityCardCO squadCityCardCO = squadCityCardHelper.get(cid, new Long(ccId));
            CityCardVO card = new CityCardVO();
            card.setId(squadCityCardCO.getCardId());
            card.setIcon(squadCityCardCO.getIcon());
            card.setName(squadCityCardCO.getName());
            card.setAttackMax(squadCityCardCO.getAttackMax());
            card.setAttackMin(squadCityCardCO.getAttackMin());
            card.setDefenceMax(squadCityCardCO.getDefenceMax());
            card.setAttackMin(squadCityCardCO.getDefenceMin());
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
    public CombatResultVO attack(long uid, long fromCid, long toCid) throws CityCardNotExistsException, CityCardIsNotYoursException {
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
    public List<AttackTargetVO> searchAttackTarget(long uid, long cid, int pageNum) throws
            UserNotExistsException, UserDontHaveThisCityException {
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
            throws UserNotExistsException, SilverNotEnoughException, SquadCardNotEnoughSlotException, CityCardNotExistsException, SquadECardException, RecruitCardIsErrorException, EquipmentIsNotEnoughException, UserDontHaveThisCityException, CityCardIsNotYoursException {
        CityCO city = cityHelper.get(uid, cid);
        // 准备资源
        StorageCO storage = storageHelper.getByCid(cid);
        SquadCityCardCO squadCityCard = squadCityCardHelper.get(cid, new Long(sCityCard));
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
                throw new EquipmentIsNotEnoughException("eCardId=" + eCardId + " have " + eCardAmountInStorage + " in storage < " + count);
            }
            if (!sCardData.get(sCardId + "").contains(sCityCard)) {
                throw new CityCardNotExistsException("cityCardId=" + sCityCard + " not in card data.cid=" + cid + " uid=" + uid);
            }
        }
        // 检查小队卡牌中是否已经设定了兵种，如果设定了兵种卡的话，就不可以用其他的卡了
        // -1表示没有设置过兵种卡
        if (squadCityCard.getUnitCardId() != uCardId &&
                squadCityCard.getUnitCardId() != Configuration.SQUAD_CITY_CARD.DEFAULT_SQUAD_U_CARD_ID_VALUE) {
            throw new SquadECardException("squad has " + squadCityCard.getUnitCardId() + " recuit uCardId=" + uCardId);
        }

        // 检查银币是否够
        int silverPerCard = cardHelper.getCardSpecData(unitCard, Configuration.CARD.SILVER_KEY);
        if (city.getSilver() < count * silverPerCard) {
            throw new SilverNotEnoughException("need " + count * silverPerCard + " but have " + city.getSilver());
        }

        // 去掉相应的卡，和银币
        if (eCardId != Configuration.CARD.E_CARD_ID_DEFAULT_VALUE) {
            // 如果为-1的话，不需要删除卡
            eCardData.put(eCardId + "", eCardAmountInStorage - count + "");
        }
        city.setSilver(city.getSilver() - count * silverPerCard);

        // 检查是否已经超出小队卡的slot限制
        int maxSlot = squadCityCard.getMaxSlot();
        int curSlot = squadCityCard.getCurSlot();
        if (maxSlot < curSlot + count) {
            throw new SquadCardNotEnoughSlotException("cur is " + curSlot +
                    " new is " + count + " all is " + maxSlot);
        }
        // 提高小队卡的属性
        int oldAttackMax = squadCityCard.getAttackMax();
        int oldAttackMin = squadCityCard.getAttackMin();
        int oldDefenceMax = squadCityCard.getDefenceMax();
        int oldDefenceMin = squadCityCard.getDefenceMin();
        int oldCurSlot = squadCityCard.getCurSlot();

        int addAttackMax = cardHelper.getCardSpecData(unitCard, Configuration.CARD.ATTACK_MAX_KEY) * count;
        int addAttackMin = cardHelper.getCardSpecData(unitCard, Configuration.CARD.ATTACK_MIN_KEY) * count;
        int addDefenceMax = cardHelper.getCardSpecData(unitCard, Configuration.CARD.DEFENCE_MAX_KEY) * count;
        int addDefenceMin = cardHelper.getCardSpecData(unitCard, Configuration.CARD.DEFENCE_MIN_KEY) * count;

        squadCityCard.setAttackMax(oldAttackMax + addAttackMax);
        squadCityCard.setAttackMin(oldAttackMin + addAttackMin);
        squadCityCard.setDefenceMax(oldDefenceMax + addDefenceMax);
        squadCityCard.setDefenceMin(oldDefenceMin + addDefenceMin);
        squadCityCard.setCurSlot(oldCurSlot + count);
        squadCityCard.setUnitCardId(uCardId);

        // 更新city cityCard storage的相关信息
        cityHelper.updateSilver(city);
        cityHelper.cache(city);
        storageHelper.updateEquipmentCardData(city.getId(), storage);
        storageHelper.cache(storage);
        squadCityCardHelper.updateAttributes(squadCityCard);
        squadCityCardHelper.updateUnitCardId(squadCityCard, uCardId);
        squadCityCardHelper.cache(squadCityCard);

        // 生成HumanCardVO
        HumanCardVO cardVO = new HumanCardVO();
        cardVO.setMaxSlot(squadCityCard.getMaxSlot());
        cardVO.setCurSlot(squadCityCard.getCurSlot());
        cardVO.setName(squadCityCard.getName());
        cardVO.setId(squadCityCard.getId());
        cardVO.setIcon(squadCityCard.getIcon());
        cardVO.setAttackMax(squadCityCard.getAttackMax());
        cardVO.setAttackMin(squadCityCard.getAttackMin());
        cardVO.setDefenceMax(squadCityCard.getDefenceMax());
        cardVO.setDefenceMin(squadCityCard.getDefenceMin());

        return cardVO;
    }

    public List<CaptainCityCardVO> getCaptainCityCards(long uid, long cid) throws CityCardNotExistsException, CityCardIsNotYoursException {
        List<CaptainCityCardCO> list = captainCityCardHelper.getCityCardsFromStorage(uid, cid);
        List<CaptainCityCardVO> returnList = new LinkedList<CaptainCityCardVO>();
        for (CaptainCityCardCO captainCityCard : list) {
            SkillCO skillCO = captainCityCardHelper.getSkill(captainCityCard);
            CaptainCityCardVO vo = DTOUtils.toCaptainCityCardVO(captainCityCard, skillCO);
            returnList.add(vo);
        }
        return returnList;
    }

    public List<SquadCityCardVO> getSquadCityCards(long uid, long cid) throws CityCardNotExistsException, CityCardIsNotYoursException {
        List<SquadCityCardCO> list = squadCityCardHelper.getSquadCityCards(uid, cid);
        List<SquadCityCardVO> returnList = new LinkedList<SquadCityCardVO>();
        for (SquadCityCardCO cityCard : list) {
            SquadCityCardVO vo = DTOUtils.toSquadCityCardVO(cityCard);
            returnList.add(vo);
        }
        return returnList;
    }

    public CaptainCityCardVO getCaptainCityCard(long uid, long cid, long captainCityCardId) throws CityCardNotExistsException, CityCardIsNotYoursException {
        CaptainCityCardCO captainCityCard = captainCityCardHelper.get(cid, captainCityCardId);
        SkillCO skillCO = captainCityCardHelper.getSkill(captainCityCard);
        return DTOUtils.toCaptainCityCardVO(captainCityCard, skillCO);
    }


    public SquadCityCardVO getSquadCityCard(long uid, long cid, long squadCityCardId) throws CityCardNotExistsException, CityCardIsNotYoursException {
        SquadCityCardCO co = squadCityCardHelper.get(cid, squadCityCardId);
        return DTOUtils.toSquadCityCardVO(co);
    }
}
