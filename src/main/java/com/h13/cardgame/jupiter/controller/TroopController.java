package com.h13.cardgame.jupiter.controller;

import com.h13.cardgame.jupiter.exceptions.SilverNotEnoughException;
import com.h13.cardgame.jupiter.exceptions.UserNotExistsException;
import com.h13.cardgame.jupiter.exceptions.ServerErrorException;
import com.h13.cardgame.jupiter.service.TroopService;
import com.h13.cardgame.jupiter.utils.DTOUtils;
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
            return DTOUtils.getSucessResponse(uid, cid, list);
        } catch (UserNotExistsException e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, UserNotExistsException.CODE);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
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
            return DTOUtils.getSucessResponse(uid, cid, result);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }


    @RequestMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        long cid = -1;
        long uid = -1L;
        try {
            uid = new Long(request.getParameter("uid"));
            cid = new Long(request.getParameter("cid"));
            TroopVO troop = troopService.getTroop(cid);
            return DTOUtils.getSucessResponse(uid, cid, troop);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
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
            return DTOUtils.getSucessResponse(uid, cid, troop);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
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
            return DTOUtils.getSucessResponse(uid, cid, troop);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
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
            return DTOUtils.getSucessResponse(uid, cid, ca);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
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
            // 小队卡的id                                          c1
            String sCityCard = request.getParameter("sCityCard");
            // 合成几个兵
            int count = new Integer(request.getParameter("count"));
            // 合成的兵种
            long uCardId = new Long(request.getParameter("uCardId"));
            HumanCardVO humanCardVO = troopService.recruit(uid, cid, sCardId, sCityCard, count, uCardId);
            return DTOUtils.getSucessResponse(uid, cid, humanCardVO);
        } catch (SilverNotEnoughException e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, SilverNotEnoughException.CODE);
        } catch (Exception e) {
            LOG.error("error", e);
            return DTOUtils.getFailureResponse(-1, cid, ServerErrorException.CODE);
        }
    }


}
