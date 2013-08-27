package com.h13.cardgame.jupiter.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.h13.cardgame.cache.co.*;
import com.h13.cardgame.jupiter.BaseDTO;
import com.h13.cardgame.jupiter.Constants;
import com.h13.cardgame.jupiter.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DTO的util
 */
public class DTOUtils {
    private static Log LOG = LogFactory.getLog(DTOUtils.class);
    private static String CALLBACK_KEY = "callback";

    /**
     * 返回的是一个类似于执行的请求，没有任何返回结果的可能性
     *
     * @param uid
     * @param cid
     * @return
     */
    public static String getOriginalResponse(HttpServletRequest request, HttpServletResponse response,
                                             long uid, long cid) {
        String callback = request.getParameter(CALLBACK_KEY);
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.SUCCESS));
        String json = null;
        if (callback == null)
            json = JSON.toJSONString(list);
        else
            json = callback + "(" + JSON.toJSONString(list) + ")";
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
    public static String getSucessResponse(HttpServletRequest request, HttpServletResponse response,
                                           long uid, long cid, Object... info) {
        String callback = request.getParameter(CALLBACK_KEY);
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.SUCCESS));
        for (Object obj : info) {
            list.add(obj);
        }
        String json = null;
        if (callback == null)
            json = JSON.toJSONString(list);
        else
            json = callback + "(" + JSON.toJSONString(list) + ")";
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
    public static String getFailureResponse(HttpServletRequest request, HttpServletResponse response,
                                            long uid, long cid, String errorCode) {
        String callback = request.getParameter(CALLBACK_KEY);
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(uid, cid, Constants.ResponseStatus.FAILURE, errorCode));
        String json = null;
        if (callback == null)
            json = JSON.toJSONString(list);
        else
            json = callback + "(" + JSON.toJSONString(list) + ")";
        LogWriter.error(LogWriter.RESPONSE, json);
        return json;
    }


    public static CaptainCityCardVO toCaptainCityCardVO(CaptainCityCardCO captainCityCardCO, SkillCO skillCO) {
        CaptainCityCardVO vo = new CaptainCityCardVO();
        vo.setCardId(captainCityCardCO.getCardId());
        vo.setIcon(captainCityCardCO.getIcon());
        vo.setId(captainCityCardCO.getId());
        vo.setName(captainCityCardCO.getName());
        vo.setLevel(captainCityCardCO.getLevel());
        vo.setLevelExp(captainCityCardCO.getLevelExp());
        vo.setTitle(captainCityCardCO.getTitle());
        vo.setTitleExp(captainCityCardCO.getTitleExp());

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

    public static SquadCityCardVO toSquadCityCardVO(SquadCityCardCO squadCityCardCO) {
        SquadCityCardVO vo = new SquadCityCardVO();
        vo.setAttackMax(squadCityCardCO.getAttackMax());
        vo.setAttackMin(squadCityCardCO.getAttackMin());
        vo.setDefenceMax(squadCityCardCO.getDefenceMax());
        vo.setDefenceMin(squadCityCardCO.getDefenceMin());
        vo.setMaxSlot(squadCityCardCO.getMaxSlot());
        vo.setCurSlot(squadCityCardCO.getCurSlot());
        vo.setCardId(squadCityCardCO.getCardId());
        vo.setIcon(squadCityCardCO.getIcon());
        vo.setId(squadCityCardCO.getId());
        vo.setName(squadCityCardCO.getName());
        return vo;
    }

    public static CityVO toCityVO(CityCO city) {
        CityVO vo = new CityVO();
        vo.setId(city.getId());
        vo.setEnergy(city.getEnergy());
        vo.setExp(city.getExp());
        vo.setGold(city.getGold());
        vo.setLevel(city.getLevel());
        vo.setName(city.getName());
        vo.setSilver(city.getSilver());
        vo.setUserId(city.getUserId());
        vo.setBarSize(city.getBarSize());
        vo.setCooldownStatus(city.getCooldownStatus());
        vo.setTaskStatus(city.getTaskStatus());
        return vo;
    }


    /**
     * 生成一个空的卡牌，用于占位
     *
     * @return
     */
    public static CityCardVO newNullCityCardVO() {
        CityCardVO vo = new CityCardVO();
        vo.setFlag(CityCardVO.NULL);
        return vo;
    }

    public static BoardMessageVO toBoardMessageVO(BoardMessageCO boardMessageCO) {
        BoardMessageVO boardMessageVO = new BoardMessageVO();
        boardMessageVO.setCityId(boardMessageCO.getCityId());
        boardMessageVO.setTs(boardMessageCO.getTs());
        boardMessageVO.setCityName(boardMessageCO.getCityName());
        boardMessageVO.setContent(boardMessageCO.getContent());
        boardMessageVO.setId(boardMessageCO.getId());
        return boardMessageVO;
    }

    public static MessageBoxVO toMessageBoxVO(MessageBoxCO messageBoxCO) {
        MessageBoxVO messageBoxVO = new MessageBoxVO();
        messageBoxVO.setId(messageBoxCO.getId());
        messageBoxVO.setCreateTime(messageBoxCO.getCreateTime());
        messageBoxVO.setContent(messageBoxCO.getContent());
        messageBoxVO.setToCityId(messageBoxCO.getToCityId());
        messageBoxVO.setFromCityId(messageBoxCO.getFromCityId());
        return messageBoxVO;
    }
}
