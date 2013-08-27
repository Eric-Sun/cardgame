package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.*;
import com.h13.cardgame.jupiter.service.TroopService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
import com.h13.cardgame.jupiter.utils.LogWriter;
import com.h13.cardgame.jupiter.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-4-20
 * Time: 下午11:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/troop")
public class TroopController {
    private static Log LOG = LogFactory.getLog(TroopController.class);
    @Autowired
    TroopService troopService;

    @RequestMapping("/attack/search")
    @ResponseBody
    public String search(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        int pageNum = 1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            if (request.getParameter("pageNum") != null)
                pageNum = new Integer(request.getParameter("pageNum"));
            List<AttackTargetVO> list = troopService.searchAttackTarget(uid, cid, pageNum);
            return DTOUtils.getSucessResponse(request, response, uid, cid, list);
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, UserNotExistsException.CODE);
        } catch (UserDontHaveThisCityException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, UserDontHaveThisCityException.CODE);
        }
    }

    @RequestMapping("/attack")
    @ResponseBody
    public String attack(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        int pageNum = 1;
        long uid = -1L;
        long targetCid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            targetCid = new Long(request.getParameter("targetCid"));
            CombatResultVO result = troopService.attack(uid, cid, targetCid);
            return DTOUtils.getSucessResponse(request, response, uid, cid, result);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardNotExistsException.CODE);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsNotYoursException.CODE);
        }
    }


    @RequestMapping("")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            TroopVO troop = troopService.getTroop(cid);
            return DTOUtils.getSucessResponse(request, response, uid, cid, troop);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardNotExistsException.CODE);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsNotYoursException.CODE);
        }
    }

    @RequestMapping("/onList")
    @ResponseBody
    public String onList(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            int index = new Integer(request.getParameter("index"));
            long ccId = new Long(request.getParameter("cityCardId"));
            TroopVO troop = troopService.onList(cid, index, ccId);
            return DTOUtils.getSucessResponse(request, response, uid, cid, troop);
        } catch (IndexOfTroopHaveAnotherCardException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, IndexOfTroopHaveAnotherCardException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardNotExistsException.CODE);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsNotYoursException.CODE);
        } catch (CityCardIsOnListException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsOnListException.CODE);
        }
    }

    @RequestMapping("/downList")
    @ResponseBody
    public String downList(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            int index = new Integer(request.getParameter("index"));
            long ccId = new Long(request.getParameter("cityCardId"));
            TroopVO troop = troopService.downList(cid, index, ccId);
            return DTOUtils.getSucessResponse(request, response, uid, cid, troop);
        } catch (IndexOfTroopHaveAnotherCardException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, IndexOfTroopHaveAnotherCardException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardNotExistsException.CODE);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsNotYoursException.CODE);
        } catch (IndexOfTroopHaveNoCardException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, IndexOfTroopHaveNoCardException.CODE);
        } catch (CityCardIsOnListException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsOnListException.CODE);
        }
    }


    @RequestMapping("/getCombatAttributes")
    @ResponseBody
    public String getCombatAttributes(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            CombatAttributesVO ca = troopService.getCombatAttributes(cid);
            return DTOUtils.getSucessResponse(request, response, uid, cid, ca);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardNotExistsException.CODE);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsNotYoursException.CODE);
        }
    }

    @RequestMapping("/recruit")
    @ResponseBody
    public String recruit(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            // 小队原卡牌的id
            long sCardId = new Long(request.getParameter("sCardId"));
            // 小队卡的id
            String sCityCard = request.getParameter("sCityCard");
            // 合成几个兵
            int count = new Integer(request.getParameter("count"));
            // 合成的兵种
            long uCardId = new Long(request.getParameter("uCardId"));
            HumanCardVO humanCardVO = troopService.recruit(uid, cid, sCardId,
                    sCityCard, count, uCardId);
            return DTOUtils.getSucessResponse(request, response, uid, cid, humanCardVO);
        } catch (SilverNotEnoughException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, -1, cid, SilverNotEnoughException.CODE);
        } catch (RecruitCardIsErrorException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, RecruitCardIsErrorException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardNotExistsException.CODE);
        } catch (UserNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, UserNotExistsException.CODE);
        } catch (SquadCardNotEnoughSlotException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, SquadCardNotEnoughSlotException.CODE);
        } catch (SquadECardException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, SquadECardException.CODE);
        } catch (EquipmentIsNotEnoughException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, EquipmentIsNotEnoughException.CODE);
        } catch (UserDontHaveThisCityException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, UserDontHaveThisCityException.CODE);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request, response, uid, cid, CityCardIsNotYoursException.CODE);
        }
    }


    /**
     * 获得所有的队长城市卡
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/captainCityCards")
    @ResponseBody
    public String captainCityCards(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        long cid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            List<CaptainCityCardVO> list = troopService.getCaptainCityCards(uid, cid);
            return DTOUtils.getSucessResponse(request,response,uid, cid, list);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardNotExistsException.CODE);
        }

    }


    @RequestMapping("/squadCityCards")
    @ResponseBody
    public String squadCityCards(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        long cid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            List<SquadCityCardVO> list = troopService.getSquadCityCards(uid, cid);
            return DTOUtils.getSucessResponse(request,response,uid, cid, list);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardIsNotYoursException.CODE);
        }

    }


    /**
     * 获得单张的队长城市卡具体信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/captainCityCard")
    @ResponseBody
    public String captainCityCard(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        long cid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            long captainCityCardId = new Long(request.getParameter("captainCityCardId"));
            CaptainCityCardVO vo = troopService.getCaptainCityCard(uid, cid, captainCityCardId);
            return DTOUtils.getSucessResponse(request,response,uid, cid, vo);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardNotExistsException.CODE);
        }

    }


    /**
     * 获得单张小队城市卡的具体信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/squadCityCard")
    @ResponseBody
    public String squadCityCard(HttpServletRequest request, HttpServletResponse response) {
        long uid = -1;
        long cid = -1;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            long squadCityCardId = new Long(request.getParameter("squadCityCardId"));
            SquadCityCardVO vo = troopService.getSquadCityCard(uid, cid, squadCityCardId);
            return DTOUtils.getSucessResponse(request,response,uid, cid, vo);
        } catch (CityCardIsNotYoursException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardIsNotYoursException.CODE);
        } catch (CityCardNotExistsException e) {
            LogWriter.warn(LogWriter.TASK, e);
            return DTOUtils.getFailureResponse(request,response,uid, cid, CityCardIsNotYoursException.CODE);
        }
    }
}
