package com.h13.cardgame.jupiter.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.CardCO;
import com.h13.cardgame.cache.co.CityCardCO;
import com.h13.cardgame.cache.co.SkillCO;
import com.h13.cardgame.config.Configuration;
import com.h13.cardgame.jupiter.BaseDTO;
import com.h13.cardgame.jupiter.Constants;
import com.h13.cardgame.jupiter.vo.CaptainCardVO;
import com.h13.cardgame.jupiter.vo.CaptainCityCardVO;
import com.h13.cardgame.jupiter.vo.SkillVO;
import com.h13.cardgame.jupiter.vo.SquardCityCardVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DTO的util
 */
public class DTOUtils {
    private static Log LOG = LogFactory.getLog(DTOUtils.class);

    /**
     * 返回的是一个类似于执行的请求，没有任何返回结果的可能性
     *
     * @param uid
     * @param cid
     * @return
     */
    public static String getOriginalResponse(long uid, long cid) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.SUCCESS));
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

    /**
     * 返回一个成功的操作，并且包含N个返回结果
     *
     * @param uid
     * @param cid
     * @param info
     * @return
     */
    public static String getSucessResponse(long uid, long cid, Object... info) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.SUCCESS));
        for (Object obj : info) {
            list.add(obj);
        }
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

    /**
     * 返回一个操作失败的结果
     *
     * @param uid
     * @param cid
     * @param errorCode
     * @return
     */
    public static String getFailureResponse(long uid, long cid, String errorCode) {
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.FAILURE, errorCode));
        String json = JSON.toJSONString(list);
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }


    public static CaptainCityCardVO toCaptainCityCardVO(CityCardCO cityCard, SkillCO skillCO) {
        CaptainCityCardVO vo = new CaptainCityCardVO();
        vo.setCardId(cityCard.getCardId());
        vo.setDesc(cityCard.getDesc());
        vo.setIcon(cityCard.getIcon());
        vo.setId(cityCard.getId());
        vo.setName(cityCard.getName());
        vo.setLevel(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.LEVEL_KEY));
        vo.setLevelExp(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.LEVEL_EXP_KEY));
        vo.setTitle(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.TITLE_KEY));
        vo.setTitleExp(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CAPTAIN_CITY_CARD.TITLE_EXP_KEY));

        SkillVO skillVO = new SkillVO();
        skillVO.setId(skillCO.getId());
        skillVO.setName(skillCO.getName());
        skillVO.setData(skillCO.getData());
        vo.setSkill(skillVO);
        return vo;
    }

    public static CaptainCardVO toCaptainCardVO(CardCO card) {
        CaptainCardVO vo = new CaptainCardVO();
        vo.setId(card.getId());
        vo.setDesc(card.getDesc());
        vo.setIcon(card.getIcon());
        vo.setName(card.getName());
        return vo;
    }

    public static SquardCityCardVO toSquardCityCardVO(CityCardCO cityCard) {
        SquardCityCardVO vo = new SquardCityCardVO();
        vo.setAttackMax(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CITY_CARD.ATTACK_MAX_KEY));
        vo.setAttackMin(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CITY_CARD.ATTACK_MIN_KEY));
        vo.setDefenceMax(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CITY_CARD.DEFENCE_MAX_KEY));
        vo.setDefenceMin(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CITY_CARD.DEFENCE_MIN_KEY));
        vo.setMaxSlot(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CITY_CARD.MAX_SLOT_KEY));
        vo.setCurSlot(DataUtils.getSquardIntData(cityCard.getData(), Configuration.CITY_CARD.CUR_SLOT_KEY));
        vo.setCardId(cityCard.getCardId());
        vo.setDesc(cityCard.getDesc());
        vo.setIcon(cityCard.getIcon());
        vo.setId(cityCard.getId());
        vo.setName(cityCard.getName());
        return vo;
    }
}
